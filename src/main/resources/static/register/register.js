document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch('/api/users/register', {
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
        if (response.status === 409) { // Si hay un conflicto (email ya registrado)
            throw new Error('El email ya está en uso');
        }
        if (!response.ok) {
            throw new Error('Error en el registro');
        }
        return response.json();
    })
    .then(data => {
        document.getElementById('responseMessage').innerText = 'Registro exitoso';
        document.getElementById('responseMessage').style.color = 'green'; // Color verde
    })
    .catch(error => {
        document.getElementById('responseMessage').innerText = error.message;
        document.getElementById('responseMessage').style.color = 'red'; // Color rojo
    });
});
