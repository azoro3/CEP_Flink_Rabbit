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
    private boolean chaiseR;
    private boolean deambulateur;
    private boolean retraite;
    private boolean hopital;
    private String ancienneChute;

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public boolean isChaiseR() {
        return chaiseR;
    }

    public void setChaiseR(boolean chaiseR) {
        this.chaiseR = chaiseR;
    }

    public boolean isDeambulateur() {
        return deambulateur;
    }

    public void setDeambulateur(boolean deambulateur) {
        this.deambulateur = deambulateur;
    }

    public boolean isRetraite() {
        return retraite;
    }

    public void setRetraite(boolean retraite) {
        this.retraite = retraite;
    }

    public boolean isHopital() {
        return hopital;
    }

    public void setHopital(boolean hopital) {
        this.hopital = hopital;
    }

    public String getAncienneChute() {
        return ancienneChute;
    }

    public void setAncienneChute(String ancienneChute) {
        this.ancienneChute = ancienneChute;
    }

}
