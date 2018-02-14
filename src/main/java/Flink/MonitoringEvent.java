package Flink;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Objects;

/**
 *
 * @author Arthur
 */
public class MonitoringEvent {

    private String idClient;
    private String ancienneChute;

    public MonitoringEvent() {}

    public MonitoringEvent(String idClient, String ancienneChute) {
        this.idClient = idClient;
        this.ancienneChute = ancienneChute;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonitoringEvent)) return false;
        MonitoringEvent that = (MonitoringEvent) o;
        return Objects.equals(idClient, that.idClient) &&
                Objects.equals(ancienneChute, that.ancienneChute);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idClient, ancienneChute);
    }
}
