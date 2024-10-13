package com.webblog.controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webblog.enums.StatusC;
import com.webblog.models.Article;
import com.webblog.models.Commontaire;
import com.webblog.services.ArticleService;
import com.webblog.services.CommentaireService;


public class ServletComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentaireService commentaireService;
	private ArticleService articleService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletComments() {
        super();
        commentaireService=new CommentaireService();
        articleService = new ArticleService();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentContent = request.getParameter("comment");

	 
	    String statusValue = request.getParameter("status");
	    StatusC status = StatusC.valueOf(statusValue); 

	    int articleId = Integer.parseInt(request.getParameter("id"));
	    Article article = articleService.findById(articleId);  

	   
	    Commontaire newComment = new Commontaire();
	    newComment.setContenu(commentContent);
	    newComment.setDateCreation(LocalDate.now());
	    newComment.setStatus(status);
	    newComment.setArticle(article);

	  
	    boolean success = commentaireService.ajouterCommentaire(newComment);

	    if (success) {
	          response.sendRedirect(request.getContextPath() + "/comments");
	    } else {
	        request.setAttribute("error", "Failed to post comment. Please try again.");
	        request.getRequestDispatcher("/commentaire.jsp").forward(request, response); 
	    }
	}

}
