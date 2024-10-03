document.addEventListener('DOMContentLoaded', function () {
    let currentPage = 0;
    const pageSize = 5; // Número de posts por página
    const postsList = document.getElementById('postsList');
    const prevButton = document.getElementById('prevPage');
    const nextButton = document.getElementById('nextPage');
    const pageIndicator = document.getElementById('pageIndicator');
    const searchForm = document.getElementById('searchForm');
    const searchInput = document.getElementById('searchInput');
    let searchKeyword = ''; // Para almacenar la palabra clave de búsqueda

    function loadPosts(page) {
        const url = searchKeyword
            ? `/api/posts/search?keyword=${searchKeyword}&page=${page}&size=${pageSize}`
            : `/api/posts/paginated?page=${page}&size=${pageSize}`;

        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al cargar los posts');
                }
                return response.json();
            })
            .then(data => {
                const posts = data.content; // Contenido de la página actual
                postsList.innerHTML = ''; // Limpiar lista de posts

                if (posts.length === 0) {
                    postsList.innerText = 'No se encontraron posts.';
                    return;
                }

                posts.forEach(post => {
                    const postDiv = document.createElement('div');
                    postDiv.classList.add('post');
                    postDiv.innerHTML = `
                        <img src="${post.imageUrl}" alt="Imagen del post">
                        <div class="post-content">
                            <h3>${post.title}</h3>
                            <p>${post.content}</p>
                            <a href="/detail/${post.id}" class="button">Leer más</a>
                        </div>
                    `;
                    postsList.appendChild(postDiv);
                });

                // Actualizar los botones de navegación
                prevButton.disabled = currentPage === 0;
                nextButton.disabled = currentPage >= data.totalPages - 1;

                // Mostrar el indicador de la página actual
                pageIndicator.textContent = `Página ${currentPage + 1} de ${data.totalPages}`;
            })
            .catch(error => {
                console.error(error);
                postsList.innerText = 'Error al cargar los posts';
            });
    }

    // Manejo del formulario de búsqueda
    searchForm.addEventListener('submit', function (event) {
        event.preventDefault();
        searchKeyword = searchInput.value; // Actualizar la palabra clave de búsqueda
        currentPage = 0; // Resetear la página a 0 al buscar
        loadPosts(currentPage); // Cargar posts con la nueva búsqueda
    });

    // Navegación a la página anterior
    prevButton.addEventListener('click', function () {
        if (currentPage > 0) {
            currentPage--;
            loadPosts(currentPage);
        }
    });

    // Navegación a la siguiente página
    nextButton.addEventListener('click', function () {
        currentPage++;
        loadPosts(currentPage);
    });

    // Cargar los posts de la primera página al cargar el DOM
    loadPosts(currentPage);
});
