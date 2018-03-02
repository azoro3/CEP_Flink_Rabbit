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
public class Alert {
    private String idClient;
    private Integer identifiant;
    
	public Alert() {
	}

	public Alert(String idClient, Integer identifiant) {
		this.idClient = idClient;
		this.identifiant = identifiant;
	}
	
	public String getIdClient() {
		return idClient;
	}
	
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	
	public Integer getIdentifiant() {
		return identifiant;
	}
	
	public void setIdentifiant(Integer identifiant) {
		this.identifiant = identifiant;
	}

	@Override
	public String toString() {
		return "Alert [idClient=" + idClient + ", identifiant=" + identifiant + "]";
	}
	
	
        
}
