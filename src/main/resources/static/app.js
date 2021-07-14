const cards = document.querySelectorAll(".food__cart-button");
const items = document.getElementById("items");
const footer = document.getElementById("footer");
const templateFooter = document.getElementById("").content;
const templateCarrito = document.getElementById("modal").content;
const fragment = document.createDocumentFragment();
let carrito = [];

document.addEventListener("DOMContentLoaded", () => {
  if (localStorage.getItem("carrito")) {
    carrito = JSON.parse(localStorage.getItem("carrito"));
    pintarCarrito();
  }
});
cards.forEach((c) =>
  c.addEventListener("click", (e) => {
    addCarrito(e);
  })
);

items.addEventListener("click", (e) => {
  btnAction(e);
});

const addCarrito = (e) => {
  setCarrito(e.target.parentElement.parentElement);
  e.stopPropagation();
};

const setCarrito = (objeto) => {
  console.log("holasdasd");
  const producto = {
    id: objeto.querySelector("button").dataset.id,
    title: objeto.querySelector("h5").textContent,
    precio: objeto.querySelector("p").textContent,
    cantidad: 1,
  };
  // if para aumentar cantidad y no objeto completo
  if (carrito.hasOwnProperty(producto.id)) {
    producto.cantidad = carrito[producto.id].cantidad + 1;
  }
  carrito[producto.id] = { ...producto };
  pintarCarrito();
};

const pintarCarrito = () => {
  items.innerHTML = "";
  Object.values(carrito).forEach((producto) => {
    templateCarrito.querySelector("th").textContent = producto.id;
    templateCarrito.querySelectorAll("td")[0].textContent = producto.title;
    templateCarrito.querySelectorAll("td")[1].textContent = producto.cantidad;
    templateCarrito.querySelector(".btn-info").dataset.id = producto.id;
    templateCarrito.querySelector(".btn-danger").dataset.id = producto.id;
    templateCarrito.querySelector("span").textContent =
      producto.cantidad * producto.precio;
    const clone = templateCarrito.cloneNode(true);
    fragment.appendChild(clone);
  });
  items.appendChild(fragment);
  pintarFooter();
  localStorage.setItem("carrito", JSON.stringify(carrito));
};

// const itemNuevo = document.createElement("div");
// itemNuevo.classList.add("order-item");
// const itemTitle = document.createElement("p");
// itemTitle.innerText="asd";
// itemNuevo.appendChild(itemTitle);

const pintarFooter = () => {
  footer.innerHTML = "";
  if (Object.keys(carrito).length === 0) {
    footer.innerHTML =
      '<th scope="row" colspan="5">Carrito vacío - comience a comprar!</th>';
    return;
  }
  const nCantidad = Object.values(carrito).reduce(
    (acc, { cantidad }) => acc + cantidad,
    0
  );
  const nPrecio = Object.values(carrito).reduce(
    (acc, { cantidad, precio }) => acc + cantidad * precio,
    0
  );

  templateFooter.querySelectorAll("td")[0].textContent = nCantidad;
  templateFooter.querySelector("span").textContent = nPrecio;

  const clone = templateFooter.cloneNode(true);
  fragment.appendChild(clone);
  footer.appendChild(fragment);

  const btnVaciar = document.getElementById("vaciar-carrito");
  btnVaciar.addEventListener("click", () => {
    carrito = {};
    pintarCarrito();
  });
};

const btnAction = (e) => {
  //    accion sumar
  if (e.target.classList.contains("btn-info")) {
    // carrito[e.target.dataset.id]
    const producto = carrito[e.target.dataset.id];
    producto.cantidad++;
    carrito[e.target.dataset.id] = { ...producto };
    pintarCarrito();
  }
  //restar cosas de carrito
  if (e.target.classList.contains("btn-danger")) {
    const producto = carrito[e.target.dataset.id];
    producto.cantidad--;
    if (producto.cantidad === 0) {
      delete carrito[e.target.dataset.id];
    }
    pintarCarrito();
  }

  e.stopPropagation();
};

const enviarCompra = () => {
  const compraHecha = JSON.stringify(Object.values(carrito));
};
//ultimo carrito 1/jul
