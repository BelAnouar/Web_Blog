package com.webblog.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webblog.models.Article;
import com.webblog.services.ArticleService;



public class Servletarticle extends HttpServlet {
       
 
	private static final long serialVersionUID = 1L;

    public ArticleService articleService;
	public Servletarticle() {
        super();
        articleService=new ArticleService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int page=1;
		int pagSize=5;
		
        String searchQuery = request.getParameter("search");

		if(request.getParameter("page")!=null) {
			page =Integer.parseInt(request.getParameter("page"));
			
		}
		if(request.getParameter("pagSize")!=null) {
			pagSize =Integer.parseInt(request.getParameter("pagSize"));
			
		}
		
		int totalArticle=articleService.countArteicle();
		List<Article> articles= articleService.getAllArticle(page, pagSize);
		   if (searchQuery != null && !searchQuery.isEmpty()) {
	        	  String finalSearchQuery = searchQuery.toLowerCase();
	        	  
	        	  articles.stream()
	        	  .filter(article->article.getTitre()!=null &&article.getTitre().toLowerCase().contains(finalSearchQuery))
	        	  .collect(Collectors.toList());
		   }
		
		int totalPages= (int) Math.ceil((double) totalArticle/pagSize);
				  request.setAttribute("articles",articles);
		            request.setAttribute("currentPage", page);
		            request.setAttribute("totalPages", totalPages);
		       
		            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/articles.jsp");
		            dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
