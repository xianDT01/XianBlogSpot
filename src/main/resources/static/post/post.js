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
                        <h3>${post.title}</h3>
                        <p>${post.content}</p>
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

    // Manejo de la creación de un nuevo post
    document.getElementById('newPostBtn').addEventListener('click', function() {
        // Redirige a una página para crear un nuevo post (cambia la ruta según tu estructura)
        window.location.href = '/create-post'; // Ajusta esta ruta según lo que tengas
    });
});
