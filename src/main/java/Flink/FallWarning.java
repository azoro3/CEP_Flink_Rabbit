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
public class FallWarning {
    public String idClient;
    public int idNiveauUrgence;

    public FallWarning(String idClient, int idNiveauUrgence) {
        this.idClient = idClient;
        this.idNiveauUrgence = idNiveauUrgence;
    }
}
