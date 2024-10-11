package com.webblog.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.webblog.enums.StatusC;


@Entity
@Table(name = "commentaire")
public class Commontaire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String contenu;
	private LocalDate dateCreation;
	@Enumerated
	private StatusC status;
	@Column(name = "article_id")
	@ManyToOne
	private int articleId;
	@Column(name = "auteur_id")
	@OneToOne
	private int auteurId;
	public Commontaire(int id, String contenu, LocalDate dateCreation, StatusC status, int articleId, int auteurId) {
		
		this.id = id;
		this.contenu = contenu;
		this.dateCreation = dateCreation;
		this.status = status;
		this.articleId = articleId;
		this.auteurId = auteurId;
	}
	
	public Commontaire() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public LocalDate getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}
	public StatusC getStatus() {
		return status;
	}
	public void setStatus(StatusC status) {
		this.status = status;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getAuteurId() {
		return auteurId;
	}
	public void setAuteurId(int auteurId) {
		this.auteurId = auteurId;
	}


}
