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
  orderItem.classList.add('order-item');
  const orderItemsNames = cartModalItems.getElementsByClassName('order-item__title');
  for (let i = 0; i < orderItemsNames.length; i++) {
    if (orderItemsNames[i].innerHTML == title) {
      alert('Este item ya se encuentra en el carrito.');
      return;
    }
  }
  const orderItemContent = `
  <p>
    <span class="order-item__cant">${cant}</span>x
    <span class="order-item__title">${title}</span>
  </p>
  <div class="buttons-items">
    <button class="add-item">
      <i class="fas fa-plus"></i>
    </button>
    <button class="remove-item">
      <i class="fas fa-minus"></i>
    </button>
    <p class="order-item__price">$<span>${totalPrice}</span></p>
  </div>
  `;
  orderItem.innerHTML = orderItemContent;
  cartModalItems.append(orderItem);
  const removeCartItemsButtons = cartModalItems.getElementsByClassName('remove-item');
  for (let i = 0; i < removeCartItemsButtons.length; i++) {
    let removeButton = removeCartItemsButtons[i];
    removeButton.addEventListener('click', () => {
      alert('asd')
    });  
  }
  cartModalItems.getElementsByClassName('add-item')[0].addEventListener('click', updateCart)
};

for (let i = 0; i < addToCartButtons.length; i++) {
  const button = addToCartButtons[i];
  button.addEventListener("click", addToCartClicked);
}

// Remove items from cart



const updateCart = () => {
  let cartItemContainer = document.getElementsByClassName('items-container')[0];
  let cartRows = cartItemContainer.getElementsByClassName('order-item');
  let total = 0;
  console.log(cartItemContainer, cartRows);
}