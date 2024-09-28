document.addEventListener('DOMContentLoaded', function() {
    const postId = window.location.pathname.split('/').pop();
    console.log('Post ID:', postId); // Asegúrate de obtener el ID correcto

    fetch(`/api/posts/${postId}`)
        .then(response => {
            console.log('Response status:', response.status);
            if (!response.ok) {
                throw new Error('Error al cargar los detalles del post');
            }
            return response.json();
        })
        .then(post => {
            console.log('Post Data:', post); // Asegúrate de obtener los datos correctos

            // Renderizar los datos en la página
            document.getElementById('postTitle').innerText = post.title || 'Título no disponible';
            document.getElementById('postImage').src = post.imageUrl || '';
            document.getElementById('postContent').innerText = post.content || 'Contenido no disponible';
        })
        .catch(error => {
            console.error('Fetch error:', error);
            document.getElementById('postContent').innerText = 'Error al cargar los detalles del post';
        });
});
