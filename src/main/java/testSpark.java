
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.util.Collector;

public class testSpark {

    private static final String HOST = "localhost";
    private static final int[] PORTS = {5672, 5673, 5674};
    private static final RandomValue RD = new RandomValue();
    private static final String USERNAME = "guest";
    private static final String PWD = "guest";
    static final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    static final RMQConnectionConfig connectionConfig = new RMQConnectionConfig.Builder()
            .setHost(HOST)
            .setPort(PORTS[RD.getValue()])
            .setVirtualHost(HOST)
            .setUserName(USERNAME)
            .setPassword(PWD)
            .build();

    public static void main(String[] args) throws IOException, TimeoutException, Exception {
        Sender.send("hello;world", HOST, PORTS[RD.getValue()]);
        Sender.send("hello;world", HOST, PORTS[RD.getValue()]);
        DataStream<String> stream = env.addSource(new RMQSource<>(
                connectionConfig, // config for the RabbitMQ connection
                "hello", // name of the RabbitMQ queue to consume
                true, // use correlation ids; can be false if only at-least-once is required
                new SimpleStringSchema())) // deserialization schema to turn messages into Java objects
                .setParallelism(1);

        DataStream<String[]> res = stream.map(line -> line.split(";"));
        res.print();
        env.execute("Streaming WordCount Example");
    }
}
