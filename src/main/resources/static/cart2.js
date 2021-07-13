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

// const addToCartButtons = document.querySelectorAll(".food__cart-button");
// const cartModalItems = document.querySelector(".items-container");

// const addToCartClicked = (e) => {
//   let buttonClicked = e.target;
//   let foodItem = buttonClicked.parentElement.parentElement;
//   let title = foodItem.getElementsByClassName("food__title")[0].innerText;
//   let price = foodItem.getElementsByClassName("price-value")[0].innerText;
//   let cant = foodItem.getElementsByClassName("food__cart-select")[0].value;
//   let totalPrice = price * cant;
//   totalPrice = Math.round(totalPrice * 100) / 100;
//   addItemToCart(title, cant, totalPrice);
// };

// const addItemToCart = (title, cant, totalPrice) => {
//   const orderItem = document.createElement("div");
//   orderItem.classList.add("order-item");
//   const orderItemsNames =
//     cartModalItems.getElementsByClassName("order-item__title");
//   for (let i = 0; i < orderItemsNames.length; i++) {
//     if (orderItemsNames[i].innerHTML == title) {
//       alert("Este item ya se encuentra en el carrito.");
//       return;
//     }
//   }
//   const orderItemContent = `
//   <p>
//     <span class="order-item__cant">${cant}</span>x
//     <span class="order-item__title">${title}</span>
//   </p>
//   <div class="buttons-items">
//     <button class="add-item">
//       <i class="fas fa-plus"></i>
//     </button>
//     <button class="remove-item">
//       <i class="fas fa-minus"></i>
//     </button>
//     <p class="order-item__price">$<span>${totalPrice}</span></p>
//   </div>
//   `;
//   orderItem.innerHTML = orderItemContent;
//   cartModalItems.append(orderItem);

// };

// let addItemsButtons = cartModalItems.getElementsByClassName("add-item");
// for (let addItemButton of addItemsButtons) {
//   addItemButton.addEventListener("click", () => {
//     console.log("sad");
//   });
// }
// for (let i = 0; i < addToCartButtons.length; i++) {
//   const button = addToCartButtons[i];
//   button.addEventListener("click", addToCartClicked);
// }

// document.getElementsByClassName('order-button')[0].addEventListener('click', () => {alert('hola')})

const removeCartItem = (e) => {
  let removeFromCartButton = e.target;
  removeFromCartButton.parentElement.parentElement.parentElement.remove();
  updateCartTotal();
};

const quantityChanged = (e) => {
  let quantityInput = e.target;
  if (isNaN(quantityInput.value) || quantityInput.value <= 0) {
    quantityInput.value = 1;
  }
  updateCartTotal();
};

const addToCartClicked = (e) => {
  let button = e.target;
  let orderItem = button.parentElement.parentElement;
  let title = orderItem.getElementsByClassName("food__title")[0].innerText;
  let price = orderItem
    .getElementsByClassName("food__cart-price")[0]
    .innerText.replace("$", "");
  addItemToCart(title, price);
  updateCartTotal();
};

const addItemToCart = (title, price) => {
  let orderItemDiv = document.createElement("div");
  orderItemDiv.classList.add("order-item");
  let orderItems = document.getElementsByClassName("items-container")[0];
  let orderItemNames = orderItems.getElementsByClassName("order-item__title");
  for (let name of orderItemNames) {
    if (name.innerHTML == title) {
      alert("Este item ya se encuentra en el carrito.");
      return;
    }
  }
  let orderItemContent = `
    <p class="order-item__title">${title}</p>
    <span class="order-item__price">$${price}</span>
    <div class="cart-quantity">
        <input class="cart-quantity-input" type="number" value="1">
        <button class="remove-item" type="button"><i class="fas fa-trash-alt"></i></button>
    </div>
    `;
  orderItemDiv.innerHTML = orderItemContent;
  orderItems.append(orderItemDiv);
  orderItemDiv
    .getElementsByClassName("remove-item")[0]
    .addEventListener("click", removeCartItem);
  orderItemDiv
    .getElementsByClassName("cart-quantity-input")[0]
    .addEventListener("change", quantityChanged);
};

const updateCartTotal = () => {
  let orderItemsContainer =
    document.getElementsByClassName("items-container")[0];
  let orderItems = orderItemsContainer.getElementsByClassName("order-item");
  let total = 0;
  for (let orderItem of orderItems) {
    let priceItem = orderItem.getElementsByClassName("order-item__price")[0];
    let quantityItem = orderItem.getElementsByClassName(
      "cart-quantity-input"
    )[0];
    let price = +priceItem.innerHTML.replace("$", "");
    let quantity = +quantityItem.value;
    total += price * quantity;
  }
  total = Math.round(total * 100) / 100;
  document.getElementsByClassName("total-price")[0].innerHTML = total;
};

let removeCartItemButtons = document.getElementsByClassName("remove-item");
for (let removeButton of removeCartItemButtons) {
  // removeButton.addEventListener("click", removeCartItem);
}

let quantityInputs = document.getElementsByClassName("cart-quantity-input");
for (let quantityInput of quantityInputs) {
  quantityInput.addEventListener("change", quantityChanged);
}

let addToCartButtons = document.getElementsByClassName("food__cart-button");
for (let addButton of addToCartButtons) {
  addButton.addEventListener("click", addToCartClicked);
}
