<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion des Auteurs</title>
<script src="https://cdn.tailwindcss.com"></script>

<style>
  .modal-active {
    display: block;
  }
</style>

</head>
<body>

<header class='bg-white font-[sans-serif] mb-20 tracking-wide relative z-50'>
    <div id="collapseMenu"
      class='bg-[#213343] lg:!flex lg:justify-between lg:gap-y-4 lg:gap-x-10 lg:px-10 lg:py-2'>
      <ul class='lg:!flex lg:gap-x-10 py-4'>
        <li><a href='#' class='hover:text-[#FFA726] text-[#FFA726] text-md block'>Auteurs</a></li>
        <li><a href='javascript:void(0)' class='hover:text-[#FFA726] text-white text-md block'>Articles</a></li>
        <li><a href='javascript:void(0)' class='hover:text-[#FFA726] text-white text-md block'>Commentaires</a></li>
      </ul>
    </div>
</header>

<div class="w-[90%] mx-auto">
  <button id="openAddModal" type="button"
        class="px-5 py-2.5 rounded-lg text-sm my-4 tracking-wider font-medium border border-orange-600 bg-transparent hover:bg-gray-800 text-[#FFA726] hover:text-[#FFA726] transition-all duration-300">Ajouter un auteur</button>
  
  <!-- Table -->
  <div class="font-[sans-serif] overflow-x-auto rounded-lg">
    <table class="w-full bg-white">
      <thead class="bg-gray-800">
        <tr>
          <th class="p-4 text-left text-sm font-medium text-white">Nom</th>
          <th class="p-4 text-left text-sm font-medium text-white">Prénom</th>
          <th class="p-4 text-left text-sm font-medium text-white">Email</th>
          <th class="p-4 text-left text-sm font-medium text-white">Role</th>
          <th class="p-4 text-left text-sm font-medium text-white">Date naissance</th>
          <th class="p-4 text-left text-sm font-medium text-white">Actions</th>
        </tr>
      </thead>
      <tbody class="whitespace-nowrap">
        <c:forEach var="auteur" items="${auteurs}">
          <tr class="even:bg-blue-50">
            <td class="p-4 text-sm text-black">${auteur.nom}</td>
            <td class="p-4 text-sm text-black">${auteur.prenom}</td>
            <td class="p-4 text-sm text-black">${auteur.email}</td>
            <td class="p-4 text-sm text-black">${auteur.role}</td>
            <td class="p-4 text-sm text-black">${auteur.dateNaissance}</td>
            <td class="p-4 flex justify-center items-center">
              <button onclick="openUpdateModal(${auteur.id}, '${auteur.nom}', '${auteur.prenom}', '${auteur.email}', '${auteur.role}', '${auteur.dateNaissance}')" class="mr-4" title="Edit">
                <svg xmlns="http://www.w3.org/2000/svg" class="w-5 fill-blue-500 hover:fill-blue-700" viewBox="0 0 348.882 348.882">
                  <path d="m333.988 11.758-.42-.383A43.363 43.363 0 0 0 304.258 0a43.579 43.579 0 0 0-32.104 14.153L116.803 184.231a14.993 14.993 0 0 0-3.154 5.37l-18.267 54.762c-2.112 6.331-1.052 13.333 2.835 18.729 3.918 5.438 10.23 8.685 16.886 8.685h.001c2.879 0 5.693-.592 8.362-1.76l52.89-23.138a14.985 14.985 0 0 0 5.063-3.626L336.771 73.176c16.166-17.697 14.919-45.247-2.783-61.418zM130.381 234.247l10.719-32.134.904-.99 20.316 18.556-.904.99-31.035 13.578zm184.24-181.304L182.553 197.53l-20.316-18.556L294.305 34.386c2.583-2.828 6.118-4.386 9.954-4.386 3.365 0 6.588 1.252 9.082 3.53l.419.383c5.484 5.009 5.87 13.546.861 19.03z" />
                  <path d="M303.85 138.388c-8.284 0-15 6.716-15 15v127.347c0 21.034-17.113 38.147-38.147 38.147H68.904c-21.035 0-38.147-17.113-38.147-38.147V100.413c0-21.034 17.113-38.147 38.147-38.147h131.587c8.284 0 15-6.716 15-15s-6.716-15-15-15H68.904C31.327 32.266.757 62.837.757 100.413v180.321c0 37.576 30.571 68.147 68.147 68.147h181.798c37.576 0 68.147-30.571 68.147-68.147V153.388c.001-8.284-6.715-15-14.999-15z" />
                </svg>
              </button>
              <form method="POST" action="auteur">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="auteurId" value="${auteur.id}">
                <button type="submit" title="Delete" class="mr-4">
                  <svg xmlns="http://www.w3.org/2000/svg" class="w-5 fill-red-500 hover:fill-red-700" viewBox="0 0 24 24">
                    <path d="M19 7a1 1 0 0 0-1 1v11.191A1.92 1.92 0 0 1 15.99 21H8.01A1.92 1.92 0 0 1 6 19.191V8a1 1 0 0 0-2 0v11.191A3.918 3.918 0 0 0 8.01 23h7.98A3.918 3.918 0 0 0 20 19.191V8a1 1 0 0 0-1-1Zm1-3h-4V2a1 1 0 0 0-1-1H9a1 1 0 0 0-1 1v2H4a1 1 0 0 0 0 2h16a1 1 0 0 0 0-2ZM10 4V3h4v1Z" />
                    <path d="M11 17v-7a1 1 0 0 0-2 0v7a1 1 0 0 0 2 0Zm4 0v-7a1 1 0 0 0-2 0v7a1 1 0 0 0 2 0Z" />
                  </svg>
                </button>
              </form>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>

   <div class="flex justify-center my-10">
    <nav class="inline-flex rounded-md shadow">
      <c:if test="${currentPage > 1}">
        <a href="?page=${currentPage - 1}" class="px-3 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-l-md hover:bg-gray-50">Précédent</a>
      </c:if>
      <span class="px-3 py-2 text-sm font-medium text-orange-600 bg-orange-50 border border-gray-300 hover:bg-gray-50">${currentPage}</span>
      <c:if test="${auteurs.size() == 5}">
        <a href="?page=${currentPage + 1}" class="px-3 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-r-md hover:bg-gray-50">Suivant</a>
      </c:if>
    </nav>
  </div>


  <!-- Add Author Modal -->
  <div id="addAuthorModal" class="hidden fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
    <div class="bg-white p-8 rounded-lg w-1/3 relative">
      <h2 class="text-xl font-semibold mb-4">Ajouter un Auteur</h2>
      <form action="auteur" method="POST">
        <input type="hidden" name="action" value="add">
        <div class="mb-4">
        <label for="nom" class="block mb-1">Nom</label>
        <input type="text" id="nom" name="nom" class="w-full p-2 border border-gray-300 rounded-lg">
      </div>
      <div class="mb-4">
        <label for="prenom" class="block mb-1">Prénom</label>
        <input type="text" id="prenom" name="prenom" class="w-full p-2 border border-gray-300 rounded-lg">
      </div>
      <div class="mb-4">
        <label for="email" class="block mb-1">Email</label>
        <input type="email" id="email" name="email" class="w-full p-2 border border-gray-300 rounded-lg">
      </div>
      <div class="mb-4">
        <label for="role" class="block mb-1">Role</label>
        <select id="role" name="role" class="w-full p-2 border border-gray-300 rounded-lg">
          <option value="Contributeur">Contributeur</option>
          <option value="Editeur">Editeur</option>
        </select>
      </div>
      <div class="mb-4">
        <label for="dateNaissance" class="block mb-1">Date de naissance</label>
        <input type="date" id="dateNaissance" name="dateNaissance" class="w-full p-2 border border-gray-300 rounded-lg">
      </div>
      <div class="flex justify-end">
          <button type="button" class="ml-2 bg-gray-600 text-white px-4 py-2 rounded-lg hover:bg-gray-700" id="closeAddModal">Annuler</button>
          <button type="submit" class="ml-2 bg-orange-600 text-white px-4 py-2 rounded-lg hover:bg-orange-700">Ajouter</button>
        </div>


      </form>
    </div>
  </div>

  <!-- Update Author Modal -->
  <div id="updateAuthorModal" class="hidden fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
    <div class="bg-white p-8 rounded-lg w-1/3 relative">
      <h2 class="text-xl font-semibold mb-4">Modifier un Auteur</h2>
      <form id="updateAuthorForm" action="auteur" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="auteurId" id="updateAuteurId">
        <div class="mb-4">
          <label for="updateNom" class="block mb-1">Nom</label>
          <input type="text" name="nom" id="updateNom" required class="w-full p-2 border border-gray-300 rounded-lg">
        </div>
        <div class="mb-4">
          <label for="updatePrenom" class="block mb-1">Prénom</label>
          <input type="text" name="prenom" id="updatePrenom" required class="w-full p-2 border border-gray-300 rounded-lg">
        </div>
        <div class="mb-4">
          <label for="updateEmail" class="block mb-1">Email</label>
          <input type="email" name="email" id="updateEmail" required class="w-full p-2 border border-gray-300 rounded-lg">
        </div>
        <div class="mb-4">
        <label for="updateRole" class="block mb-1">Role</label>
        <select id="updateRole" name="role" class="w-full p-2 border border-gray-300 rounded-lg">
          <option value="Contributeur">Contributeur</option>
          <option value="Editeur">Editeur</option>
        </select>
        <div class="mb-4">
          <label for="updateDateNaissance" class="block mb-1">Date de Naissance</label>
          <input type="date" name="dateNaissance" id="updateDateNaissance" required class="w-full p-2 border border-gray-300 rounded-lg">
        </div>
        <div class="flex justify-end">
          <button type="button" class="ml-2 bg-gray-600 text-white px-4 py-2 rounded-lg hover:bg-gray-700" id="closeUpdateModal">Annuler</button>
          <button type="submit" class="ml-2 bg-orange-600 text-white px-4 py-2 rounded-lg hover:bg-orange-700">Modifier</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script>
  document.getElementById('openAddModal').onclick = function() {
    document.getElementById('addAuthorModal').classList.remove('hidden');
  };

  document.getElementById('closeAddModal').onclick = function() {
    document.getElementById('addAuthorModal').classList.add('hidden');
  };

  function openUpdateModal(id, nom, prenom, email, role, dateNaissance) {
    document.getElementById('updateAuteurId').value = id;
    document.getElementById('updateNom').value = nom;
    document.getElementById('updatePrenom').value = prenom;
    document.getElementById('updateEmail').value = email;
    document.getElementById('updateRole').value = role;
    document.getElementById('updateDateNaissance').value = dateNaissance;

    document.getElementById('updateAuthorModal').classList.remove('hidden');
  }

  document.getElementById('closeUpdateModal').onclick = function() {
    document.getElementById('updateAuthorModal').classList.add('hidden');
  };
</script>

</body>
</html>
