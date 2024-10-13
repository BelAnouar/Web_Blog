package com.webblog.controllers;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webblog.services.ArticleService;



public class Servletarticle extends HttpServlet {
       
 
	private static final long serialVersionUID = 1L;

    public ArticleService articleService;
	public Servletarticle() {
        super();
        articleService=new ArticleService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   try {
	            EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
	            System.out.println("EntityManagerFactory created: " + (emf != null));
	            emf.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
