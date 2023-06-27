function validarFormulario() {

    var fechaIngreso = document.getElementById("fondo-fecha-ingreso").value;
    var fechaEgreso = document.getElementById("fondo-fecha-egreso").value;


    // Validación requerida
    if (

        fechaIngreso.trim() === "" ||
        fechaEgreso.trim() === ""

    ) {
        alert("Por favor, complete todos los campos requeridos.");
        return false; // Evita enviar el formulario si hay campos vacíos
    }

    // Resto de la lógica del formulario...

    return true; // Envía el formulario si todos los campos están completos
}
