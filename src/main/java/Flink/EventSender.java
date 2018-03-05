package Flink;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 
 * @author Arthur
 * Envoi d'un évènement après traitement dans le CEP pour affectation
 */
public class EventSender {
	
	//Etablir la connexion ou la getter
	//prendre l'objet
	//Envoyer objet ou representation
	  private final static String QUEUE_NAME = "AffectationQueue";

	  public void send(Alert event) throws Exception {
	    
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    
	    
	    Gson gson = new GsonBuilder().create();
	    String message = gson.toJson(event);
	    
	    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
	    System.out.println(" [x] Sent '" + message + "'");

	    channel.close();
	    connection.close();
	  }
}
