// JavaScript
document.addEventListener('DOMContentLoaded', function() {
    fetch('/api/posts')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cargar los posts');
            }
            return response.json();
        })
        .then(posts => {
            const postsList = document.getElementById('postsList');
            posts.forEach(post => {
                const postDiv = document.createElement('div');
                postDiv.classList.add('post');
                postDiv.innerHTML = `
                    <img src="${post.imageUrl}" alt="Imagen del post">
                    <div class="post-content">
                        <h3>${post.title}</h3> <!-- Usa el valor del post directamente -->
                        <p>${post.content}</p> <!-- Usa el valor del post directamente -->
                        <a href="/detail/${post.id}" class="button">Leer más</a>
                    </div>
                `;
                postsList.appendChild(postDiv);
            });
        })
        .catch(error => {
            console.error(error);
            const postsList = document.getElementById('postsList');
            postsList.innerText = 'Error al cargar los posts';
        });
});
