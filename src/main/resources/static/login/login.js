document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch('/api/users/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email,
            password: password
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error en el inicio de sesión');
        }
        return response.json();
    })
    .then(data => {
        // Redirigir a la página principal después de un inicio de sesión exitoso
        window.location.href = '/posts'; // Cambia '/posts' por la ruta de tu página principal
    })
    .catch(error => {
        document.getElementById('responseMessage').innerText = error.message;
    });
});
