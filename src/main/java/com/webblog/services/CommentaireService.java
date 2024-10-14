package com.webblog.services;


import java.util.List;

import com.webblog.models.Commentaire;
import com.webblog.models.Commontaire;
import com.webblog.repositories.impl.CommantaireImpl;

public class CommentaireService {
	private final CommantaireImpl CommantaireImpl;

	public CommentaireService() {
		this.CommantaireImpl = new CommantaireImpl();
	}

	public boolean ajouterCommentaire(Commontaire newComment) {

		return CommantaireImpl.save(newComment);
	}
	
	public List<Commentaire> getCommentesByid(int id){
		return CommantaireImpl.getById(id);
	}
	
	public Commentaire getCommenteByid(int id){
		return CommantaireImpl.getComment(id);
	}
	
	public boolean modifierCommentaire(Commontaire newComment) {
		return CommantaireImpl.update(newComment);
	}
	public boolean deleteCommentaire(int idComment) {
		return CommantaireImpl.delete(idComment);
	}
	
	
}
