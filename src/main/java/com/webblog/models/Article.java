package com.webblog.models;

import java.time.LocalDate;
import javax.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.webblog.enums.Status;

@Entity
@Table(name = "article")
public class Article {

    public Article() {
        // Default constructor
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Le titre ne peut pas être nul")
    @Size(min = 5, max = 100, message = "Le titre doit contenir entre 5 et 100 caractères")
    @Column(name = "titre", nullable = false)
    private String titre;

    @NotNull(message = "Le contenu ne peut pas être nul")
    @Column(name = "contenu", columnDefinition = "TEXT", nullable = false)
    private String contenu;

    @NotNull(message = "La date de création est obligatoire")
    @Column(name = "dateCreation", nullable = false)
    private LocalDate dateCreation;

    @Column(name = "datePublication")
    private LocalDate datePublication;

    @NotNull(message = "Le statut est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private Status statut;

   
    @JoinColumn(name = "auteur_id", nullable = false)
    @NotNull(message = "L'auteur est obligatoire")
    @ManyToOne
    private Auteur auteur;

    // Getters and Setters

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
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

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatut() {
        return statut;
    }

    public void setStatut(Status statut) {
        this.statut = statut;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", contenu='" + contenu + '\'' +
                ", dateCreation=" + dateCreation +
                ", datePublication=" + datePublication +
                ", statut=" + statut +
             // ", auteur=" + (auteur != null ? auteur.getName() : "null") +
                '}';
    }
}
