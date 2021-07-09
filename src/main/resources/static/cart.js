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

// Add to cart buttons
const addToCartButtons = document.querySelectorAll(".food__cart-button");
const cartModalItems = document.querySelector(".items-container");

const addToCartClicked = (e) => {
  let buttonClicked = e.target;
  let foodItem = buttonClicked.parentElement.parentElement;
  let title = foodItem.getElementsByClassName("food__title")[0].innerText;
  let price = foodItem.getElementsByClassName("price-value")[0].innerText;
  let cant = foodItem.getElementsByClassName("food__cart-select")[0].value;
  let totalPrice = price * cant;
  addItemToCart(title, cant, totalPrice);
};

const addItemToCart = (title, cant, totalPrice) => {
  const orderItem = document.createElement('div');
  orderItem.classList.add('order-item')
  const orderItemContent = `
            <p class="order-item__title">${title}</p>
            <p class="order-item__amount">${cant}</p>
            <div class="buttons-items">
              <button id="add-item">
                <i class="fas fa-plus"></i>
              </button>
              <button id="remove-item">
                <i class="fas fa-minus"></i>
              </button>
            </div>
            <p class="order-item__price">$<span>${totalPrice}</span></p>
  `;
  orderItem.innerHTML = orderItemContent;
  console.log(orderItem);
  // cartModalItems.innerHTML = orderItem;
  cartModalItems.append(orderItem);
  // cartModalItems.innerHTML.append(orderItem);
};

for (let i = 0; i < addToCartButtons.length; i++) {
  const button = addToCartButtons[i];
  button.addEventListener("click", addToCartClicked);
  // button.addEventListener("click", (e) => {
  //   let buttonClicked = e.target;
  //   let foodItem = buttonClicked.parentElement.parentElement;
  //   let price = foodItem.getElementsByClassName("price-value")[0].innerText;
  //   let cant = foodItem.getElementsByClassName("food__cart-select")[0].value;
  //   console.log(cant);
  // });
}
