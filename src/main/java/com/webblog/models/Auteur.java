package com.webblog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="auteur")
public class Auteur {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	    private Integer id; 
   
	 public Auteur() {
		// TODO Auto-generated constructor stub
	}
	 
	 public Integer getId() {
		return id;
	}
	 public void setId(Integer id) {
		this.id = id;
	}
	 
}
