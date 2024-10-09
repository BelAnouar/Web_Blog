<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script src="https://cdn.tailwindcss.com"></script>

</head>
<body>

<header class='bg-white font-[sans-serif] mb-20  tracking-wide relative z-50'>
  
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
        <li class='mb-6 hidden max-lg:block'>
          
        </li>
        <li class='max-lg:border-b max-lg:py-4'><a href='#'
            class='hover:text-[#FFA726] text-[#FFA726] text-sm block'>Home</a></li>
        <li class='max-lg:border-b max-lg:py-4'><a href='javascript:void(0)'
            class='hover:text-[#FFA726] text-white text-sm block'>Team</a></li>
        <li class='max-lg:border-b max-lg:py-4'><a href='javascript:void(0)'
            class='hover:text-[#FFA726] text-white text-sm block'>Feature</a></li>
        
      </ul>
  
      
    </div>
  </header>
  <button  type="button"
        class=" px-5 py-2.5 rounded-lg text-sm m-4 tracking-wider font-medium border border-orange-600 outline-none bg-transparent hover:bg-gray-800 text-[#FFA726] hover:text-[#FFA726] transition-all duration-300">Ajouter un auteur</button>
      
  <div class="font-[sans-serif] overflow-x-auto">
      <table class="min-w-full bg-white">
        <thead class="bg-gray-800 whitespace-nowrap">
          <tr>
            <th class="p-4 text-left text-sm font-medium text-white">
              Nom
            </th>
            <th class="p-4 text-left text-sm font-medium text-white">
              Prénom
            </th>
            <th class="p-4 text-left text-sm font-medium text-white">
              Email
            </th>
            <th class="p-4 text-left text-sm font-medium text-white">
              Role
            </th>
            <th class="p-4 text-left text-sm font-medium text-white">
              Date naissance
            </th>
            <th class="p-4 text-left text-sm font-medium text-white">
              Actions
            </th>
          </tr>
        </thead>

        <tbody class="whitespace-nowrap ">
          

          <tr class="even:bg-blue-50">
            <td class="p-4 text-sm text-black">
              Jane 
            </td>
            <td class="p-4 text-sm text-black">
               Smith
            </td>
            <td class="p-4 text-sm text-black">
              jane@example.com
            </td>
            <td class="p-4 text-sm text-black">
              User
            </td>
            <td class="p-4 text-sm text-black">
              2022-07-20
            </td>
            <td class="p-4">
              <button class="mr-4" title="Edit">
                <svg xmlns="http://www.w3.org/2000/svg" class="w-5 fill-blue-500 hover:fill-blue-700"
                  viewBox="0 0 348.882 348.882">
                  <path
                    d="m333.988 11.758-.42-.383A43.363 43.363 0 0 0 304.258 0a43.579 43.579 0 0 0-32.104 14.153L116.803 184.231a14.993 14.993 0 0 0-3.154 5.37l-18.267 54.762c-2.112 6.331-1.052 13.333 2.835 18.729 3.918 5.438 10.23 8.685 16.886 8.685h.001c2.879 0 5.693-.592 8.362-1.76l52.89-23.138a14.985 14.985 0 0 0 5.063-3.626L336.771 73.176c16.166-17.697 14.919-45.247-2.783-61.418zM130.381 234.247l10.719-32.134.904-.99 20.316 18.556-.904.99-31.035 13.578zm184.24-181.304L182.553 197.53l-20.316-18.556L294.305 34.386c2.583-2.828 6.118-4.386 9.954-4.386 3.365 0 6.588 1.252 9.082 3.53l.419.383c5.484 5.009 5.87 13.546.861 19.03z"
                    data-original="#000000" />
                  <path
                    d="M303.85 138.388c-8.284 0-15 6.716-15 15v127.347c0 21.034-17.113 38.147-38.147 38.147H68.904c-21.035 0-38.147-17.113-38.147-38.147V100.413c0-21.034 17.113-38.147 38.147-38.147h131.587c8.284 0 15-6.716 15-15s-6.716-15-15-15H68.904C31.327 32.266.757 62.837.757 100.413v180.321c0 37.576 30.571 68.147 68.147 68.147h181.798c37.576 0 68.147-30.571 68.147-68.147V153.388c.001-8.284-6.715-15-14.999-15z"
                    data-original="#000000" />
                </svg>
              </button>
              <button class="mr-4" title="Delete">
                <svg xmlns="http://www.w3.org/2000/svg" class="w-5 fill-red-500 hover:fill-red-700" viewBox="0 0 24 24">
                  <path
                    d="M19 7a1 1 0 0 0-1 1v11.191A1.92 1.92 0 0 1 15.99 21H8.01A1.92 1.92 0 0 1 6 19.191V8a1 1 0 0 0-2 0v11.191A3.918 3.918 0 0 0 8.01 23h7.98A3.918 3.918 0 0 0 20 19.191V8a1 1 0 0 0-1-1Zm1-3h-4V2a1 1 0 0 0-1-1H9a1 1 0 0 0-1 1v2H4a1 1 0 0 0 0 2h16a1 1 0 0 0 0-2ZM10 4V3h4v1Z"
                    data-original="#000000" />
                  <path d="M11 17v-7a1 1 0 0 0-2 0v7a1 1 0 0 0 2 0Zm4 0v-7a1 1 0 0 0-2 0v7a1 1 0 0 0 2 0Z"
                    data-original="#000000" />
                </svg>
              </button>
            </td>
          </tr>

          <tr class="even:bg-blue-50">
            <td class="p-4 text-sm text-black">
              Alen 
            </td>
            <td class="p-4 text-sm text-black">
               Smith
            </td>
            <td class="p-4 text-sm text-black">
              alen@example.com
            </td>
            <td class="p-4 text-sm text-black">
              User
            </td>
            <td class="p-4 text-sm text-black">
              2022-07-21
            </td>
            <td class="p-4">
              <button class="mr-4" title="Edit">
                <svg xmlns="http://www.w3.org/2000/svg" class="w-5 fill-blue-500 hover:fill-blue-700"
                  viewBox="0 0 348.882 348.882">
                  <path
                    d="m333.988 11.758-.42-.383A43.363 43.363 0 0 0 304.258 0a43.579 43.579 0 0 0-32.104 14.153L116.803 184.231a14.993 14.993 0 0 0-3.154 5.37l-18.267 54.762c-2.112 6.331-1.052 13.333 2.835 18.729 3.918 5.438 10.23 8.685 16.886 8.685h.001c2.879 0 5.693-.592 8.362-1.76l52.89-23.138a14.985 14.985 0 0 0 5.063-3.626L336.771 73.176c16.166-17.697 14.919-45.247-2.783-61.418zM130.381 234.247l10.719-32.134.904-.99 20.316 18.556-.904.99-31.035 13.578zm184.24-181.304L182.553 197.53l-20.316-18.556L294.305 34.386c2.583-2.828 6.118-4.386 9.954-4.386 3.365 0 6.588 1.252 9.082 3.53l.419.383c5.484 5.009 5.87 13.546.861 19.03z"
                    data-original="#000000" />
                  <path
                    d="M303.85 138.388c-8.284 0-15 6.716-15 15v127.347c0 21.034-17.113 38.147-38.147 38.147H68.904c-21.035 0-38.147-17.113-38.147-38.147V100.413c0-21.034 17.113-38.147 38.147-38.147h131.587c8.284 0 15-6.716 15-15s-6.716-15-15-15H68.904C31.327 32.266.757 62.837.757 100.413v180.321c0 37.576 30.571 68.147 68.147 68.147h181.798c37.576 0 68.147-30.571 68.147-68.147V153.388c.001-8.284-6.715-15-14.999-15z"
                    data-original="#000000" />
                </svg>
              </button>
              <button class="mr-4" title="Delete">
                <svg xmlns="http://www.w3.org/2000/svg" class="w-5 fill-red-500 hover:fill-red-700" viewBox="0 0 24 24">
                  <path
                    d="M19 7a1 1 0 0 0-1 1v11.191A1.92 1.92 0 0 1 15.99 21H8.01A1.92 1.92 0 0 1 6 19.191V8a1 1 0 0 0-2 0v11.191A3.918 3.918 0 0 0 8.01 23h7.98A3.918 3.918 0 0 0 20 19.191V8a1 1 0 0 0-1-1Zm1-3h-4V2a1 1 0 0 0-1-1H9a1 1 0 0 0-1 1v2H4a1 1 0 0 0 0 2h16a1 1 0 0 0 0-2ZM10 4V3h4v1Z"
                    data-original="#000000" />
                  <path d="M11 17v-7a1 1 0 0 0-2 0v7a1 1 0 0 0 2 0Zm4 0v-7a1 1 0 0 0-2 0v7a1 1 0 0 0 2 0Z"
                    data-original="#000000" />
                </svg>
              </button>
            </td>
          </tr>    
        </tbody>
      </table>
    </div>
    
    <script>
    var toggleOpen = document.getElementById('toggleOpen');
    var toggleClose = document.getElementById('toggleClose');
    var collapseMenu = document.getElementById('collapseMenu');

    function handleClick() {
      if (collapseMenu.style.display === 'block') {
        collapseMenu.style.display = 'none';
      } else {
        collapseMenu.style.display = 'block';
      }
    }

    toggleOpen.addEventListener('click', handleClick);
    toggleClose.addEventListener('click', handleClick);
    </script>
    
</body>
</html>