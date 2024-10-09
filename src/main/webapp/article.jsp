<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="https://cdn.tailwindcss.com"></script>
  </head>

  <body>

    <div class="bg-white sm:px-6 px-4 py-10 font-sans">
      <div class="max-w-4xl mx-auto">

        <div class="grid gap-16">



          <div class="rounded">
            <div class="flex justify-between">
              <div>
                <span class="text-sm block text-gray-400 mb-4">5 OCT 2023</span>
                <h3 class="text-xl font-bold text-gray-800 group-hover:text-blue-500 transition-all">Trends and
                  Predictions</h3>
                <div class="mt-4">
                  <div class="pr-6 text-right  py-4">

                    <p class="text-gray-400 text-sm">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis
                      accumsan, nunc et tempus blandit, metus mi consectetur felis turpis vitae ligula.</p>
                  </div>
                </div>
                <div class="flex flex-wrap items-center gap-3 mt-6">
                  <p class="text-xs text-orange-700">BY SIMON KONECKI</p>
                </div>
              </div>
              <div class="relative  group ">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                  <path
                    d="M12 10c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zm6 0c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zM6 10c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2z">
                  </path>
                </svg>
                <div
                  class="bg-white z-50 group-hover:block hover:block hidden text-center shadow-lg py-4 px-4 sm:min-w-[100px]  absolute -right-6">
                  <form action="" method="get">
                    <button class="text-sm text-gray-500 hover:text-black">Modifier</button>
                  </form>

                  <hr class="border-b-0 my-4" />
                  <button onclick="" class="text-sm  text-gray-500 hover:text-black">
                    Supprimer
                  </button>
                </div>
              </div>
            </div>
            </div>
          </div>
        </div>
        <section class="bg-white dark:bg-gray-900 py-8 lg:py-16 antialiased">
          <div class="max-w-2xl mx-auto px-4">
            <div class="flex justify-between items-center mb-6">
              <h2 class="text-lg  font-bold text-gray-900 dark:text-white">Discussion (20)</h2>
            </div>
            <form class="mb-6">
              <div
                class="py-2 px-4 mb-4 bg-white rounded-lg rounded-t-lg border border-gray-200 dark:bg-gray-800 dark:border-gray-700">
                <label for="comment" class="sr-only">Your comment</label>
                <textarea id="comment" rows="6"
                  class="px-0 w-full text-sm text-gray-900 border-0 focus:ring-0 focus:outline-none dark:text-white dark:placeholder-gray-400 dark:bg-gray-800"
                  placeholder="Write a comment..." required></textarea>
              </div>
              <button type="submit"
                class="inline-flex items-center py-2.5 px-4 text-xs font-medium text-center text-white bg-primary-700 rounded-lg focus:ring-4 focus:ring-primary-200 dark:focus:ring-primary-900 hover:bg-primary-800">
                Post comment
              </button>
            </form>
            <article class="p-6 text-base bg-white rounded-lg dark:bg-gray-900">
              <footer class="flex justify-between items-center mb-2">
                <div class="flex items-center">
                  <p class="inline-flex items-center mr-3 text-sm text-gray-900 dark:text-white font-semibold"><img
                      class="mr-2 w-6 h-6 rounded-full"
                      src="https://flowbite.com/docs/images/people/profile-picture-2.jpg" alt="Michael Gough">Michael
                    Gough</p>
                  <p class="text-sm text-gray-600 dark:text-gray-400"><time pubdate datetime="2022-02-08"
                      title="February 8th, 2022">Feb. 8, 2022</time></p>
                </div>
                
                <div class="relative  group ">
                  <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24">
                    <path
                      d="M12 10c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zm6 0c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zM6 10c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2z">
                    </path>
                  </svg>
                  <div
                    class="bg-white z-50 group-hover:block hover:block hidden text-center shadow-lg py-4 px-4 sm:min-w-[100px]  absolute -right-6">
                    <form action="" method="get">
                      <button class="text-sm text-gray-500 hover:text-black">Modifier</button>
                    </form>
  
                    <hr class="border-b-0 my-4" />
                    <button onclick="" class="text-sm  text-gray-500 hover:text-black">
                      Supprimer
                    </button>
                  </div>
                </div>
              </footer>
              <p class="text-gray-500 dark:text-gray-400">Very straight-to-point article. Really worth time reading.
                Thank
                you! But tools are just the
                instruments for the UX designers. The knowledge of the design tools are as important as the
                creation of the design strategy.</p>

            </article>

            <hr class="my-8" />


          </div>

        </section>
      </div>


  </body>

  </html>