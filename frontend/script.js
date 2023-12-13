const bejelentkezettFelhasznalo = JSON.parse(localStorage.getItem("bejelentkezes"));
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
  if (bejelentkezettFelhasznalo) {
    return `<input id="hozzadas" data-jatek-id="${id}" class="my-2 p-2 btn btn-success fs-5" type="button" value="Hozzáadás a kosárhoz" />`;
  } else {
    return `<input id="hozzadas" data-jatek-id="${id}" class="my-2 p-2 btn btn-success fs-5" type="button" disabled value="Hozzáadás a kosárhoz" />`;
  }

};

document.addEventListener("DOMContentLoaded", function () {
  const keresoInput = document.querySelector('.navbar input[type="search"]');
  const form = document.querySelector(".navbar form");
  const aktualisPath = window.location.pathname;
  console.log(aktualisPath);
  //kijelentkezés essetén
  //localStorage.removeItem("bejelentkezes");
  console.log(bejelentkezettFelhasznalo.id);
  // Eseményfigyelők hozzáadása a keresőhöz és az ármezőkhöz
  form.addEventListener("submit", function (esemeny) {
    // A keresőszöveg tárolása a localStorage-ban
    localStorage.setItem('keresesSzoveg', keresoInput.value.toLowerCase());

    if (aktualisPath == "/frontend/index.html") {
      // Most már tovább navigálhatsz az új oldalra
      window.open("./termekek/termekek.html");
    } else if (aktualisPath == "/frontend/jatek/jatek.html") {
      window.open("../termekek/termekek.html");
    } else {}
  });


  document.addEventListener("click", function (event) {
    if (event.target && event.target.id === "hozzadas") {
      const jatekId = event.target.getAttribute("data-jatek-id");

      // Execute the code to send a POST request to your server
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