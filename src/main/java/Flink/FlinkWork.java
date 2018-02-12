package Flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.util.Collector;
import utils.RandomValue;

/**
 *
 * @author Arthur
 */
public class FlinkWork {

    private static final String HOST = "localhost";
    private static final int[] PORTS = {5672, 5673, 5674};
    private static final RandomValue RD = new RandomValue();

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
         * Retrieve data stream from rabbitMQ
         */
        final DataStream<String> stream = env
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
        DataStream<MonitoringEvent> ds = stream.flatMap(new Tokenizer());
        Pattern<MonitoringEvent, ?> warningPattern = Pattern.<MonitoringEvent>begin("start")
                .where(new SimpleCondition<MonitoringEvent>() {
                    @Override
                    public boolean filter(MonitoringEvent evt) {
                        return evt.getIdClient().equals("C12");
                    }
                });
        PatternStream<MonitoringEvent> fallPatternStream = CEP.pattern(ds, warningPattern);
        ds.print();
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
            out.collect(me);
        }
    }
}
