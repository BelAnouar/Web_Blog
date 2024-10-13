package com.webblog.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webblog.enums.Status;
import com.webblog.models.Article;
import com.webblog.models.Auteur;
import com.webblog.services.ArticleService;
import com.webblog.services.AuteurService;
import com.webblog.utilis.LoggerMessage;

public class ServletArticle extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ArticleService articleService;
	private AuteurService auteurServices;

	public ServletArticle() {
		super();
		articleService = new ArticleService();
		auteurServices = new AuteurService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = 1;
		int pageSize = 3;

		List<Auteur> auteur = auteurServices.getAllAuteurs();

		String searchQuery = request.getParameter("search");
		String statusFilter = request.getParameter("statusFilter");

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}

		int totalArticles = articleService.countArteicle();

		List<Article> articles = articleService.getAllArticle(page, pageSize);
		

			int commentCount = articleService.getCommentCount();
			request.setAttribute("commentCount", commentCount);
	

		// Appliquer les filtres
		if (searchQuery != null && !searchQuery.isEmpty() || statusFilter != null && !statusFilter.isEmpty()) {
			articles = articles.stream()
					.filter(article -> {
						boolean matchesSearch = searchQuery == null || searchQuery.isEmpty() ||
								(article.getTitre() != null
										&& article.getTitre().toLowerCase().contains(searchQuery.toLowerCase()));
						boolean matchesStatus = statusFilter == null || statusFilter.isEmpty() ||
								(article.getStatut() != null && article.getStatut().toString().equals(statusFilter));
						return matchesSearch && matchesStatus;
					})
					.collect(Collectors.toList());

			totalArticles = articles.size();
		}

		int totalPages = (int) Math.ceil((double) totalArticles / pageSize);
		request.setAttribute("articles", articles);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("auteurs", auteur);
		request.setAttribute("searchQuery", searchQuery);
		request.setAttribute("statusFilter", statusFilter);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/article/articles.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action) {
			case "add":
				addArticle(request, response);
				break;
			case "update":
				updateArticle(request, response);
				break;
			case "delete":
				deleteArticle(request, response);
				break;
			default:
				doGet(request, response);
		}
	}

	private void addArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String titre = request.getParameter("titre");
		String contenu = request.getParameter("contenu");
		String datePublicationStr = request.getParameter("datePublication");
		String statutStr = request.getParameter("statut");
		String auteurIdStr = request.getParameter("auteurId");

		try {
			LocalDate datePublication = null;
			if (datePublicationStr != null && !datePublicationStr.isEmpty()) {
				datePublication = LocalDate.parse(datePublicationStr);
			}

			Status statut = Status.valueOf(statutStr);
			Integer auteurId = Integer.parseInt(auteurIdStr);

			Auteur auteur = auteurServices.findById(auteurId);
			if (auteur != null) {
				Article article = new Article();
				article.setTitre(titre);
				article.setContenu(contenu);
				article.setDateCreation(LocalDate.now());
				article.setDatePublication(datePublication);
				article.setStatut(statut);
				article.setAuteur(auteur);

				articleService.save(article);
				LoggerMessage.info("Article ajouté avec succès : " + article);
			} else {
				LoggerMessage.error("Erreur : Auteur non trouvé");
			}
		} catch (Exception e) {
			LoggerMessage.debug("Erreur lors de l'ajout de l'article : " + e.getMessage());
			e.printStackTrace();
		}

		doGet(request, response);
	}

	private void updateArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String titre = request.getParameter("titre");
		String contenu = request.getParameter("contenu");
		LocalDate dateCreation = LocalDate.parse(request.getParameter("dateCreation"));
		LocalDate datePublication = LocalDate.parse(request.getParameter("datePublication"));
		Status statut = Status.valueOf(request.getParameter("statut"));
		Integer auteurId = Integer.parseInt(request.getParameter("auteurId"));

		Auteur auteur = auteurServices.findById(auteurId);
		Article article = new Article();
		article.setId(id);
		article.setTitre(titre);
		article.setContenu(contenu);
		article.setDateCreation(dateCreation);
		article.setDatePublication(datePublication);
		article.setStatut(statut);
		article.setAuteur(auteur);
		articleService.update(article);

		doGet(request, response);
	}

	private void deleteArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		articleService.delete(id);

		doGet(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Implémentation à ajouter si nécessaire
	}

}
