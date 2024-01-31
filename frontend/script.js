const logged_user = JSON.parse(localStorage.getItem("bejelentkezes"));
const log_reg = document.getElementById("log_reg");
const userbox = document.getElementById("user-box");

let cart_id = [];

// GET kérés
fetch(
    "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/fooldal"
  )
  .then((response) => response.json())
  .then((data) => {
    var carouselInner = document.getElementById("gameCarousel");

    for (let i = 0; i < data.length; i++) {
      var akciosAr = Math.round(
        data[i].ar - (data[i].ar / 100) * data[i].akcio
      );
      carouselInner.innerHTML += `
        <div class="carousel-item">
        <div class="card flex-column flex-lg-row">
        <a href="./jatek/jatek.html?id=${data[i].id}"><img src="./kepek/jatekok/${
          data[i].kep
        }" class="d-block w-100" alt="${data[i].nev}">
        </a>
        <div class="card-body">
          <!--<span class="badge bg-secondary">New</span>-->
          <h5 class="card-title">${data[i].nev}</h5>
          ${
            data[i].akcio > 0
              ? `<p class="card-text ar" style="text-decoration: line-through;">${data[i].ar} Ft</p>`
              : `<p class="card-text ar">${data[i].ar} Ft</p>`
          }
          ${
            data[i].akcio > 0
              ? `<p class="card-text akciosar">${akciosAr} Ft</p>`
              : ""
          }
          ${hozzaadasGomb(data[i].id)}
        </div>
        </div>
      `;
      // Kiválasztjuk az első carousel-item-et
      var elsoCarouselElem = document.querySelector(".carousel-item");

      // Hozzáadjuk az "active" osztályt
      elsoCarouselElem.classList.add("active");
    }
  })
  .catch((hiba) => alert(hiba));

//get kosár
function getkosar(gombid) {
  fetch(`http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/kosar/${logged_user.id}`, {
      method: 'GET'
    })
    .then(response => response.json())
    .then(result => {
      // Ellenőrizze, hogy a válasz tartalmazza-e a várt tulajdonságokat
      console.log('Eredmény:', result);
      for (let i = 0; i < result.length; i++) {
        gombid[i] = result[i].jatekId;
        console.log(gombid[i], result[i].jatekId);
        console.log(cart_id[0], cart_id[1], cart_id[2]);
      }
    }).catch(error => console.error(error));
}
// vissza gomb
let mybutton = document.getElementById("vissza-gomb");

window.onscroll = () => {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    mybutton.style.display = "flex";
  } else {
    mybutton.style.display = "none";
  }
};

function felgorget() {
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

function hozzaadasGomb(id) {
  var vanilyen_ID = false;
  if (logged_user) {
    for (let i = 0; i < cart_id.length; i++) {
      if (cart_id[i] === id) {
        vanilyen_ID = true;
      }
    }
    if (vanilyen_ID) {
      return `<input id="hozzadas" data-jatek-id="${id}" class="my-2 p-2 btn btn-success fs-5" type="button" disabled value="Kosárban" />`;
    } else {
      return `<input id="hozzadas" data-jatek-id="${id}" class="my-2 p-2 btn btn-success fs-5" type="button" value="Hozzáadás a kosárhoz" />`;

    }
  } else {
    return `<input id="hozzadas" data-jatek-id="${id}" class="my-2 p-2 btn btn-success fs-5" type="button" disabled value="Hozzáadás a kosárhoz" />`;
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
    getkosar(cart_id);
  } else {
    log_reg.display = "flex";
    userbox.display = "none";
  }
  //kijelentkezés essetén
  logout.addEventListener("click", function () {
    localStorage.removeItem("bejelentkezes");
    log_reg.style.display = "flex";
    userbox.style.display = "none";
  });
  settings_btn.addEventListener("click", function () {
    if (actual_path == "/frontend/index.html") {
      // Most már tovább navigálhatsz az új oldalra
      window.location.href = "./felhasznalo/felhasznalo.html";
    } else {
      window.location.href = "../felhasznalo/felhasznalo.html";
    }
  });
  cart_btn.addEventListener("click", function () {
    if (actual_path == "/frontend/index.html") {
      // Most már tovább navigálhatsz az új oldalra
      window.location.href = "./kosar/kosar.html";
    } else {
      window.location.href = "../kosar/kosar.html";
    }
  });
  reg.addEventListener("click", function () {
    if (actual_path == "/frontend/index.html") {
      // Most már tovább navigálhatsz az új oldalra
      window.location.href = "./regisztracio/regisztracio.html";
    } else {
      window.location.href = "../regisztracio/regisztracio.html";
    }
  });
  login.addEventListener("click", function () {
    if (actual_path == "/frontend/index.html") {
      // Most már tovább navigálhatsz az új oldalra
      window.location.href = "./bejelentkezes/bejelentkezes.html";
    } else {
      window.location.href = "../bejelentkezes/bejelentkezes.html";
    }
  });
  // Eseményfigyelők hozzáadása a keresőhöz és az ármezőkhöz
  form.addEventListener("submit", function (esemeny) {
    esemeny.preventDefault();

    // A keresőszöveg tárolása a localStorage-ban
    localStorage.setItem('keresesSzoveg', search_Input.value.toLowerCase());

    if (actual_path == "/frontend/index.html") {
      // Tovább navigálunk az új oldalra
      window.location.href = "./termekek/termekek.html";
    } else if (actual_path == "/frontend/jatek/jatek.html") {
      window.location.href = "../termekek/termekek.html";
    } else {
      // Egyéb esetek kezelése (opcionális)
    }
  });


  if (logged_user) {
    console.log(logged_user.id);
    log_reg.style.display = "none";
    userbox.style.display = "flex";
    user_name.innerHTML += logged_user.felhasznaloNev;
    email.innerHTML += logged_user.email;
  } else {
    log_reg.style.display = "flex";
    userbox.style.display = "none";
  }

  document.addEventListener("click", function (event) {
    if (event.target && event.target.id === "hozzadas") {
      //ha hozzáadjuk a kosárhoz akkor disbaled és a value megváltozik kosárbanra
      event.target.value = "Kosárban";
      event.target.disabled = true;
      const jatekId = event.target.getAttribute("data-jatek-id");

      if (jatekId)
        var myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/json");
      var raw = JSON.stringify({
        jatekId: jatekId,
        felhasznaloId: logged_user.id
      });

      console.log(raw);

      var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
      };

      fetch("http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/kosar", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
    }
  });

});