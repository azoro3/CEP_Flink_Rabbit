
import utils.RandomValue;
import RabbitMQ.Receiver;
import RabbitMQ.Sender;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class flint_rabbit {

    private static final String HOST = "localhost";
    private static final int[] PORTS = {5672, 5673, 5674};
    private static final RandomValue RD = new RandomValue();

    public static void main(String[] args) throws IOException, TimeoutException, Exception {
        Sender.send("C12,3", HOST, PORTS[RD.getValue()]);
        //Receiver.receive(HOST, PORTS[RD.getValue()]);
        Flink.FlinkWork.wordCount();
    }

}
