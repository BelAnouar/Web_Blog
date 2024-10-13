package com.webblog.controllers;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webblog.enums.Role;
import com.webblog.models.Auteur;
import com.webblog.services.AuteurService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class AuteurServlet extends HttpServlet {
    private AuteurService auteurService = new AuteurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int pageSize = 5; 

        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }

        List<Auteur> auteurs = auteurService.getAllAuteurs(page, pageSize);
        request.setAttribute("auteurs", auteurs);
        request.setAttribute("currentPage", page);

        request.getRequestDispatcher("/auteur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            String auteurIdParam = request.getParameter("auteurId");
            if (auteurIdParam != null && !auteurIdParam.isEmpty()) {
                Integer auteurId = Integer.parseInt(auteurIdParam);

                boolean success = auteurService.delete(auteurId);

                if (success) {
                    response.sendRedirect("auteur");
                } else {
                    request.setAttribute("errorMessage", "Error deleting the author.");
                    request.getRequestDispatcher("/auteur.jsp").forward(request, response);
                }
            }
        } 
        else if ("update".equals(action)) {
            String auteurIdParam = request.getParameter("auteurId");
            if (auteurIdParam != null && !auteurIdParam.isEmpty()) {
                Integer auteurId = Integer.parseInt(auteurIdParam);

                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String email = request.getParameter("email");
                String dateNaissance = request.getParameter("dateNaissance");
                String role = request.getParameter("role");

                Auteur auteur = auteurService.findById(auteurId);
                if (auteur != null) {
                    auteur.setNom(nom);
                    auteur.setPrenom(prenom);
                    auteur.setEmail(email);
                    auteur.setDateNaissance(LocalDate.parse(dateNaissance));
                    auteur.setRole(Role.valueOf(role));

                    boolean success = auteurService.update(auteur);
                    if (success) {
                        response.sendRedirect("auteur");
                    } else {
                        request.setAttribute("errorMessage", "Error updating the author.");
                        request.getRequestDispatcher("/auteur.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("errorMessage", "Author not found.");
                    request.getRequestDispatcher("/auteur.jsp").forward(request, response);
                }
            }
        } 
        else {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String dateNaissance = request.getParameter("dateNaissance");
            String role = request.getParameter("role");

            Auteur auteur = new Auteur();
            auteur.setNom(nom);
            auteur.setPrenom(prenom);
            auteur.setEmail(email);
            auteur.setDateNaissance(LocalDate.parse(dateNaissance));
            auteur.setRole(Role.valueOf(role));

            boolean success = auteurService.save(auteur);
            if (success) {
                response.sendRedirect("auteur");
            } else {
                request.setAttribute("errorMessage", "Error adding the author.");
                request.getRequestDispatcher("/auteur.jsp").forward(request, response);
            }
        }
    }

}
	
