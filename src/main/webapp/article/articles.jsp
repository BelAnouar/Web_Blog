<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Articles</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<header class='bg-white font-[sans-serif] mb-20  tracking-wide relative z-50 '>
  <div id="collapseMenu"
    class='bg-[#213343] max-lg:hidden lg:!flex lg:items-center lg:justify-between lg:gap-y-4 lg:gap-x-10 lg:px-10 lg:py-2 max-lg:before:fixed max-lg:before:bg-black max-lg:before:opacity-40 max-lg:before:inset-0 max-lg:before:z-50'>
    <button id="toggleClose" class='lg:hidden fixed top-2 right-4 z-[100] rounded-full bg-white p-3'>
      <svg xmlns="http://www.w3.org/2000/svg" class="w-4 fill-black" viewBox="0 0 320.591 320.591">
        <path
          d="M30.391 318.583a30.37 30.37 0 0 1-21.56-7.288c-11.774-11.844-11.774-30.973 0-42.817L266.643 10.665c12.246-11.459 31.462-10.822 42.921 1.424 10.362 11.074 10.966 28.095 1.414 39.875L51.647 311.295a30.366 30.366 0 0 1-21.256 7.288z"
          data-original="#000000"></path>
        <path
          d="M287.9 318.583a30.37 30.37 0 0 1-21.257-8.806L8.83 51.963C-2.078 39.225-.595 20.055 12.143 9.146c11.369-9.736 28.136-9.736 39.504 0l259.331 257.813c12.243 11.462 12.876 30.679 1.414 42.922-.456.487-.927.958-1.414 1.414a30.368 30.368 0 0 1-23.078 7.288z"
          data-original="#000000"></path>
      </svg>
    </button>

    <ul
      class='lg:!flex lg:gap-x-10 py-4  max-lg:space-y-3 max-lg:fixed max-lg:bg-[#151d20] max-lg:w-2/3 max-lg:min-w-[300px] max-lg:top-0 max-lg:left-0 max-lg:px-10 max-lg:py-4 max-lg:h-full max-lg:shadow-md max-lg:overflow-auto z-50'>
      <li class='mb-6 hidden max-lg:block'></li>
      <li class='max-lg:border-b max-lg:py-4'><a href='#'
          class='hover:text-[#FFA726] text-white text-md block'>Auteurs</a></li>
      <li class='max-lg:border-b max-lg:py-4'><a href='javascript:void(0)'
          class='hover:text-[#FFA726] text-[#FFA726]  text-md block'>Articles</a></li>
      <li class='max-lg:border-b max-lg:py-4'><a href='javascript:void(0)'
          class='hover:text-[#FFA726] text-white text-md block'>Commentaires</a></li>
    </ul>
  </div>
</header>

<div class="w-full px-4 sm:w-[90%] mx-auto" >
  <div class="flex flex-col sm:flex-row justify-between items-center mb-4 space-y-4 sm:space-y-0">
    <button type="button"
      class="w-full sm:w-auto px-5 py-2.5 rounded-lg text-sm tracking-wider font-medium border border-orange-600 outline-none bg-transparent hover:bg-gray-800 text-[#FFA726] hover:text-[#FFF] transition-all duration-300"
      onclick="toggleModal(true)">
      Ajouter un Article
    </button>

    <form action="${pageContext.request.contextPath}/articles" method="GET" class="flex flex-col sm:flex-row items-center w-full sm:w-auto space-y-2 sm:space-y-0 sm:space-x-2">
      <input type="text" name="search" value="${searchQuery}" placeholder="Rechercher un article..." 
        class="w-full sm:w-auto px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-600 focus:border-transparent"
      >
      <!--  
      <select name="statusFilter" 
        class="w-full sm:w-auto px-3 py-2 text-sm leading-tight text-gray-700 border border-gray-300 rounded-lg focus:outline-none focus:border-orange-600">
        <option value="">Tous les statuts</option>
        <option value="Brouillon" ${statusFilter == 'Brouillon' ? 'selected' : ''}>Brouillon</option>
        <option value="Publie" ${statusFilter == 'Publie' ? 'selected' : ''}>Publié</option>
      </select>-->
      <button type="submit" 
        class="w-full sm:w-auto px-4 py-2 bg-orange-600 text-white rounded-lg hover:bg-orange-700 focus:outline-none focus:ring-2 focus:ring-orange-600 focus:ring-offset-2">
        Rechercher
      </button>
    </form>
    <a href="${pageContext.request.contextPath}/articles" 
      class="w-full sm:w-auto px-4 py-2 bg-gray-200 text-gray-700 rounded-lg hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-gray-400 focus:ring-offset-2 text-center">
      Tous les articles
    </a>
  </div>

  <!-- Modal Ajouter -->
  <div id="myModal" class="fixed inset-0 flex items-center justify-center bg-gray-900 bg-opacity-50 hidden">
    <div class="bg-white p-2 rounded-lg shadow-lg w-full max-w-2xl mt-1">
      <h2 class="text-xl font-semibold mb-4">Ajouter un Article</h2>

      <form action="${pageContext.request.contextPath}/articles" method="POST">
        <input type="hidden" name="action" value="add">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
          <!-- Article Title -->
          <div class="flex flex-col col-span-2 my-1">
            <label class="block text-gray-700 text-sm font-bold mb-1">Titre:</label>
            <input
            name="titre"
              class="w-full px-3 py-2 text-sm leading-tight text-gray-700 border border-gray-300 rounded-lg focus:outline-none focus:border-orange-600"
              type="text" placeholder="Entrez le titre de l'article">
          </div>

          <!-- Article Content (Full Width) -->
          <div class="flex flex-col col-span-2 my-1">
            <label class="block text-gray-700 text-sm font-bold mb-1">Contenu:</label>
            <textarea
             name="contenu"
              class="w-full px-3 py-2 text-sm leading-tight text-gray-700 border border-gray-300 rounded-lg focus:outline-none focus:border-orange-600"
              rows="3" placeholder="Entrez le contenu"></textarea>
          </div>

          <!-- Date de Publication -->
          <div class="flex flex-col my-1">
            <label class="block text-gray-700 text-sm font-bold mb-1">Date Publication:</label>
            <input
            name="datePublication"
              class="w-full px-3 py-2 text-sm leading-tight text-gray-700 border border-gray-300 rounded-lg focus:outline-none focus:border-orange-600"
              type="date">
          </div>

          <!-- Status -->
          <div class="flex flex-col my-1">
            <label class="block text-gray-700 text-sm font-bold mb-1">Status:</label>
            <select 
            name="statut"
              class="w-full px-3 py-2 text-sm leading-tight text-gray-700 border border-gray-300 rounded-lg focus:outline-none focus:border-orange-600">
              <option value="Brouillon">Brouillon</option>
              <option value="Publie">Publié</option>
            </select>
          </div>

          <!-- Auteur Selection -->
          <div class="flex flex-col my-1">
            <label class="block text-gray-700 text-sm font-bold mb-1">Auteur:</label>
            <select
              class="w-full px-3 py-2 text-sm leading-tight text-gray-700 border border-gray-300 rounded-lg focus:outline-none focus:border-orange-600"
              name="auteurId">
              <c:forEach var="auteur" items="${auteurs}">
                <option value="${auteur.id}">${auteur.nom}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <!-- Modal Actions -->
        <div class="flex justify-end mt-4">
          <button type="button" onclick="toggleModal(false)"
            class="px-4 py-2 bg-gray-500 text-white rounded-lg mr-2 hover:bg-gray-600">
            Annuler
          </button>
          <button type="submit"
            class="px-4 py-2 bg-orange-600 text-white rounded-lg hover:bg-orange-700">
            Ajouter
          </button>
        </div>
      </form>
    </div>
  </div>

  <div class="overflow-x-auto">
    <table class=" w-full bg-white">
      <thead class="bg-gray-800 whitespace-nowrap">
        <tr>
          <th class="p-4 text-left text-sm font-medium text-white">Titre</th>
          <th class="p-4 text-left text-sm font-medium text-white  sm:table-cell">Contenu</th>
          <th class="p-4 text-left text-sm font-medium text-white  md:table-cell">Date Creation</th>
          <th class="p-4 text-left text-sm font-medium text-white  md:table-cell">Date Publication</th>
          <th class="p-4 text-left text-sm font-medium text-white">Status</th>
          <th class="p-4 text-left text-sm font-medium text-white  sm:table-cell">Auteur</th>
          <th class="p-4 text-left text-sm font-medium text-white">Actions</th>
        </tr>
      </thead>

      <tbody class="whitespace-nowrap ">
       <c:forEach var="article" items="${articles}">
          <tr class="even:bg-blue-50">
            <td class="p-4 text-sm text-black">${article.titre}</td>
            <td class="p-4 text-sm text-black  sm:table-cell">${article.contenu}</td>
            <td class="p-4 text-sm text-black  md:table-cell">${article.dateCreation}</td>
            <td class="p-4 text-sm text-black  md:table-cell">${article.datePublication}</td>
            <td class="p-4 text-sm text-black">${article.statut}</td>
            <td class="p-4 text-sm text-black  sm:table-cell">${article.auteur.nom}</td>
            <td class="p-4">
              <button 
                class="mr-4 p-2 bg-blue-500 text-white rounded-md shadow hover:bg-blue-600 transition duration-200" 
                title="Edit" 
                onclick="openUpdateModal(this)" 
                data-article='{"id": "${article.id}", "titre": "${article.titre}", "contenu": "${article.contenu}", "dateCreation": "${article.dateCreation}", "datePublication": "${article.datePublication}", "statut": "${article.statut}", "auteurId": "${article.auteur.id}"}'>
                <svg xmlns="http://www.w3.org/2000/svg" class="w-5 h-5 fill-current" viewBox="0 0 348.882 348.882">
                  <path d="m333.988 11.758-.42-.383A43.363 43.363 0 0 0 304.258 0a43.579 43.579 0 0 0-32.104 14.153L116.803 184.231a14.993 14.993 0 0 0-3.154 5.37l-18.267 54.762c-2.112 6.331-1.052 13.333 2.835 18.729 3.918 5.438 10.23 8.685 16.886 8.685h.001c2.879 0 5.693-.592 8.362-1.76l52.89-23.138a14.985 14.985 0 0 0 5.063-3.626L336.771 73.176c16.166-17.697 14.919-45.247-2.783-61.418zM130.381 234.247l10.719-32.134.904-.99 20.316 18.556-.904.99-31.035 13.578zm184.24-181.304L182.553 197.53l-20.316-18.556L294.305 34.386c2.583-2.828 6.118-4.386 9.954-4.386 3.365 0 6.588 1.252 9.082 3.53l.419.383c5.484 5.009 5.87 13.546.861 19.03z" />
                  <path d="M303.85 138.388c-8.284 0-15 6.716-15 15v127.347c0 21.034-17.113 38.147-38.147 38.147H68.904c-21.035 0-38.147-17.113-38.147-38.147V100.413c0-21.034 17.113-38.147 38.147-38.147h131.587c8.284 0 15-6.716 15-15s-6.716-15-15-15H68.904C31.327 32.266.757 62.837.757 100.413v180.321c0 37.576 30.571 68.147 68.147 68.147h181.798c37.576 0 68.147-30.571 68.147-68.147V153.388c.001-8.284-6.715-15-14.999-15z" />
                </svg>
              </button>
              <form action="" method="POST" style="display: inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="${article.id}">
                <button type="submit" class="mr-4 p-2 bg-red-500 text-white rounded-md shadow hover:bg-blue-600 transition duration-200" title="Delete">
                  <svg xmlns="http://www.w3.org/2000/svg" class="w-5 fill-current hover:fill-red-700" viewBox="0 0 24 24">
                    <path d="M19 7a1 1 0 0 0-1 1v11.191A1.92 1.92 0 0 1 15.99 21H8.01A1.92 1.92 0 0 1 6 19.191V8a1 1 0 0 0-2 0v11.191A3.918 3.918 0 0 0 8.01 23h7.98A3.918 3.918 0 0 0 20 19.191V8a1 1 0 0 0-1-1Zm1-3h-4V2a1 1 0 0 0-1-1H9a1 1 0 0 0-1 1v2H4a1 1 0 0 0 0 2h16a1 1 0 0 0 0-2ZM10 4V3h4v1Z" data-original="#000000" />
                    <path d="M11 17v-7a1 1 0 0 0-2 0v7a1 1 0 0 0 2 0Zm4 0v-7a1 1 0 0 0-2 0v7a1 1 0 0 0 2 0Z" data-original="#000000" />
                  </svg>
                </button>
              </form>
            </td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
  </div>

  <!-- Ajoutez ce code après la fermeture de la balise </table> -->
  <div class="flex justify-center mt-4">
    <nav class="inline-flex rounded-md shadow">
      <c:if test="${currentPage > 1}">
        <a href="${pageContext.request.contextPath}/articles?page=${currentPage - 1}&search=${searchQuery}&statusFilter=${statusFilter}" class="px-3 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-l-md hover:bg-gray-50">
          Précédent
        </a>
      </c:if>
      
      <c:forEach begin="1" end="${totalPages}" var="i">
        <a href="${pageContext.request.contextPath}/articles?page=${i}&search=${searchQuery}&statusFilter=${statusFilter}" class="px-3 py-2 text-sm font-medium ${currentPage == i ? 'text-orange-600 bg-orange-50' : 'text-gray-700 bg-white'} border border-gray-300 hover:bg-gray-50">
          ${i}
        </a>
      </c:forEach>
      
      <c:if test="${currentPage < totalPages}">
        <a href="${pageContext.request.contextPath}/articles?page=${currentPage + 1}&search=${searchQuery}&statusFilter=${statusFilter}" class="px-3 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-r-md hover:bg-gray-50">
          Suivant
        </a>
      </c:if>
    </nav>
  </div>
</div>
    
<!-- Modal Update -->
<div id="updateModal" class="fixed inset-0 flex items-center justify-center bg-gray-900 bg-opacity-50 hidden">
  <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-2xl mt-1">
    <h2 class="text-xl font-semibold mb-4">Mettre à jour l'Article</h2>

    <form id="updateForm" action="" method="POST">
      <input type="hidden" name="action" value="update">
      <input type="hidden" id="updateId" name="id">
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <!-- Article Title -->
        <div class="flex flex-col col-span-2 my-1">
          <label for="updateTitle" class="block text-gray-700 text-sm font-bold mb-1">Titre:</label>
          <input id="updateTitle" name="titre"
            class="w-full px-3 py-2 text-sm leading-tight text-gray-700 border border-gray-300 rounded-lg focus:outline-none focus:border-orange-600"
            type="text" placeholder="Entrez le titre de l'article">
        </div>

        <!-- Article Content (Full Width) -->
        <div class="flex flex-col col-span-2 my-1">
          <label for="updateContent" class="block text-gray-700 text-sm font-bold mb-1">Contenu:</label>
          <textarea id="updateContent" name="contenu"
            class="w-full px-3 py-2 text-sm leading-tight text-gray-700 border border-gray-300 rounded-lg focus:outline-none focus:border-orange-600"
            rows="3" placeholder="Entrez le contenu"></textarea>
        </div>

        <!-- Date de Création -->
        <div class="flex flex-col my-1">
          <label for="updateCreationDate" class="block text-gray-700 text-sm font-bold mb-1">Date Création:</label>
          <input id="updateCreationDate" name="dateCreation"
            class="w-full px-3 py-2 text-sm leading-tight text-gray-700 border border-gray-300 rounded-lg focus:outline-none focus:border-orange-600"
            type="date">
        </div>

        <!-- Date de Publication -->
        <div class="flex flex-col my-1">
          <label for="updatePublicationDate" class="block text-gray-700 text-sm font-bold mb-1">Date Publication:</label>
          <input id="updatePublicationDate" name="datePublication"
            class="w-full px-3 py-2 text-sm leading-tight text-gray-700 border border-gray-300 rounded-lg focus:outline-none focus:border-orange-600"
            type="date">
        </div>

        <!-- Status -->
        <div class="flex flex-col my-1">
          <label for="updateStatus" class="block text-gray-700 text-sm font-bold mb-1">Status:</label>
          <select id="updateStatus" name="statut"
            class="w-full px-3 py-2 text-sm leading-tight text-gray-700 border border-gray-300 rounded-lg focus:outline-none focus:border-orange-600">
            <option value="Brouillon">Brouillon</option>
            <option value="Publie">Publié</option>
          </select>
        </div>

        <!-- Auteur Selection -->
        <div class="flex flex-col my-1">
          <label for="updateAuthor" class="block text-gray-700 text-sm font-bold mb-1">Auteur:</label>
          <select id="updateAuthor" name="auteurId"
            class="w-full px-3 py-2 text-sm leading-tight text-gray-700 border border-gray-300 rounded-lg focus:outline-none focus:border-orange-600">
            <c:forEach var="auteur" items="${auteurs}">
              <option value="${auteur.id}">${auteur.nom}</option>
            </c:forEach>
          </select>
        </div>
      </div>

      <!-- Modal Actions -->
      <div class="flex justify-end mt-4">
        <button type="button" onclick="toggleUpdateModal(false)"
          class="px-4 py-2 bg-gray-500 text-white rounded-lg mr-2 hover:bg-gray-600">
          Annuler
        </button>
        <button type="submit"
          class="px-4 py-2 bg-orange-600 text-white rounded-lg hover:bg-orange-700">
          Mettre à jour
        </button>
      </div>
    </form>
  </div>
</div>
   
    <script>
    function toggleModal(show) {
        const modal = document.getElementById('myModal');
        if (show) {
            modal.classList.remove('hidden');
        } else {
            modal.classList.add('hidden');
        }
    }
    function toggleUpdateModal(show) {
        const pop = document.getElementById('updateModal');
        if (show) {
        	pop.classList.remove('hidden');
        } else {
        	pop.classList.add('hidden');
        }
    }
    function openUpdateModal(button) {
        const articleData = JSON.parse(button.getAttribute('data-article'));
        document.getElementById('updateId').value = articleData.id;
        document.getElementById('updateTitle').value = articleData.titre;
        document.getElementById('updateContent').value = articleData.contenu;
        document.getElementById('updateCreationDate').value = articleData.dateCreation;
        document.getElementById('updatePublicationDate').value = articleData.datePublication;
        document.getElementById('updateStatus').value = articleData.statut;
        document.getElementById('updateAuthor').value = articleData.auteurId;
        toggleUpdateModal(true);
    }
    



    </script>
    
</body>
</html>