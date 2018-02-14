package Flink;

import DB.EntityManager;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.util.Collector;
import utils.RandomValue;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Arthur
 */

/*
 * chaise roulante
 * fracture
 * déambulateur
 * 
 * -- pas sur série temporelles / type de la chute / niveau
 * check apres midi
 * 2 chutes de type 3 en 12 mois
 */
public class FlinkWork {

    private static final String HOST = "localhost";
    private static final int[] PORTS = {5672, 5673, 5674};
    private static final RandomValue RD = new RandomValue();

    private static final double CHUTE_GRAVE = 3;

    public FlinkWork() {
    }

    public static void wordCount() throws Exception {
        /**
         * RabbitMQ connection
         */
        final RMQConnectionConfig connectionConfig = new RMQConnectionConfig.Builder()
                .setHost(HOST)
                .setPort(PORTS[RD.getValue()])
                .setUserName("guest")
                .setPassword("guest")
                .setVirtualHost("/")
                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        /**
         * Retrieve data inputEventstream from rabbitMQ
         */
        final DataStream<String> inputEventstream = env
                .addSource(new RMQSource<>(
                        connectionConfig, // config for the RabbitMQ connection
                        "hello", // name of the RabbitMQ queue to consume
                        true, // use correlation ids; can be false if only at-least-once is required
                        new SimpleStringSchema())) // deserialization schema to turn messages into Java objects
                .setParallelism(1);
        /**
         * Change DataStream<String> to DataStream<MonitoringEvent> where
         * MonitoringEvent refer to a class which modelize our event.
         */
        DataStream<MonitoringEvent> inputEventStreamClean = inputEventstream.flatMap(new Tokenizer());

        Pattern<MonitoringEvent, ?> warningPattern = Pattern.<MonitoringEvent>begin("start")
                .subtype(MonitoringEvent.class)
                .where(new SimpleCondition<MonitoringEvent>() {
                    @Override
                    public boolean filter(MonitoringEvent value) {
                    		return Integer.parseInt(value.getAncienneChute())>=CHUTE_GRAVE;
                    }
                }).or(new SimpleCondition<MonitoringEvent>() {
                    @Override
                    public boolean filter(MonitoringEvent value) {
                    		return value.isChaiseRoulante();
                    }
                }).or(new SimpleCondition<MonitoringEvent>() {
                		@Override
                		public boolean filter(MonitoringEvent value) {
                    		return value.isDeambulateur();
                    }
				}).or(new SimpleCondition<MonitoringEvent>() {
            		@Override
            		public boolean filter(MonitoringEvent value) {
                		return value.isDeambulateur();
                }
				})
                .or(new SimpleCondition<MonitoringEvent>() {
            		@Override
            		public boolean filter(MonitoringEvent value) {
                		return EntityManager.getInstance().hasCurrentYearFallTwice(value.getIdClient());
                }
				});
                /*.where(new IterativeCondition<MonitoringEvent>() {
                    private static final long serialVersionUID = -6301755149429716724L;

                    @Override
                    public boolean filter(MonitoringEvent value, Context<MonitoringEvent> ctx) throws Exception {
                        return Integer.parseInt(value.getAncienneChute())>=CHUTE_GRAVE && (value.isChaiseRoulante() || value.isDeambulateur() || value.isFracture());
                    }
                });*/

        //PatternStream<MonitoringEvent> fallPatternStream = CEP.pattern(inputEventStreamClean.keyBy("idClient"), warningPattern);
        inputEventStreamClean.print();

        // Create a pattern stream from our warning pattern
        PatternStream<MonitoringEvent> tempPatternStream = CEP.pattern(
                inputEventStreamClean.keyBy("idClient"),
                warningPattern);

        DataStream<FallWarning> warnings = tempPatternStream.select(
                (Map<String, List<MonitoringEvent>> pattern) -> {
                    MonitoringEvent first = (MonitoringEvent) pattern.get("start").get(0);
                    return new FallWarning(first.getIdClient(), Integer.valueOf(first.getAncienneChute()));
                }
        );

        // Alert pattern: Two consecutive temperature warnings appearing within a time interval of 20 seconds
        Pattern<FallWarning, ?> alertPattern = Pattern.<FallWarning>begin("start");

        // Create a pattern stream from our alert pattern
        PatternStream<FallWarning> alertPatternStream = CEP.pattern(
                //warnings.keyBy("idClient"),
        		    warnings,
                alertPattern);

        // Generate a temperature alert only iff the second temperature warning's average temperature is higher than
        // first warning's temperature
        DataStream<Alert> alerts = alertPatternStream.flatSelect(
                (Map<String, List<FallWarning>> pattern, Collector<Alert> out) -> {
                    FallWarning first = pattern.get("start").get(0);

                    if (first.idNiveauUrgence>=CHUTE_GRAVE && (first.isChaiseRoulante() || first.isDeambulateur() || first.isFracture())) {
                        out.collect(new Alert(first.idClient));
                    }
                });

        // Print the warning and alert events to stdout
        warnings.print();
        alerts.print();

        env.execute();
    }

    /**
     * Inner class
     */
    public static final class Tokenizer implements FlatMapFunction<String, MonitoringEvent> {

        /**
         *
         * @param value String in output
         * @param out MonitoringEvent
         */
        @Override
        public void flatMap(String value, Collector<MonitoringEvent> out) {
            // normalize and split the line
            String[] tokens = value.toLowerCase().split(",");
            MonitoringEvent me = new MonitoringEvent();
            me.setIdClient(tokens[0]);
            me.setAncienneChute(tokens[1]);

            System.out.println(tokens[2] + " "+  tokens[3] + " " + tokens[4]);

            me.setChaiseRoulante(Boolean.valueOf(tokens[2]));
            me.setFracture(Boolean.valueOf(tokens[3]));
            me.setDeambulateur(Boolean.valueOf(tokens[4]));

            out.collect(me);
        }
    }
}
