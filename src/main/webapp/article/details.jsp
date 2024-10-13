<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Détails de l'Article - ${article.titre}</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
        }
    </style>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto px-4 py-8 max-w-4xl">
        <nav class="mb-8">
            <a href="${pageContext.request.contextPath}/articles" class="text-orange-600 hover:text-blue-800 transition duration-300 ease-in-out">
                &larr; Retour à la liste des articles
            </a>
        </nav>
        
        <article class="bg-white shadow-lg rounded-lg overflow-hidden">
            <header class="bg-gradient-to-r from-orange-500 text-white to-purple-200  p-6">
                <h1 class="text-3xl font-bold mb-2">${article.titre}</h1>
                <div class="flex items-center text-sm">
                    <span class="mr-4">Par ${article.auteur.prenom} ${article.auteur.nom}</span>
                    <span>Publié le ${article.datePublication}</span>
                </div>
            </header>
            
            <div class="p-6">
                <p class="text-orange-500 leading-relaxed mb-6">${article.contenu}</p>
                
                <div class="border-t border-gray-200 pt-4">
                    <div class="flex justify-between items-center text-sm text-orange-500">
                        <span>Créé le ${article.dateCreation}</span>
                        <span class="px-3 py-1 bg-orange-600 text-white rounded-full">${article.statut}</span>
                    </div>
                </div>
            </div>
            
            <footer class="bg-gray-50 px-6 py-4">
                <div class="flex items-center justify-between">
                    <span class="text-sm text-gray-600">
                        ${commentCount} commentaire${commentCount > 1 ? 's' : ''}
              
                </div>
            </footer>
        </article>
    </div>
</body>
</html>
