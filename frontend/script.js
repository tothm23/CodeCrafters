const bejelentkezettFelhasznalo = JSON.parse(localStorage.getItem("bejelentkezes"));

let kosarid = [];
getkosar(kosarid);

// GET kérés
fetch(
    "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/fooldal"
  )
  .then((valasz) => valasz.json())
  .then((adat) => {
    var carouselInner = document.getElementById("gameCarousel");

    for (let i = 0; i < adat.length; i++) {
      var akciosAr = Math.round(
        adat[i].ar - (adat[i].ar / 100) * adat[i].akcio
      );
      carouselInner.innerHTML += `
        <div class="carousel-item">
          <div class="card flex-column flex-lg-row">
            <a href="./jatek/jatek.html?id=${adat[i].id}"><img src="./kepek/jatekok/${
              adat[i].kep
            }" class="d-block w-100" alt="${adat[i].nev}">
            </a>
            <div class="card-body">
              <h5 class="card-title">${adat[i].nev}</h5>
              ${
                adat[i].akcio > 0
                  ? `<p class="card-text ar" style="text-decoration: line-through;">${adat[i].ar} Ft</p>`
                  : `<p class="card-text ar">${adat[i].ar} Ft</p>`
              }
              ${
                adat[i].akcio > 0
                  ? `<p class="card-text akciosar">${akciosAr} Ft</p>`
                  : ""
              }
              ${hozzaadasGomb(adat[i].id)}
            </div>  
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
  fetch(`http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/kosar/${bejelentkezettFelhasznalo.id}`, {
      method: 'GET'
    })
    .then(response => response.json())
    .then(result => {
      // Ellenőrizze, hogy a válasz tartalmazza-e a várt tulajdonságokat
      console.log('Eredmény:', result);
      for (let i = 0; i < result.length; i++) {
        gombid[i] = result[i].jatekId;
        console.log(gombid[i], result[i].jatekId);
        console.log(kosarid[0], kosarid[1], kosarid[2]);
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

function hozzaadasGomb(id) {
  var vanilyen_ID=false;
  for(let i = 0; i < kosarid.length; i++) {
    console.log(id,"for i:",kosarid[i]);
    if (kosarid[i] === id) {
      vanilyen_ID=true;
      console.log("vanilyen");
    }
    
  }
  if (bejelentkezettFelhasznalo && vanilyen_ID==true) {
    return `<input id="hozzadas" data-jatek-id="${id}" class="my-2 p-2 btn btn-success fs-5" type="button" disabled value="Kosárban" />`;
  }else if(bejelentkezettFelhasznalo && vanilyen_ID==false){
    return `<input id="hozzadas" data-jatek-id="${id}" class="my-2 p-2 btn btn-success fs-5" type="button" value="Hozzáadás a kosárhoz" />`;
  } 
  else {
    return `<input id="hozzadas" data-jatek-id="${id}" class="my-2 p-2 btn btn-success fs-5" type="button" disabled value="Hozzáadás a kosárhoz" />`;
  }

};

document.addEventListener("DOMContentLoaded", function () {
  const keresoInput = document.querySelector('.navbar input[type="search"]');
  const form = document.querySelector(".navbar form");
  const log_reg = document.getElementById("log_reg");
  const felhasznaloful = document.getElementById("felhasznalo-box");
  const aktualisPath = window.location.pathname;
  const kijelentkezés_gomb = document.getElementById("felhasznalo_kilep");
  const beallitasok_gomb = document.getElementById("beallitasok");
  const fnev = document.getElementById("felhasznalo");
  //kijelentkezés essetén
  kijelentkezés_gomb.addEventListener("click", function () {
    localStorage.removeItem("bejelentkezes");
    log_reg.style.display = "flex";
    felhasznaloful.style.display = "none";
  });
  beallitasok_gomb.addEventListener("click", function () {
    if (aktualisPath == "/frontend/index.html") {
      // Most már tovább navigálhatsz az új oldalra
      window.open("./felhasznalo/felhasznalo.html");
    } else {
      window.open("../felhasznalo/felhasznalo.html");
    }
  });
  // Eseményfigyelők hozzáadása a keresőhöz és az ármezőkhöz
  form.addEventListener("submit", function (esemeny) {
    esemeny.preventDefault();

    // A keresőszöveg tárolása a localStorage-ban
    localStorage.setItem('keresesSzoveg', keresoInput.value.toLowerCase());

    if (aktualisPath == "/frontend/index.html") {
      // Tovább navigálunk az új oldalra
      window.location.href = "./termekek/termekek.html";
    } else if (aktualisPath == "/frontend/jatek/jatek.html") {
      window.location.href = "../termekek/termekek.html";
    } else {
      // Egyéb esetek kezelése (opcionális)
    }
  });


  if (bejelentkezettFelhasznalo) {
    console.log(bejelentkezettFelhasznalo.id);
    log_reg.style.display = "none";
    felhasznaloful.style.display = "flex";
    fnev.innerHTML = "";
    fnev.innerHTML += bejelentkezettFelhasznalo.felhasznaloNev;
  } else {
    log_reg.style.display = "flex";
    felhasznaloful.style.display = "none";
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
        felhasznaloId: bejelentkezettFelhasznalo.id
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