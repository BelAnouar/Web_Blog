package com.webblog.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "article")
public class Article {
	
	 public Article() {
			// TODO Auto-generated constructor stub
		}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "contenu", columnDefinition = "TEXT", nullable = false)
    private String contenu;

    @Column(name = "dateCreation", nullable = false)
    private LocalDate dateCreation;

    @Column(name = "datePublication")
    private LocalDate datePublication;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private Statut statut;

    @ManyToOne
    @JoinColumn(name = "auteur_id", nullable = false)
    private Auteur auteur;

    public Auteur getAuteur() {
		return auteur;
	}
    public String getContenu() {
		return contenu;
	}
    public LocalDate getDateCreation() {
		return dateCreation;
	}
    public int getId() {
		return id;
	}
    public LocalDate getDatePublication() {
		return datePublication;
	}
    public Statut getStatut() {
		return statut;
	}
    public String getTitre() {
		return titre;
	}
    public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}
    
    public void setContenu(String contenu) {
		this.contenu = contenu;
	}
    public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}
    public void setDatePublication(LocalDate datePublication) {
		this.datePublication = datePublication;
	}
    public void setId(int id) {
		this.id = id;
	}
    public void setStatut(Statut statut) {
		this.statut = statut;
	}
    public void setTitre(String titre) {
		this.titre = titre;
	}
   
}

public enum Statut {
    Brouillon, Publié
}
