/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import RabbitMQ.Sender;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.sql.*;

public class NewMainDB {

    private static final String HOST = "localhost";
    private static final int[] PORTS = {5672, 5673, 5674};
    private static final RandomValue RD = new RandomValue();
    public static void main(String[] argv) throws IOException, TimeoutException {

        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;
        Statement stmt = null;
        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://92.222.86.67:5432/personnesagees", "postgres",
                    "toJIN");

            System.out.println("Creating statement...");
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT emplacement, idclient, round(random() * 2 + 1) as ancienne_chute, random() > 0.5 as chaise_roulante, random() > 0.5 as fracture, random() > 0.5 as deambulateur,idevent FROM event_assistance LIMIT 500;";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                String emplacement  = rs.getString("emplacement");
                int idclient  = rs.getInt("idclient");
                int ancienne_chute  = rs.getInt("ancienne_chute");
                Boolean chaiseRoualnte = rs.getBoolean("chaise_roulante");
                Boolean fracture = rs.getBoolean("fracture");
                Boolean deambulateur = rs.getBoolean("deambulateur");
                int idevent  = rs.getInt("idevent");

                //Display values
                //"10Chambre111,3,true,false,true,6"
                //String evenement = emplacement+","+idclient+","+ancienne_chute+","+chaiseRoualnte+","+fracture+","+deambulateur+","+idevent;
                String evenement = emplacement+","+ancienne_chute+","+chaiseRoualnte+","+fracture+","+deambulateur+","+idevent;
                System.out.println(evenement);
        			Sender.send(evenement, HOST, PORTS[RD.getValue()],"input");
        			Sender.send(evenement, HOST, PORTS[RD.getValue()],"input2");

            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            connection.close();



        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

}