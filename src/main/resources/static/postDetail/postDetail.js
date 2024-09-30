document.addEventListener('DOMContentLoaded', function() {
    const postId = window.location.pathname.split('/').pop();
    console.log('Post ID:', postId);

    // Cargar detalles del post
    fetch(`/api/posts/${postId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cargar los detalles del post');
            }
            return response.json();
        })
        .then(post => {
            console.log('Post Data:', post);
            document.getElementById('postTitle').innerText = post.title || 'Título no disponible';
            document.getElementById('postImage').src = post.imageUrl || '';
            document.getElementById('postContent').innerText = post.content || 'Contenido no disponible';

            // Cargar comentarios
            loadComments(postId);
        })
        .catch(error => {
            console.error('Fetch error:', error);
            document.getElementById('postContent').innerText = 'Error al cargar los detalles del post';
        });

    // Función para cargar comentarios
function loadComments(postId) {
    console.log(`Cargando comentarios para el post: ${postId}`);
    fetch(`/api/comments/post/${postId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cargar los comentarios');
            }
            return response.json();
        })
        .then(comments => {
            console.log('Comentarios recibidos:', comments);
            const commentsList = document.getElementById('commentsList');
            commentsList.innerHTML = '';
            comments.forEach(comment => {
                const commentDiv = document.createElement('div');
                commentDiv.classList.add('comment');
                commentDiv.innerText = `${comment.authorName}: ${comment.content}`;
                commentsList.appendChild(commentDiv);
            });
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}


    // Función para agregar un nuevo comentario
    document.getElementById('submitComment').addEventListener('click', function() {
        const commentText = document.getElementById('newComment').value;
        const userId = 1;  // Reemplazar con el ID del usuario actual (deberías manejar la autenticación)

        if (!commentText) {
            alert('Por favor, escribe un comentario.');
            return;
        }

        // Enviar el comentario junto con el postId y el userId
        fetch(`/api/comments`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ content: commentText, postId: postId, authorId: userId })  // Incluimos el authorId
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al enviar el comentario');
            }
            return response.json();
        })
        .then(() => {
            document.getElementById('newComment').value = ''; // Limpiar el campo
            loadComments(postId); // Volver a cargar los comentarios
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
    });
});
