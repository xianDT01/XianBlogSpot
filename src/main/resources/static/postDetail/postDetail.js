document.addEventListener("DOMContentLoaded", () => {
    const postId = window.location.pathname.split("/").pop(); // Obtiene el ID del post de la URL
    fetch(`/api/posts/${postId}`) // Asegúrate de tener una API que devuelva los detalles del post
        .then(response => response.json())
        .then(post => {
            document.getElementById("postTitle").innerText = post.title;
            document.getElementById("postImage").src = post.imageUrl;
            document.getElementById("postContent").innerText = post.content;

            // Cargar comentarios existentes
            loadComments(postId);
        });

    // Manejar el envío de nuevos comentarios
    document.getElementById("commentForm").addEventListener("submit", (event) => {
        event.preventDefault();
        const comment = document.getElementById("comment").value;

        // Enviar el comentario a tu API
        fetch(`/api/posts/${postId}/comments`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ comment }),
        })
        .then(response => response.json())
        .then(() => {
            document.getElementById("comment").value = ""; // Limpiar el textarea
            loadComments(postId); // Volver a cargar comentarios
        });
    });
});

function loadComments(postId) {
    fetch(`/api/posts/${postId}/comments`) // Asegúrate de tener una API que devuelva los comentarios
        .then(response => response.json())
        .then(comments => {
            const commentsList = document.getElementById("commentsList");
            commentsList.innerHTML = ""; // Limpiar comentarios existentes
            comments.forEach(comment => {
                const commentElement = document.createElement("p");
                commentElement.innerText = comment.text;
                commentsList.appendChild(commentElement);
            });
        });
}
