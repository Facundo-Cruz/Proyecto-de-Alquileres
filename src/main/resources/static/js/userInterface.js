const casas = document.querySelectorAll(".house-card")
const carta = document.querySelector(".info-casa")
const datos = document.getElementById("wow")

casas.forEach(function(casa){
    casa.addEventListener("click",function(dato){
     toggleHidden()
     cambiarDatos(casa.innerHTML)
    })
})

carta.addEventListener("click",function(event){
    if(carta === event.target){
        toggleHidden()
    }
})

function cambiarDatos(dataso){
    datos.innerHTML=dataso
}

function toggleHidden (){
    carta.classList.toggle("hidden")
}