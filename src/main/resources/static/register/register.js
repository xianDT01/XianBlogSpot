document.getElementById("registerForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const response = await fetch("/api/users", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
    });

    const result = await response.json();

    if (response.ok) {
        document.getElementById("responseMessage").innerText = "¡Registro exitoso!";
        document.getElementById("responseMessage").style.color = "green";
    } else {
        document.getElementById("responseMessage").innerText = "Error en el registro. Intente de nuevo.";
    }
});
