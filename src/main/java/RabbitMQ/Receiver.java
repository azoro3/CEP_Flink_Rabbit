package RabbitMQ;


import Flink.FlinkWork;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver {

    private final static String QUEUE_NAME = "hello";
    static String MESSAGE = "";
    static FlinkWork FW = new FlinkWork();

    public static String receive(String host, int port)
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
                FW.wordCount(message);
            }
        };
        System.out.println(MESSAGE);
        channel.basicConsume(QUEUE_NAME, true, consumer);
        return MESSAGE;
    }
}
