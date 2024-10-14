package com.webblog.controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.webblog.enums.StatusC;
import com.webblog.models.Article;
import com.webblog.models.Commentaire;
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
		commentaireService = new CommentaireService();
		articleService = new ArticleService();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getRequestURI()
				.substring(request.getContextPath().length() + request.getServletPath().length());
		System.out.println(action);
		if ("insert".equals(action)) {
			createComments(request, response);
		} else if ("/get".equals(action)) {
			getComments(request, response);
		}else if ("/update".equals(action)) {
			updateComments(request, response);
		}else if ("/delete".equals(action)) {
			deleteComments(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
		}
	}

	private void createComments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			response.sendRedirect(request.getContextPath() + "/articles?action=details&id=" + articleId);
		} else {
			request.setAttribute("error", "Failed to post comment. Please try again.");
			request.getRequestDispatcher("/commentaire.jsp").forward(request, response);
		}
	}

	private void getComments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{
			try {
				int commentsId = Integer.parseInt(request.getParameter("commentId"));
				Commentaire comments = commentaireService.getCommenteByid(commentsId);
				System.out.println(comments);
				ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String commment = mapper.writeValueAsString(comments);

				ServletOutputStream servletOutputStream = response.getOutputStream();
				servletOutputStream.write(commment.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to fetch comments.");
			}
		}

	}
	
	private void updateComments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String commentContent = request.getParameter("comment");

		String statusValue = request.getParameter("status");
		StatusC status = StatusC.valueOf(statusValue);

		int articleId = Integer.parseInt(request.getParameter("id"));
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		Article article = articleService.findById(articleId);
		Commontaire newComment = new Commontaire();
		newComment.setId(commentId);
		newComment.setContenu(commentContent);
		newComment.setDateCreation(LocalDate.now());
		newComment.setStatus(status);
		newComment.setArticle(article);
		
		
		boolean success = commentaireService.modifierCommentaire(newComment);

		if (success) {
			response.sendRedirect(request.getContextPath() + "/articles?action=details&id=" + articleId);
		} else {
			request.setAttribute("error", "Failed to post comment. Please try again.");
			request.getRequestDispatcher("/commentaire.jsp").forward(request, response);
		}
		
		
		
		
	}
	
	private void deleteComments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int articleId = Integer.parseInt(request.getParameter("id"));
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		boolean success = commentaireService.deleteCommentaire(commentId);
System.out.println(articleId);
System.out.println(success);
		if (success) {
			response.sendRedirect(request.getContextPath() + "/articles?action=details&id=" + articleId);
		} else {
			request.setAttribute("error", "Failed to post comment. Please try again.");
			request.getRequestDispatcher("/commentaire.jsp").forward(request, response);
		}
	}
}
