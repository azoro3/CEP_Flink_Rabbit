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
    
    public boolean chaiseRoulante;
    public boolean fracture;
    public boolean deambulateur;
    public Integer identifiantAlert;

    

    public FallWarning(String idClient, int idNiveauUrgence, Integer identifiantAlert) {
		this.idClient = idClient;
		this.idNiveauUrgence = idNiveauUrgence;
		this.identifiantAlert = identifiantAlert;
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
    
    

    public boolean isChaiseRoulante() {
		return chaiseRoulante;
	}

	public void setChaiseRoulante(boolean chaiseRoulante) {
		this.chaiseRoulante = chaiseRoulante;
	}

	public boolean isFracture() {
		return fracture;
	}

	public void setFracture(boolean fracture) {
		this.fracture = fracture;
	}

	public boolean isDeambulateur() {
		return deambulateur;
	}

	public void setDeambulateur(boolean deambulateur) {
		this.deambulateur = deambulateur;
	}

	public Integer getIdentifiantAlert() {
		return identifiantAlert;
	}

	public void setIdentifiantAlert(Integer identifiantAlert) {
		this.identifiantAlert = identifiantAlert;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (chaiseRoulante ? 1231 : 1237);
		result = prime * result + (deambulateur ? 1231 : 1237);
		result = prime * result + (fracture ? 1231 : 1237);
		result = prime * result + ((idClient == null) ? 0 : idClient.hashCode());
		result = prime * result + idNiveauUrgence;
		result = prime * result + ((identifiantAlert == null) ? 0 : identifiantAlert.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FallWarning other = (FallWarning) obj;
		if (chaiseRoulante != other.chaiseRoulante)
			return false;
		if (deambulateur != other.deambulateur)
			return false;
		if (fracture != other.fracture)
			return false;
		if (idClient == null) {
			if (other.idClient != null)
				return false;
		} else if (!idClient.equals(other.idClient))
			return false;
		if (idNiveauUrgence != other.idNiveauUrgence)
			return false;
		if (identifiantAlert == null) {
			if (other.identifiantAlert != null)
				return false;
		} else if (!identifiantAlert.equals(other.identifiantAlert))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FallWarning [idClient=" + idClient + ", idNiveauUrgence=" + idNiveauUrgence + ", chaiseRoulante="
				+ chaiseRoulante + ", fracture=" + fracture + ", deambulateur=" + deambulateur + ", identifiantAlert="
				+ identifiantAlert + "]";
	}
	
}
