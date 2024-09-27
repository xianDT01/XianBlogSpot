document.getElementById('createPostBtn').addEventListener('click', function(event) {
    // Puedes agregar validaciones aquí si es necesario
    const title = document.getElementById('title').value;
    const content = document.getElementById('content').value;

    if (title === '' || content === '') {
        alert('Por favor, completa todos los campos obligatorios.');
        event.preventDefault(); // Evita que el formulario se envíe
    }
});
