import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Receiver {
    private final static String QUEUE_NAME = "hello";
    private static LinkedList<String> MESSAGE = new LinkedList<>();

    public static List<String> receive(String host,int port)
            throws java.io.IOException,
            TimeoutException {

        final ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.printf("Received '%s' on port %d \n", message, factory.getPort());
                MESSAGE.add(message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
        return MESSAGE;
    }
}
