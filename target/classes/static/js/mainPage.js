const button = document.querySelector('.boton-tipos');
const select = document.querySelector(".drop");
const options = document.querySelectorAll(".option");
const selectLabel = document.querySelector('.titulo-boton');

button.addEventListener("click", function (e) {
	e.preventDefault();
  toggleHidden();
});

function toggleHidden() {
	select.classList.toggle("hidden");
    document.getElementById("arrow").classList.toggle("on")
    document.getElementById("boton").classList.toggle("activo")
}

options.forEach(function(option) {
	option.addEventListener("click", function (e) {
		setSelectTitle(e);
	});
});

function setSelectTitle(e) {
	const labelElement = document.querySelector(`label[for="${e.target.id}"]`).innerText;
	selectLabel.innerText = labelElement;
	toggleHidden();
};

function toggleDropdown() {
    var dropdown = document.getElementById("dropdown");

    if (dropdown.style.display === "none") {
      dropdown.style.display = "block";
    } else {
      dropdown.style.display = "none";
    }
  };
