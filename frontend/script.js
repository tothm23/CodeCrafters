document.addEventListener("DOMContentLoaded", function () {
  // Eseménykezelő a mynavbar elemekre
  var myNavbarItems = document.querySelectorAll(".mynavbar .dropdown-item");

  myNavbarItems.forEach(function (navbarItem) {
    navbarItem.addEventListener("click", function (event) {
      // Megtaláljuk az id-t a href-ben
      var targetId = this.getAttribute("id");

      // Az aktuális kategória azonosítójának mentése az URL-be
      var url = new URL(window.location.href);
      url.searchParams.set("currentCategory", targetId);
      window.history.pushState({}, "", url);

      // Az oldal betöltése csak a Termékek kategória esetén
      if (targetId === "termekLink") {
        loadProductPage();
      }
    });
  });

  function parseURL() {
    const url = new URL(window.location);
    const currentCategory = url.searchParams.get("currentCategory");

    if (currentCategory) {
      // Az oldal betöltése csak a Termékek kategória esetén
      if (currentCategory === "termekLink") {
        loadProductPage();
      }
    }
  }

  function updateURL() {
    const checkboxes = document.querySelectorAll(".form-check-input");
    const params = [];

    checkboxes.forEach((checkbox) => {
      if (checkbox.checked) {
        const linkId = checkbox.parentElement.getAttribute("id");
        params.push(linkId);

        // Az aktuális kategória azonosítójának mentése az URL-be
        const url = new URL(window.location.href);
        url.searchParams.set("currentCategory", linkId);
        window.history.pushState({}, "", url);
      }
    });

    // Oldal betöltése vagy egyéb műveletek a Termékek kategória esetén
    if (params.includes("termekLink")) {
      loadProductPage();
    }
  }

  function loadProductPage() {
    // Oldal betöltése vagy egyéb műveletek a Termékek kategória esetén
    // Például: window.location.href = './termékoldal/termekekoldal.html';
    console.log("Oldal betöltése: ./termékoldal/termekekoldal.html");
  }

  const checkboxes = document.querySelectorAll(".form-check-input");
  checkboxes.forEach((checkbox) => {
    checkbox.addEventListener("change", function () {
      updateURL();
    });
  });

  parseURL();
});

// GET kérés
fetch("http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/fooldal")
  .then((valasz) => valasz.json())
  .then((adat) => {
    var carouselInner = document.getElementById("gameCarousel");

    for (let i = 0; i < adat.length; i++) {
      carouselInner.innerHTML += `
      <div class="carousel-item">
          <div class="card ">
              <img src="./kepek/jatekok/${adat[i].kep}" class="d-block w-100" alt="${adat[i].nev}">
                  
              <div class="card-body">
                  <h5 class="card-title">${adat[i].nev}</h5>
                  <p class="card-text ar">${adat[i].ar} Ft</p>
                  <p class="card-text akciosar">${Math.round(adat[i].ar - (adat[i].ar / 100) * adat[i].akcio)}Ft</p>
                  <input id="hozzadas" class="my-2 p-2 btn btn-success fs-5" type="button" value="Hozzáadás a kosárhoz" />
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


//kosar gomb
let dbszam=document.getElementsByClassName("darabszam");
var hozzadasButton = document.getElementById('hozzadas');
        hozzadasButton.addEventListener('click', function() {
        dbszam.style.display=block;
        dbszam.innerHTML=parseInt(dbszam.innerHTML)+1;
        });

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
