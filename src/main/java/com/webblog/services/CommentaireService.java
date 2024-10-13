package com.webblog.services;


import com.webblog.models.Commontaire;
import com.webblog.repositories.impl.CommantaireImpl;

public class CommentaireService {
	private final CommantaireImpl CommantaireImpl;

	public CommentaireService() {
		this.CommantaireImpl = new CommantaireImpl();
	}

	public boolean ajouterCommentaire(Commontaire commentaire) {

		return CommantaireImpl.save(commentaire);
	}
}
