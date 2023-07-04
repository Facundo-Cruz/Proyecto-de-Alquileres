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
var slideIndex = 1
showSlides(slideIndex)

function moveSlide(n){
    slideIndex +=n 
    showSlides(slideIndex)
}

function showSlides(n){
    let slides = document.getElementsByClassName("slide")
    if (n>slides.length) {
        slideIndex=1    
    }
    if(n<1){
        slideIndex=slides.length
    }
    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display="none"
    }
    slides[slideIndex-1].style.display="block"
}
