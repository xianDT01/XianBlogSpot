document.addEventListener('DOMContentLoaded', () => {
    const postSelect = document.getElementById('postSelect');
    const postDetails = document.getElementById('postDetails');
    const deletePostBtn = document.getElementById('deletePostBtn');

    // Cargar la lista de posts
    fetch('/api/posts')
        .then(response => response.json())
        .then(posts => {
            posts.forEach(post => {
                const option = document.createElement('option');
                option.value = post.id;
                option.textContent = post.title;
                postSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error cargando los posts:', error));

    // Mostrar detalles del post seleccionado
    postSelect.addEventListener('change', () => {
        const postId = postSelect.value;
        if (postId) {
            fetch(`/api/posts/${postId}`)
                .then(response => response.json())
                .then(post => {
                    postDetails.innerHTML = `
                        <h3>${post.title}</h3>
                        <p>${post.content}</p>
                        <p>Autor: ${post.author}</p>
                    `;
                })
                .catch(error => console.error('Error cargando los detalles del post:', error));
        }
    });

    // Eliminar post con confirmación
    deletePostBtn.addEventListener('click', () => {
        const postId = postSelect.value;
        if (postId) {
            const confirmDelete = confirm("¿Estás seguro de que deseas eliminar este post y todos sus comentarios?");
            if (confirmDelete) {
                fetch(`/api/posts/${postId}`, {
                    method: 'DELETE'
                })
                .then(response => {
                    if (response.ok) {
                        alert('Post eliminado con éxito');
                        window.location.href = '/posts';  // Redirigir a la página de posts
                    } else {
                        alert('Error al eliminar el post');
                    }
                })
                .catch(error => console.error('Error al eliminar el post:', error));
            }
        }
    });
});
