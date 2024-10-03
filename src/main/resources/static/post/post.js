document.addEventListener('DOMContentLoaded', function () {
    let currentPage = 0;
    const pageSize = 5; // Número de posts por página
    const postsList = document.getElementById('postsList');
    const prevButton = document.getElementById('prevPage');
    const nextButton = document.getElementById('nextPage');
    const pageIndicator = document.getElementById('pageIndicator');

    function loadPosts(page) {
        fetch(`/api/posts/paginated?page=${page}&size=${pageSize}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al cargar los posts');
                }
                return response.json();
            })
            .then(data => {
                const posts = data.content; // Contenido de la página actual
                postsList.innerHTML = ''; // Limpiar lista de posts

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

    // Manejo del botón de página anterior
    prevButton.addEventListener('click', function () {
        if (currentPage > 0) {
            currentPage--;
            loadPosts(currentPage);
        }
    });

    // Manejo del botón de página siguiente
    nextButton.addEventListener('click', function () {
        currentPage++;
        loadPosts(currentPage);
    });

    // Cargar los posts de la primera página al cargar el DOM
    loadPosts(currentPage);
});
