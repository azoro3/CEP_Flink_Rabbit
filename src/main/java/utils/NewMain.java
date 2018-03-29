/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import RabbitMQ.Sender;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Arthur
 */
public class NewMain {

    private static final String HOST = "localhost";
    private static final int[] PORTS = {5672, 5673, 5674};
    private static final RandomValue RD = new RandomValue();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, TimeoutException {
      
    		Sender.send("10Chambre111,3,true,false,true,6", HOST, PORTS[RD.getValue()],"input");
        Sender.send("9Chambre315,3,true,true,true,8", HOST, PORTS[RD.getValue()],"input");
        Sender.send("18Chambre101,1,false,false,false,9", HOST, PORTS[RD.getValue()],"input");
        Sender.send("18Chambre102,1,false,false,false,13", HOST, PORTS[RD.getValue()],"input");
        Sender.send("18Chambre103,1,false,false,false,14", HOST, PORTS[RD.getValue()],"input");


        Sender.send("10Chambre111,3,true,false,true,6", HOST, PORTS[RD.getValue()],"input2");
        Sender.send("9Chambre315,3,true,true,true,8", HOST, PORTS[RD.getValue()],"input2");
        Sender.send("18Chambre101,1,false,false,false,9", HOST, PORTS[RD.getValue()],"input2");
        Sender.send("18Chambre102,1,false,false,false,13", HOST, PORTS[RD.getValue()],"input2");
        Sender.send("18Chambre103,1,false,false,false,14", HOST, PORTS[RD.getValue()],"input2");
    }

}
