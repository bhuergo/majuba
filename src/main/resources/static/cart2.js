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

let carrito = [];

document.addEventListener("DOMContentLoaded", () => {
  if (localStorage.getItem("cart")) {
    carrito = JSON.parse(localStorage.getItem("cart"));
    carrito.forEach((item) => {
      addItemToCart(item.name, item.price, item.id, item.cant);
    });
    updateCartTotal();
  }
});

const removeCartItem = (e) => {
  let removeFromCartButton = e.target;
  let idItem =
    removeFromCartButton.parentElement.parentElement.parentElement.id;
  let cart = window.localStorage.getItem("cart");
  let cartParsed = JSON.parse(cart);
  let cartItemIndex = cartParsed.findIndex(
    (itemCart) => itemCart.id === idItem
  );
  carrito.splice(cartItemIndex, 1);
  window.localStorage.setItem("cart", JSON.stringify(carrito));
  removeFromCartButton.parentElement.parentElement.parentElement.remove();
  updateCartTotal();
};

const quantityChanged = (e) => {
  let quantityInput = e.target;
  if (isNaN(quantityInput.value) || quantityInput.value <= 0) {
    quantityInput.value = 1;
  }
  let foodItem = quantityInput.parentElement.parentElement;
  let idItem = foodItem.id;
  let cart = window.localStorage.getItem("cart");
  let cartParsed = JSON.parse(cart);
  let cartItemIndex = cartParsed.findIndex(
    (itemCart) => itemCart.id === idItem
  );
  carrito[cartItemIndex].cant = +quantityInput.value;
  window.localStorage.setItem("cart", JSON.stringify(carrito));
  updateCartTotal();
};

const addToCartClicked = (e) => {
  let button = e.target;
  let orderItem = button.parentElement.parentElement;
  let foodItemID = button.getAttribute("data-id");
  let title = orderItem.getElementsByClassName("food__title")[0].innerText;
  let price = orderItem
    .getElementsByClassName("food__cart-price")[0]
    .innerText.replace("$", "");
  addItemToCart(title, price, foodItemID);
  updateCartTotal();
};

const addItemToCart = (title, price, foodItemID, cant = 1) => {
  let orderItemDiv = document.createElement("div");
  orderItemDiv.classList.add("order-item");
  orderItemDiv.id = foodItemID;
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
        <input class="cart-quantity-input" type="number" value="${cant}">
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
  let idItem = orderItemDiv.id;
  let carritoItem = {
    id: idItem,
    name: title,
    price: price,
    cant: cant,
  };
  let boolean = carrito.find((item) => item.name === carritoItem.name);
  if (!boolean) {
    carrito = [...carrito, carritoItem];
    window.localStorage.setItem("cart", JSON.stringify(carrito));
  }
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
    let foodItemID = orderItem.getAttribute("data-id");
    let price = +priceItem.innerHTML.replace("$", "");
    let quantity = +quantityItem.value;
    total += price * quantity;
  }
  total = Math.round(total * 100) / 100;
  document.getElementsByClassName("total-price")[0].innerHTML = total;
};
let removeCartItemButtons = document.getElementsByClassName("remove-item");
for (let removeButton of removeCartItemButtons) {
  removeButton.addEventListener("click", removeCartItem);
}

let quantityInputs = document.getElementsByClassName("cart-quantity-input");
for (let quantityInput of quantityInputs) {
  quantityInput.addEventListener("change", quantityChanged);
}

let addToCartButtons = document.getElementsByClassName("food__cart-button");
for (let addButton of addToCartButtons) {
  addButton.addEventListener("click", addToCartClicked);
}

document
  .getElementsByClassName("order-button")[0]
  .addEventListener("click", purchaseClicked);



async function purchaseClicked() {
  let cartParsed = JSON.parse(window.localStorage.getItem('cart'));
  let finishCart = [];
  cartParsed.forEach(cartItem => {
    let finishCartItem = {
      id: cartItem.id,
      cant: cartItem.cant
    }
    finishCart.push(finishCartItem);
  })
  console.log(finishCart);
  const url = "localhost:8080/saveCart__${}__";
  const options = {
      method: "post",
      headers: {
          "Content-Type": "application/json"
      },
      body: JSON.stringify(carrito)
  }

  const response = await fetch(url, options);
  return response.json();
}
