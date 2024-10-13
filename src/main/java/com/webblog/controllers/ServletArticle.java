package com.webblog.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.webblog.utilis.validateur;

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
		String action = request.getParameter("action");
		if ("details".equals(action)) {
			showDetails(request, response);
			return;
		}

		int page = 1;
		int pageSize = 3;

		List<Auteur> auteurs = auteurServices.getAllAuteurs();

		String searchQuery = request.getParameter("search");

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}

		List<Article> articles;
		int totalArticles;

		if (searchQuery != null && !searchQuery.isEmpty()) {
			articles = articleService.searchArticlesByTitle(searchQuery, page, pageSize);
			totalArticles = articles.size();
		} else {
			articles = articleService.getAllArticle(page, pageSize);
			totalArticles = articleService.countArteicle();
		}

		int totalPages = (int) Math.ceil((double) totalArticles / pageSize);
		request.setAttribute("articles", articles);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("auteurs", auteurs);
		request.setAttribute("searchQuery", searchQuery);

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
		String titre = request.getParameter("titre").toLowerCase();
		String contenu = request.getParameter("contenu");
		String datePublicationStr = request.getParameter("datePublication");
		String statutStr = request.getParameter("statut");
		String auteurIdStr = request.getParameter("auteurId");

		List<String> errors = new ArrayList<>();

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

				if (validateur.validerArticle(article)) {
					articleService.save(article);
					LoggerMessage.info("Article ajouté avec succès : " + article);
					request.setAttribute("successMessage", "Article ajouté avec succès");
				} else {
					errors.add("Les données de l'article sont invalides");
				}
			} else {
				errors.add("Erreur : Auteur non trouvé");
			}
		} catch (Exception e) {
			LoggerMessage.debug("Erreur lors de l'ajout de l'article : " + e.getMessage());
			errors.add("Une erreur est survenue lors de l'ajout de l'article");
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
		}

		doGet(request, response);
	}

	private void updateArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> errors = new ArrayList<>();

		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String titre = request.getParameter("titre").toLowerCase();
			String contenu = request.getParameter("contenu");
			LocalDate dateCreation = LocalDate.parse(request.getParameter("dateCreation"));
			LocalDate datePublication = LocalDate.parse(request.getParameter("datePublication"));
			Status statut = Status.valueOf(request.getParameter("statut"));
			Integer auteurId = Integer.parseInt(request.getParameter("auteurId"));

			Auteur auteur = auteurServices.findById(auteurId);
			if (auteur == null) {
				errors.add("Erreur : Auteur non trouvé");
			} else {
				Article article = new Article();
				article.setId(id);
				article.setTitre(titre);
				article.setContenu(contenu);
				article.setDateCreation(dateCreation);
				article.setDatePublication(datePublication);
				article.setStatut(statut);
				article.setAuteur(auteur);

				if (validateur.validerArticle(article)) {
					articleService.update(article);
					request.setAttribute("successMessage", "Article mis à jour avec succès");
				} else {
					errors.add("Les données de l'article sont invalides");
				}
			}
		} catch (Exception e) {
			LoggerMessage.debug("Erreur lors de la mise à jour de l'article : " + e.getMessage());
			errors.add("Une erreur est survenue lors de la mise à jour de l'article");
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
		}

		doGet(request, response);
	}

	private void deleteArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> errors = new ArrayList<>();

		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			articleService.delete(id);
			request.setAttribute("successMessage", "Article supprimé avec succès");
		} catch (Exception e) {
			LoggerMessage.debug("Erreur lors de la suppression de l'article : " + e.getMessage());
			errors.add("Une erreur est survenue lors de la suppression de l'article");
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
		}

		doGet(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Implémentation à ajouter si nécessaire
	}

	private void showDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Article article = articleService.findById(id);

		if (article != null) {
			int commentCount = articleService.getCommentCountForArticle(id);
			request.setAttribute("article", article);
			request.setAttribute("commentCount", commentCount);
			System.out.println(commentCount);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/article/details.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("error", "Article non trouvé");
			doGet(request, response);
		}
	}

}