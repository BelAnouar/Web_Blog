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
<body class="bg-gray-100 ">
    <div class="container mx-auto px-4 py-8 max-w-4xl">
        <nav class="mb-8">
            <a href="${pageContext.request.contextPath}/articles" class="text-orange-600 hover:text-blue-800 transition duration-300 ease-in-out">
                &larr; Retour à la liste des articles
            </a>
        </nav>
        
        <article class="bg-white shadow-lg rounded-lg overflow-hidden ">
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
            
            <footer class="bg-gray-50 px-6 py-4 ">
                <div class="flex items-center justify-between">
                    <span class="text-sm text-gray-600">
                        ${commentCount} commentaire${commentCount > 1 ? 's' : ''}
              
                </div>
            </footer>
            <form action="<%= request.getContextPath() %>/Servletcomment/insert?id=${id}" method="post" class="mb-6 p-3">
    <div class="py-2 px-4 mb-4 bg-white rounded-lg rounded-t-lg border border-gray-200 ">
        <label for="comment" class="sr-only">Your comment</label>
        <textarea name="comment" id="comment" rows="6"
            class="form-control  px-2 w-full text-sm text-gray-900 border-0 focus:ring-0 dark:text-white "
            placeholder="Write a comment..." required></textarea>
    </div>

   
    <div class="form-group mb-4">
        <label for="status" class="form-label ">Select Status</label>
        <select name="status" id="status" class="form-select ">
            <option value="Approuvé">Approuvé</option>
            <option value="Rejeté">Rejeté</option>
        </select>
    </div>

    <button type="submit"
        class="btn btn-primary inline-flex items-center py-2.5 px-5 text-xs font-medium text-center text-white bg-blue-500 rounded-lg focus:ring-4 focus:ring-blue-200 dark:focus:ring-blue-900 hover:bg-blue-800">
        Post comment
    </button>
</form>
            
        </article>
        
        
         <article class="p-6 mt-4 mb-6 text-base bg-white rounded-lg ">
                  <footer class="flex justify-between items-center mb-2">
                      <div class="flex items-center">
                          <p class="inline-flex items-center mr-3 font-semibold text-sm text-gray-900 "><img
                                  class="mr-2 w-6 h-6 rounded-full"
                                  src="https://flowbite.com/docs/images/people/profile-picture-2.jpg"
                                  alt="Michael Gough">Michael Gough</p>
                          <p class="text-sm text-gray-600 dark:text-gray-400"><time pubdate datetime="2022-02-08"
                                  title="February 8th, 2022">Feb. 8, 2022</time></p>
                      </div>
                      <button id="dropdownComment1Button" data-dropdown-toggle="dropdownComment1"
                          class="inline-flex items-center p-2 text-sm font-medium text-center text-gray-500 bg-white rounded-lg hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-gray-50 dark:text-gray-400 dark:bg-gray-900 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
                          type="button">
                            <svg class="w-4 h-4" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 16 3">
                                <path d="M2 0a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3Zm6.041 0a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM14 0a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3Z"/>
                            </svg>
                          <span class="sr-only">Comment settings</span>
                      </button>
                      <!-- Dropdown menu -->
                      <div id="dropdownComment1"
                          class="hidden z-10 w-36 bg-white rounded divide-y divide-gray-100 shadow dark:bg-gray-700 dark:divide-gray-600">
                          <ul class="py-1 text-sm text-gray-700 dark:text-gray-200"
                              aria-labelledby="dropdownMenuIconHorizontalButton">
                              <li>
                                  <a href="#"
                                      class="block py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Edit</a>
                              </li>
                              <li>
                                  <a href="#"
                                      class="block py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Remove</a>
                              </li>
                              <li>
                                  <a href="#"
                                      class="block py-2 px-4 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Report</a>
                              </li>
                          </ul>
                      </div>
                  </footer>
                  <p>Very straight-to-point article. Really worth time reading. Thank you! But tools are just the
                      instruments for the UX designers. The knowledge of the design tools are as important as the
                      creation of the design strategy.</p>                  
              </article>
          </section>
      </article>
        
    </div>
</body>
</html>
