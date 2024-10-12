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

	private ArticleService articleService ;
    public Servletarticle() {
        super();
        articleService = new ArticleService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        System.out.println("aaa");
	/*	int page = 1;
		int pageSize = 5;

		String searchQuery = request.getParameter("search");

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));

		}
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));

		}

		int totalArticles = articleService.countArteicle();
		
		List<Article> articles = articleService.getAllArticle(page, pageSize);

	articles.forEach(article -> System.out.println(article.getTitre()));
		
		if (searchQuery != null && !searchQuery.isEmpty()) {
			String finalSearchQuery = searchQuery.toLowerCase();
			articles = articles.stream()
					.filter(article -> article.getTitre() != null
							&& article.getTitre().toLowerCase().contains(finalSearchQuery))
					.collect(Collectors.toList());
		}

		int totalPages = (int) Math.ceil((double) totalArticles / pageSize);
		request.setAttribute("articles", articles);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);

	RequestDispatcher dispatcher = request.getRequestDispatcher("/article/articles.jsp");
		dispatcher.forward(request, response);*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Implémentation à ajouter si nécessaire
	}

}
