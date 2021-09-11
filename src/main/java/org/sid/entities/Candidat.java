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
@DiscriminatorValue("candidat")
public class Candidat extends User {
	
	
	
	private String Nom;
	private String Prenom;
	private String Adresse;
	private String Email;
	private String Telephone;
	private String Statut;
	private String Infos;
	private String educationLevel;
	private int yearsOfExperience;
	
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public Candidat(String username, String password, boolean enabled, Set<Role> roles,String nom, String prenom, String adresse, String email, String telephone, String statut,
			String infos, String educationLevel, int yearsOfExperience, List<Candidature> candidatures) {
		super();
		Nom = nom;
		Prenom = prenom;
		Adresse = adresse;
		Email = email;
		Telephone = telephone;
		Statut = statut;
		Infos = infos;
		this.educationLevel = educationLevel;
		this.yearsOfExperience = yearsOfExperience;
		this.candidatures = candidatures;
	}
	@OneToMany(mappedBy = "candidat", fetch = FetchType.LAZY)
	private List<Candidature> candidatures;
	
	public Candidat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Candidature> getCandidatures() {
		return candidatures;
	}
	public void setCandidatures(List<Candidature> candidatures) {
		this.candidatures = candidatures;
	}
	public Candidat(String username, String password, boolean enabled, Set<Role> roles) {
		super(username, password, enabled, roles);
		// TODO Auto-generated constructor stub
	}
	public Candidat(String username, String password, boolean enabled, Set<Role> roles,String nom, String prenom, String adresse, String email, String telephone, String statut,
			String infos) {
		this(username, password, enabled, roles);
		Nom = nom;
		Prenom = prenom;
		Adresse = adresse;
		Email = email;
		Telephone = telephone;
		Statut = statut;
		Infos = infos;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public String getAdresse() {
		return Adresse;
	}
	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public String getStatut() {
		return Statut;
	}
	public void setStatut(String statut) {
		Statut = statut;
	}
	public String getInfos() {
		return Infos;
	}
	public void setInfos(String infos) {
		Infos = infos;
	}
	
	
	





}
