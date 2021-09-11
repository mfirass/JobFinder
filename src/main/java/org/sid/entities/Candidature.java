package org.sid.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
@Entity
public class Candidature implements Serializable {
	
	@Id @GeneratedValue
	private Long id;
		
	public String Etat_cand;
	
	@ManyToOne
	@JoinColumn(name = "id_candidat")
	private Candidat candidat;
	@ManyToOne
	@JoinColumn(name = "id_offre")
	private Offre offre;
	public Candidature(Long id, String etat_cand, Candidat candidat, Offre offre) {
		super();
		this.id = id;
		Etat_cand = etat_cand;
		this.candidat = candidat;
		this.offre = offre;
	}
	
	
	public Candidature() {
		super();
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEtat_cand() {
		return Etat_cand;
	}
	public void setEtat_cand(String etat_cand) {
		Etat_cand = etat_cand;
	}
	public Candidat getCandidat() {
		return candidat;
	}
	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}
	public Offre getOffre() {
		return offre;
	}
	public void setOffre(Offre offre) {
		this.offre = offre;
	}
	
	
	
	





}
