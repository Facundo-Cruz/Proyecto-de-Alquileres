
function validarFormulario() {
    var nombre = document.querySelector(".nombre").value;
    var apellido = document.querySelector(".apellido").value;
    var alias = document.querySelector(".alias").value;
    var email = document.querySelector(".email").value;
    var pass = document.querySelector(".password1").value;
    var pass1 = document.querySelector(".password").value;
    var imagenes = document.querySelector(".imagen").value;
    var propietario = document.querySelector(".radio:checked");

    // Validación requerida
    if (
        nombre.trim() === "" ||
        apellido.trim() === "" ||
        alias.trim() === "" ||
        email.trim() === "" ||
        imagenes.trim() === "" ||
        !propietario ||
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

    // Validación de formato de contraseña
   var passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,}$/;
    if (!passwordRegex.test(pass)) {
        alert("La contraseña debe tener al menos 6 caracteres, una mayúscula y un carácter especial.");
       return false; // Evita enviar el formulario si la contraseña no cumple el formato
    }

    // Resto de la lógica del formulario...

    return true; // Envía el formulario si todos los campos están completos y las validaciones pasan
}

