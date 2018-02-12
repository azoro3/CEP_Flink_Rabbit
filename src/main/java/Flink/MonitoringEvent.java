package Flink;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Arthur
 */
public class MonitoringEvent {

    private String idClient;
    private String ancienneChute;

    public MonitoringEvent() {}

    public MonitoringEvent(String idClient) {
        this.idClient=idClient;
    }


    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getAncienneChute() {
        return ancienneChute;
    }

    public void setAncienneChute(String ancienneChute) {
        this.ancienneChute = ancienneChute;
    }
    @Override
    public String toString(){
        return "("+this.idClient+","+this.ancienneChute+")";
    }

}
