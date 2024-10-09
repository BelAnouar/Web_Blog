package com.webblog.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.webblog.models.Article;
import com.webblog.repositories.GenericRepository;

public class ArticleImpl implements GenericRepository<Article, Integer>{
	   private EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("webblogPU");
	    private EntityManager entityManager =entityManagerFactory.createEntityManager();

	public ArticleImpl() {
		
	}
   
	@Override
	public Boolean save(Article entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Article entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	

}
