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
    
    private boolean chaiseRoulante;
    private boolean fracture;
    private boolean deambulateur;
    private Integer identifiantAlert;
    

    public MonitoringEvent() {}

    public MonitoringEvent(String idClient, String ancienneChute, Integer identifiantAlert) {
        this.idClient = idClient;
        this.ancienneChute = ancienneChute;
        this.identifiantAlert = identifiantAlert;
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
		result = prime * result + ((ancienneChute == null) ? 0 : ancienneChute.hashCode());
		result = prime * result + (chaiseRoulante ? 1231 : 1237);
		result = prime * result + (deambulateur ? 1231 : 1237);
		result = prime * result + (fracture ? 1231 : 1237);
		result = prime * result + ((idClient == null) ? 0 : idClient.hashCode());
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
		MonitoringEvent other = (MonitoringEvent) obj;
		if (ancienneChute == null) {
			if (other.ancienneChute != null)
				return false;
		} else if (!ancienneChute.equals(other.ancienneChute))
			return false;
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
		if (identifiantAlert == null) {
			if (other.identifiantAlert != null)
				return false;
		} else if (!identifiantAlert.equals(other.identifiantAlert))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MonitoringEvent [idClient=" + idClient + ", ancienneChute=" + ancienneChute + ", chaiseRoulante="
				+ chaiseRoulante + ", fracture=" + fracture + ", deambulateur=" + deambulateur + ", identifiantAlert="
				+ identifiantAlert + "]";
	}
   
}
