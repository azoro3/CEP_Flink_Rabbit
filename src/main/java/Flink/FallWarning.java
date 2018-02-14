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
public class FallWarning {
    public String idClient;
    public int idNiveauUrgence;

    public FallWarning(String idClient, int idNiveauUrgence) {
        this.idClient = idClient;
        this.idNiveauUrgence = idNiveauUrgence;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public int getIdNiveauUrgence() {
        return idNiveauUrgence;
    }

    public void setIdNiveauUrgence(int idNiveauUrgence) {
        this.idNiveauUrgence = idNiveauUrgence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FallWarning)) return false;
        FallWarning that = (FallWarning) o;
        return idNiveauUrgence == that.idNiveauUrgence &&
                Objects.equals(idClient, that.idClient);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idClient, idNiveauUrgence);
    }

    @Override
    public String toString() {
        return "FallWarning{" +
                "idClient='" + idClient + '\'' +
                ", idNiveauUrgence=" + idNiveauUrgence +
                '}';
    }
}
