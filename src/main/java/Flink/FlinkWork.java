package Flink;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.util.Collector;

/**
 *
 * @author Arthur
 */
public class FlinkWork {

    public static void wordCount() {
        final RMQConnectionConfig connectionConfig = new RMQConnectionConfig.Builder()
                .setHost("localhost")
                .setPort(5672)
                .setUserName("guest")
                .setPassword("guest")
                .setVirtualHost("/")
                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        final DataStream<String> stream = env
                .addSource(new RMQSource<>(
                        connectionConfig, // config for the RabbitMQ connection
                        "hello", // name of the RabbitMQ queue to consume
                        true, // use correlation ids; can be false if only at-least-once is required
                        new SimpleStringSchema())) // deserialization schema to turn messages into Java objects
                .setParallelism(1);
        DataStream<MonitoringEvent> ds = stream.flatMap(new Tokenizer());
        try {
            env.execute();
        } catch (Exception ex) {
            Logger.getLogger(FlinkWork.class.getName()).log(Level.SEVERE, null, ex);
        }
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
