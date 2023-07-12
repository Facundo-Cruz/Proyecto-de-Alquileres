
function validarFormulario() {
    var nombre = document.querySelector(".nombre").value;
    var apellido = document.querySelector(".apellido").value;
    var alias = document.querySelector(".alias").value;
    var email = document.querySelector(".email").value;
    var pass = document.querySelector(".password1").value;
    var pass1 = document.querySelector(".password").value;
    var imagenes = document.querySelector(".imagen").value;
    var propietario = document.querySelector(".campo").value;

    // Validación requerida
    if (
        nombre.trim() === "" ||
        apellido.trim() === "" ||
        alias.trim() === "" ||
        email.trim() === "" ||
        propietario.trim() === "" ||
        pass1.trim() === "" ||
        pass.trim() === ""
    ) {
        alert("Por favor, complete todos los campos requeridos.");
        return false; // Evita enviar el formulario si hay campos vacíos
    }

    // Validación de contraseñas
    if (pass !== pass1) {
        alert("Las contraseñas no coinciden.");
        return false; // Evita enviar el formulario si las contraseñas no coinciden
    }

    // Validación de la contraseña
    if (!validarContrasena(pass)) {
        return false; // Evita enviar el formulario si la contraseña no cumple el formato
    }

    return true; // Envía el formulario si todos los campos están completos y las validaciones pasan
}

function validarContrasena(pass) {
    var passwordRegex = /^(?=.*[A-Z])(?=.*\d).{6,}$/;
    if (!passwordRegex.test(pass)) {
        alert("La contraseña debe tener al menos 6 caracteres, una mayúscula y un número.");
        return false; // Evita enviar el formulario si la contraseña no cumple el formato
    }
    return true; // La contraseña cumple el formato
}

// Asignar el evento submit al formulario
var formulario = document.querySelector(".formulario-registro-de-usuario");
formulario.addEventListener("submit", function(event) {
    if (!validarFormulario()) {
        event.preventDefault(); // Evita que el formulario se envíe si la validación no pasa
    }
});

function mostrarPrimeraOp() {
    var opciones = document.getElementById("campo").options.value;
    opciones[0].selected = true;
  }
