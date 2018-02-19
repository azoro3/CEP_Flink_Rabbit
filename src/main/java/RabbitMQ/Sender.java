package RabbitMQ;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.util.concurrent.TimeoutException;

public class Sender {
    private final static String QUEUE_NAME = "hello";
    public static void send(String msg,String host,int port,String queue) throws java.io.IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queue, true, false, false, null);
        channel.basicPublish("", queue, null, msg.getBytes());
        System.out.printf("Send '%s' on port %d \n",msg,factory.getPort());
        channel.close();
        connection.close();
    }
}