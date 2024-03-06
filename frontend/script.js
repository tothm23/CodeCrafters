const logged_user = JSON.parse(localStorage.getItem("loged_userdata"));
const log_reg = document.getElementById("log_reg");
const userbox = document.getElementById("user-box");

let cart_id = [];

// GET kérés
fetch(
    "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/mainPage"
  )
  .then((response) => response.json())
  .then((data) => {
    var carouselInner = document.getElementById("gameCarousel");
    var bestseller_card = document.getElementById("bestseller_card");
    var new_card = document.getElementById("new_card");

    for (let i = 0; i < data.length; i++) {
      var salePrice = Math.round(
        data[i].price - (data[i].price / 100) * data[i].discount
      );
      if (data[i].type == "carousel") {
        carouselInner.innerHTML += `
        <div class="carousel-item">
        <div class="card d-flex flex-column flex-md-row">
        <a class="d-block w-100" href="./game/game.html?id=${data[i].id}"><img src="./img/games/${
          data[i].image
        }" class="d-block w-100" alt="${data[i].name}">
        </a>
        <div class="card-body">
          <h5 class="card-title">${data[i].name}</h5>
          ${
            data[i].discount > 0
              ? `<p class="card-text" style="text-decoration: line-through;">${data[i].price} Ft</p>`
              : `<p class="card-text">${data[i].price} Ft</p>`
          }
          ${
            data[i].discount > 0
              ? `<p class="card-text saleprice">${salePrice} Ft</p>`
              : ""
          }
          ${add_btn(data[i].id)}
        </div>
        </div>
      `;
        // Kiválasztjuk az első carousel-item-et
        var elsoCarouselElem = document.querySelector(".carousel-item");

        // Hozzáadjuk az "active" osztályt
        elsoCarouselElem.classList.add("active");
      }
      if(data[i].type === "bestseller")
      {
        bestseller_card.innerHTML += `
        <div class="card d-flex flex-column">
        <a class="d-block w-100" href="./game/game.html?id=${data[i].id}"><img src="./img/games/${
          data[i].image
        }" class="d-block w-100" alt="${data[i].name}">
        </a>
        <div class="card-body">
          <h5 class="card-title">${data[i].name}</h5>
          ${
            data[i].discount > 0
              ? `<p class="card-text" style="text-decoration: line-through;">${data[i].price} Ft</p>`
              : `<p class="card-text">${data[i].price} Ft</p>`
          }
          ${
            data[i].discount > 0
              ? `<p class="card-text saleprice">${salePrice} Ft</p>`
              : ""
          }
          ${add_btn(data[i].id)}
        </div>
      `;
      }
      if(data[i].type === "new"){
        new_card.innerHTML += `
        <div class="card d-flex flex-column">
        <a class="d-block w-100" href="./game/game.html?id=${data[i].id}"><img src="./img/games/${
          data[i].image
        }" class="d-block w-100" alt="${data[i].name}">
        </a>
        <div class="card-body">
          <span class="badge bg-secondary">Új</span>
          <h5 class="card-title">${data[i].name}</h5>
          ${
            data[i].discount > 0
              ? `<p class="card-text" style="text-decoration: line-through;">${data[i].price} Ft</p>`
              : `<p class="card-text">${data[i].price} Ft</p>`
          }
          ${
            data[i].discount > 0
              ? `<p class="card-text saleprice">${salePrice} Ft</p>`
              : ""
          }
          ${add_btn(data[i].id)}
        </div>
      `;
      }
    }
  })
  .catch((error) => alert(error));

//get kosár
function getCart(btn_id) {
  fetch(`http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/basket/${logged_user.id}`, {
      method: 'GET'
    })
    .then(response => response.json())
    .then(result => {
      // Ellenőrizze, hogy a válasz tartalmazza-e a várt tulajdonságokat
      //console.log('Eredmény:', result);
      for (let i = 0; i < result.length; i++) {
        btn_id[i] = result[i].gameId;
        console.log(btn_id[i], result[i].gameId);
        console.log(cart_id[0], cart_id[1], cart_id[2]);
      }
    }).catch(error => console.error(error));
}
// vissza gomb
let mybutton = document.getElementById("scroll_Up");

window.onscroll = () => {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    mybutton.style.display = "flex";
  } else {
    mybutton.style.display = "none";
  }
};

function scroll_Up() {
  window.scrollTo({
    top: 0,
    behavior: "smooth",
  });
}
//token
function parseJwt(token) {
  var base64Url = token.split('.')[1];
  var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
  var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function (c) {
    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  }).join(''));

  return JSON.parse(jsonPayload);
}

function add_btn(id) {
  var vanilyen_ID = false;
  if (logged_user) {
    for (let i = 0; i < cart_id.length; i++) {
      if (cart_id[i] === id) {
        vanilyen_ID = true;
      }
    }
    if (vanilyen_ID) {
      return `<input id="add_btn" data-jatek-id="${id}" class="my-2 p-2 btn btn-success fs-5" type="button" disabled value="Kosárban" />`;
    } else {
      return `<input id="add_btn" data-jatek-id="${id}" class="my-2 p-2 btn btn-success fs-5" type="button" value="Hozzáadás" />`;

    }
  } else {
    return `<input id="add_btn" data-jatek-id="${id}" class="my-2 p-2 btn btn-success fs-5" type="button" disabled value="Hozzáadás" />`;
  }
};

document.addEventListener("DOMContentLoaded", function () {
  const search_Input = document.querySelector('.navbar input[type="search"]');
  const form = document.querySelector(".navbar form");
  const actual_path = window.location.pathname;
  const logout = document.getElementById("logout");
  const settings_btn = document.getElementById("settings");
  const cart_btn = document.getElementById("cart");
  const user_name = document.getElementById("username");
  const email = document.getElementById("email");
  const login = document.getElementById("login");
  const reg = document.getElementById("registration");

  if (logged_user) {
    log_reg.display = "none";
    userbox.display = "flex";
    getCart(cart_id);
  } else {
    log_reg.display = "flex";
    userbox.display = "none";
  }
  //kijelentkezés essetén
  logout.addEventListener("click", function () {
    localStorage.removeItem("loged_userdata");
    log_reg.style.display = "flex";
    userbox.style.display = "none";
  });
  settings_btn.addEventListener("click", function () {
    if (actual_path == "/frontend/index.html") {
      // Most már tovább navigálhatsz az új oldalra
      window.location.href = "./user/user.html";
    } else {
      window.location.href = "../user/user.html";
    }
  });
  cart_btn.addEventListener("click", function () {
    if (actual_path == "/frontend/index.html") {
      // Most már tovább navigálhatsz az új oldalra
      window.location.href = "./cart/cart.html";
    } else {
      window.location.href = "../cart/cart.html";
    }
  });
  reg.addEventListener("click", function () {
    if (actual_path == "/frontend/index.html") {
      // Most már tovább navigálhatsz az új oldalra
      window.location.href = "./registration/registration.html";
    } else {
      window.location.href = "../registration/registration.html";
    }
  });
  login.addEventListener("click", function () {
    if (actual_path == "/frontend/index.html") {
      // Most már tovább navigálhatsz az új oldalra
      window.location.href = "./login/login.html";
    } else {
      window.location.href = "../login/login.html";
    }
  });
  // Eseményfigyelők hozzáadása a keresőhöz és az ármezőkhöz
  form.addEventListener("submit", function (esemeny) {
    esemeny.preventDefault();

    // A keresőszöveg tárolása a localStorage-ban
    localStorage.setItem('searched_text', search_Input.value.toLowerCase());

    if (actual_path == "/frontend/index.html") {
      // Tovább navigálunk az új oldalra
      window.location.href = "./products/products.html";
    } else if (actual_path == "/frontend/game/game.html") {
      window.location.href = "../products/products.html";
    } else {
      // Egyéb esetek kezelése (opcionális)
    }
  });

  if (logged_user) {
    console.log(logged_user.id);
    log_reg.style.display = "none";
    userbox.style.display = "flex";
    user_name.innerHTML += logged_user.userName;
    email.innerHTML += logged_user.email;
  } else {
    log_reg.style.display = "flex";
    userbox.style.display = "none";
  }

  document.addEventListener("click", function (event) {
    if (event.target && event.target.id === "add_btn") {
      //ha hozzáadjuk a kosárhoz akkor disabled és a value megváltozik kosárban-ra
      event.target.value = "Kosárban";
      event.target.disabled = true;
      const gameId = event.target.getAttribute("data-jatek-id");

      if (gameId)
        var myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/json");
      var raw = JSON.stringify({
        gameId: gameId,
        userId: logged_user.id
      });

      console.log(raw);

      var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
      };

      fetch("http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/basket", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
    }
  });

});