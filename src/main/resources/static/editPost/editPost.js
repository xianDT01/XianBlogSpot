document.addEventListener('DOMContentLoaded', function() {
    const postSelect = document.getElementById('postSelect');
    const postTitle = document.getElementById('postTitle');
    const postContent = document.getElementById('postContent');
    const postImageUrl = document.getElementById('postImageUrl');
    const editPostForm = document.getElementById('editPostForm');

    // Cargar posts en el combobox
    fetch('/api/posts') // Asegúrate que esta ruta devuelve los posts
        .then(response => response.json())
        .then(posts => {
            posts.forEach(post => {
                const option = document.createElement('option');
                option.value = post.id;
                option.textContent = post.title;
                postSelect.appendChild(option);
            });
        });

    // Cargar los datos del post seleccionado
    postSelect.addEventListener('change', function() {
        const postId = postSelect.value;

        fetch(`/api/posts/${postId}`)
            .then(response => response.json())
            .then(post => {
                postTitle.value = post.title;
                postContent.value = post.content;
                postImageUrl.value = post.imageUrl || '';
            });
    });

    // Manejar el envío del formulario
    editPostForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const postId = postSelect.value;
        const updatedPost = {
            title: postTitle.value,
            content: postContent.value,
            imageUrl: postImageUrl.value
        };

        fetch(`/api/posts/${postId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedPost)
        })
        .then(response => response.json())
        .then(data => {
            alert('Post actualizado con éxito');
        })
        .catch(error => {
            console.error('Error al actualizar el post:', error);
        });
    });
});
