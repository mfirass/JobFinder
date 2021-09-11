package org.sid.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
@DiscriminatorValue("entreprise")
public class Entreprise extends User {
	

	
	private String Nom_entre;
	private String Date_creation;
	private String Adresse;
	private String Ville;
	private String Email;
	private String Telephone_fixe;
	private String Secteur;
	private String type_entre;
	
	
	@OneToMany(mappedBy = "entreprise", fetch = FetchType.LAZY)
	private List<Offre> offres;
	@OneToMany(mappedBy = "entreprise", fetch = FetchType.LAZY)
	private List<Article> articles;
	
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public List<Offre> getOffres() {
		return offres;
	}
	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}
	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Entreprise(String username, String password, boolean enabled, Set<Role> roles) {
		super(username, password, enabled, roles);
		// TODO Auto-generated constructor stub
	}
	public Entreprise(String username, String password, boolean enabled, Set<Role> roles,String nom_entre, String date_creation, String adresse, String ville, String email,
			String telephone_fixe, String secteur, String type) {
		this(username, password, enabled, roles);
		Nom_entre = nom_entre;
		Date_creation = date_creation;
		Adresse = adresse;
		Ville = ville;
		Email = email;
		Telephone_fixe = telephone_fixe;
		Secteur = secteur;
		type_entre = type;
	}
	public String getNom_entre() {
		return Nom_entre;
	}
	public void setNom_entre(String nom_entre) {
		Nom_entre = nom_entre;
	}
	public String getDate_creation() {
		return Date_creation;
	}
	public void setDate_creation(String date_creation) {
		Date_creation = date_creation;
	}
	public String getAdresse() {
		return Adresse;
	}
	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
	public String getVille() {
		return Ville;
	}
	public void setVille(String ville) {
		Ville = ville;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTelephone_fixe() {
		return Telephone_fixe;
	}
	public void setTelephone_fixe(String telephone_fixe) {
		Telephone_fixe = telephone_fixe;
	}
	public String getSecteur() {
		return Secteur;
	}
	public void setSecteur(String secteur) {
		Secteur = secteur;
	}

	public String getType_entre() {
		return type_entre;
	}
	public void setType_entre(String type_entre) {
		this.type_entre = type_entre;
	}
	
	
	





}
