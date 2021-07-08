const cantSelect = document.querySelector(".food__cart-select");
const cartButton = document.querySelector(".cart-button");
const modalBg = document.querySelector(".cart__modal-bg");
const cartModalButton = document.querySelector(".cart__modal-button");
cartButton.addEventListener("click", () => {
  modalBg.classList.toggle("bg-active");
});
cartModalButton.addEventListener("click", () => {
  modalBg.classList.toggle("bg-active");
});


// Cart

