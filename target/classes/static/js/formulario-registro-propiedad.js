
function mostrarPrimeraOpcion() {
  var opciones = document.getElementById("tipo-de-propiedad").options.value;
  opciones[0].selected = true;
}
/* Boton para ocultar redes sociales */
var switchOn = false;

function toggleRedesSociales() {
  var redesSocialesDiv = document.getElementById("redes-sociales");
  var switchImg = document.getElementById("switch-img");

  switchOn = !switchOn;

  if (switchOn) {
    redesSocialesDiv.style.display = "block";
    switchImg.src = "/imagenes/toggle-button.png";
  } else {
    redesSocialesDiv.style.display = "none";
    switchImg.src = "/imagenes//off-button.png";
  }
}
/* Validaciones del formulario */

function validarFormulario() {
  var tipoPropiedad = document.getElementById("tipo-de-propiedad").value;
  var nombre = document.querySelector(".nombre").value;
  var direccion = document.querySelector(".direccion").value;
  var localidad = document.getElementById("localidad").value;
  var codigoPostal = document.getElementById("cp").value;
  var fechaIngreso = document.getElementById("fondo-fecha-ingreso").value;
  var fechaEgreso = document.getElementById("fondo-fecha-egreso").value;
  var telefono = document.querySelector("input[type='text'][placeholder='223-458745']").value;
  var email = document.querySelector("input[type='email'][placeholder='tumail@gmail.com']").value;
  var imagenes = document.querySelector(".imagen").value;
  var precio = document.getElementById("caja-precio-v").value;

  // Validación requerida
  if (
    tipoPropiedad === "" ||
    nombre.trim() === "" ||
    direccion.trim() === "" ||
    localidad.trim() === "" ||
    codigoPostal.trim() === "" ||
    fechaIngreso.trim() === "" ||
    fechaEgreso.trim() === "" ||
    telefono.trim() === "" ||
    email.trim() === "" ||
    imagenes.trim() === "" ||
    precio.trim() === ""
  ) {
    alert("Por favor, complete todos los campos requeridos.");
    return false; // Evita enviar el formulario si hay campos vacíos
  }

  // Resto de la lógica del formulario...

  return true; // Envía el formulario si todos los campos están completos
}