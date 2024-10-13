package com.webblog.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.webblog.enums.StatusC;


@Entity
@Table(name = "commentaire")
public class Commentaire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String contenu;
	private LocalDate dateCreation;
	@Enumerated(EnumType.STRING)
	private StatusC status;
	@ManyToOne
    @JoinColumn(name = "article_id", nullable = false) 
    private Article article;
	
	
	
	
	public Commentaire(int id, String contenu, LocalDate dateCreation, StatusC status, Article article) {
		
		this.id = id;
		this.contenu = contenu;
		this.dateCreation = dateCreation;
		this.status = status;
		this.article = article;
	}

	public Commentaire() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
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
	


}
