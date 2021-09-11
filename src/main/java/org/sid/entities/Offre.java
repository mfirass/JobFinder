package org.sid.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
@Entity
public class Offre implements Serializable {
	
	@Id @GeneratedValue
	private Long id;
	private String titre;
	private String description;
	private String ville;
	private String image;
	private String categorie;
	
	private String date_offre;
	
	public String getDate_offre() {
		return date_offre;
	}


	public void setDate_offre(String date_offre) {
		this.date_offre = date_offre;
	}



	private double salaire;
	private String requirement;

	public String getRequirement() {
		return requirement;
	}



	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}



	@ManyToOne
	@JoinColumn(name = "id_entreprise")
	private Entreprise entreprise;
	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy = "offre", fetch = FetchType.LAZY)
	private List<Candidature> candidatures;
	
	
	public Offre(String titre, String description, String ville, String image,
			String categorie, double salaire,String requirement) {
		super();
		this.titre = titre;
		this.description = description;
		
		this.ville = ville;
		this.image = image;
		
		this.categorie = categorie;
		this.salaire=salaire;
		this.requirement = requirement;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Entreprise getEntreprise() {
		return entreprise;
	}



	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}



	public List<Candidature> getCandidatures() {
		return candidatures;
	}



	public void setCandidatures(List<Candidature> candidatures) {
		this.candidatures = candidatures;
	}



	public String getTitre() {
		return titre;
	}

	public double getSalaire() {
		return salaire;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	
	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	



	



	public String getCategorie() {
		return categorie;
	}



	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}



	public Offre() {
		super();
		
	}
}
