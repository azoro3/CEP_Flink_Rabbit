package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class EntityManager {

    static EntityManager entityManager;

    public static EntityManager getInstance(){
        if(entityManager == null) {
            entityManager = new EntityManager();
        }
        return entityManager;
    }

    public boolean hasCurrentYearFallTwice(String idUniqueIndividu) {
        ArrayList<String> idIndividus = new ArrayList<>();
        try {
            PreparedStatement pstmt = PostgresConnection.getDbCon().conn.prepareStatement("SELECT * FROM rule3 WHERE idunique=?");
            pstmt.setString(1, idUniqueIndividu);
            pstmt.execute();

            ResultSet resultSet = pstmt.getResultSet();
            
            while (resultSet.next()) {
                idIndividus.add(resultSet.getString("idunique"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (idIndividus.size()>0)?true:false;
    }

    /*public void saveEvent(Event event) {
        try {
            PreparedStatement pstmt = PostgresConnection.getDbCon().conn.prepareStatement(
                    "INSERT INTO event_assistance (" +
                            "idevent_type, " +
                            "heure_enregistrement, " +
                            "emplacement, " +
                            "confirmed_event, " +
                            "idniveau_urgence, " +
                            "idclient, " +
                            "teleoperateur_idconseiller_confirmation, " +
                            "closed_event, " +
                            "teleoperateur_idconseiller_cloture, " +
                            "heure_confirmation, " +
                            "heure_cloture, " +
                            "appel_final_concluant, " +
                            "image_name) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");


            pstmt.setInt(1, event.getIdevent_type());
            pstmt.setDate(2, BDD.convertDateToSQLFormat(event.getHeure_enregistrement()));
            pstmt.setString(3, event.getEmplacement());
            pstmt.setBoolean(4, event.getConfirmed_event());
            pstmt.setInt(5, event.getIdniveau_urgence());
            pstmt.setInt(6, event.getIdclient());
            pstmt.setInt(7, event.getTeleoperateur_idconseiller_confirmation());
            pstmt.setBoolean(8, event.getClosed_event());
            pstmt.setInt(9, event.getTeleoperateur_idconseiller_cloture());
            pstmt.setDate(10, BDD.convertDateToSQLFormat(event.getHeure_confirmation()));
            pstmt.setDate(11, BDD.convertDateToSQLFormat(event.getHeure_cloture()));
            pstmt.setBoolean(12, event.getAppel_final_concluant());
            pstmt.setString(13, event.getImage_name());
            pstmt.execute();

        }
        catch (Exception e) {
            System.err.println("Erreur a l'enregistrement de l'evenement ! ");
            e.printStackTrace();
        }
    }*/

}

