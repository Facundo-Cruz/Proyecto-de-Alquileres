function mostrarPrimeraOpcion() {
<<<<<<< HEAD
  var opciones = document.getElementById("tipo-de-propiedad").options.value;
  opciones[0].selected = true;
}

var switchOn = false;
=======
    var opciones = document.getElementById("tipo-de-propiedad").options.value;
    opciones[0].selected = true;
  }

  var switchOn = false;
>>>>>>> 002f9aab68627c9c1cbd22968d20216ac8c08735

function toggleRedesSociales() {
  var redesSocialesDiv = document.getElementById("redes-sociales");
  var switchImg = document.getElementById("switch-img");
<<<<<<< HEAD

  switchOn = !switchOn;

  if (switchOn) {
    redesSocialesDiv.style.display = "block";
    switchImg.src = "/imagenes/off-button.png";
  } else {
    redesSocialesDiv.style.display = "none";
    switchImg.src = "/imagenes//off-button.png";
=======
  
  switchOn = !switchOn;
  
  if (switchOn) {
    redesSocialesDiv.style.display = "block";
    switchImg.src = "../static.img/off-button.png";
  } else {
    redesSocialesDiv.style.display = "none";
    switchImg.src = "../static.img//off-button.png";
>>>>>>> 002f9aab68627c9c1cbd22968d20216ac8c08735
  }
}



// Obtener todos los elementos de tipo checkbox
var checkboxes = document.querySelectorAll('input[type="checkbox"]');

// Agregar un evento change a cada checkbox
<<<<<<< HEAD
checkboxes.forEach(function (checkbox) {
  checkbox.addEventListener('change', function () {
=======
checkboxes.forEach(function(checkbox) {
  checkbox.addEventListener('change', function() {
>>>>>>> 002f9aab68627c9c1cbd22968d20216ac8c08735
    // Verificar si el checkbox está seleccionado
    if (this.checked) {
      this.removeAttribute('checked'); // Eliminar el atributo 'checked'
    } else {
      this.setAttribute('checked', 'checked'); // Agregar el atributo 'checked'
    }
  });
});


<<<<<<< HEAD




=======
  
>>>>>>> 002f9aab68627c9c1cbd22968d20216ac8c08735
