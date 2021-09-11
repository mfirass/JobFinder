package org.sid.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Article implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	private String description;
	private String date_article;
	@ManyToOne
	@JoinColumn(name = "id_entreprise")
	private Entreprise entreprise;
	
	
	
	public Article() {
		super();
	}
	
	public Article(Long id, String titre, String description, String date_article, Entreprise entreprise) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.date_article = date_article;
		this.entreprise = entreprise;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
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
	public String getDate_article() {
		return date_article;
	}
	public void setDate_article(String date_article) {
		this.date_article = date_article;
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	
	
	
}
