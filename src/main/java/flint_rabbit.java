
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

public class flint_rabbit {

    private static final String HOST = "localhost";
    private static final int[] PORTS = {5672, 5673, 5674};
    private static final RandomValue RD = new RandomValue();

    public static void main(String[] args) throws IOException, TimeoutException, Exception {

        Sender.send("hello world", HOST, PORTS[RD.getValue()]);
        Sender.send("hello world", HOST, PORTS[RD.getValue()]);
        Receiver.receive(HOST, PORTS[RD.getValue()]);
    }
}
