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
      
    		Sender.send("C5,3,true,false,true", HOST, PORTS[RD.getValue()]);
        Sender.send("C22,3,true,true,true", HOST, PORTS[RD.getValue()]);
        Sender.send("C23,1,false,false,false", HOST, PORTS[RD.getValue()]);

    }

}
