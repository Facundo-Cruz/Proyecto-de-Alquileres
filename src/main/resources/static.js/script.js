function mostrarPrimeraOpcion() {
    var opciones = document.getElementById("tipo-de-propiedad").options.value;
    opciones[0].selected = true;
  }

  var switchOn = false;

function toggleRedesSociales() {
  var redesSocialesDiv = document.getElementById("redes-sociales");
  var switchImg = document.getElementById("switch-img");
  
  switchOn = !switchOn;
  
  if (switchOn) {
    redesSocialesDiv.style.display = "block";
    switchImg.src = "../static.img/off-button.png";
  } else {
    redesSocialesDiv.style.display = "none";
    switchImg.src = "../static.img//off-button.png";
  }
}



// Obtener todos los elementos de tipo checkbox
var checkboxes = document.querySelectorAll('input[type="checkbox"]');

// Agregar un evento change a cada checkbox
checkboxes.forEach(function(checkbox) {
  checkbox.addEventListener('change', function() {
    // Verificar si el checkbox está seleccionado
    if (this.checked) {
      this.removeAttribute('checked'); // Eliminar el atributo 'checked'
    } else {
      this.setAttribute('checked', 'checked'); // Agregar el atributo 'checked'
    }
  });
});


  