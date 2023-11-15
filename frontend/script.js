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

var games = [
  {
    nevBE: "Witcher 3",
    arBE: 29.99,
    akciosarBE: 20.99,
    leirasBE: "Ez egy fantasztikus játék.",
    kepBE: "./kepek/jatekok/witcher3.jpg",
    korhatarBE: 12,
    akcioBE: true,
    mennyisegraktaronBE: 50,
    kategoriaIdBE: 1,
    eszkozIdBE: 3,
    platformIdBE: 2,
  },
  {
    nevBE: "Játék 2",
    arBE: 39.99,
    akciosarBE: 21.99,
    leirasBE: "Ez egy másik fantasztikus játék.",
    kepBE: "./kepek/jatekok/BF2.webp",
    korhatarBE: 16,
    akcioBE: false,
    mennyisegraktaronBE: 30,
    kategoriaIdBE: 2,
    eszkozIdBE: 1,
    platformIdBE: 1,
  },
  {
    nevBE: "Játék 3",
    arBE: 19.99,
    akciosarBE: 6.99,
    leirasBE: "Ez is egy fantasztikus játék.",
    kepBE: "./kepek/jatekok/csgo.jpg",
    korhatarBE: 18,
    akcioBE: true,
    mennyisegraktaronBE: 20,
    kategoriaIdBE: 1,
    eszkozIdBE: 2,
    platformIdBE: 3,
  },
];

var carouselInner = document.getElementById("gameCarousel");

games.forEach(function (game, index) {
  var activeClass = index === 0 ? "active" : "";
  var itemClass = "three-cards";

  carouselInner.innerHTML += `
    <div class="carousel-item ${activeClass} ${itemClass}">
        <div class="card">
            <img src="${game.kepBE}" class="card-img-top" alt="...">
                <div class="card-body">
                <h5 class="card-title">${game.nevBE}</h5>
                <p class="card-text ar">${game.arBE} Ft</p>
                <p class="card-text akciosar">${game.akciosarBE}Ft</p>
                <input id="hozzadas" class="my-2 p-2 btn btn-success fs-5" type="button" value="Hozzáadás a kosárhoz" />         
        </div>
    </div>    
        </div>
    </div>
  `;
});

//vissza gomb
let mybutton = document.getElementById("vissza-gomb");

window.onscroll = function () {
  scrollFunction();
};

function scrollFunction() {
  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    mybutton.style.display = "block";
  } else {
    mybutton.style.display = "none";
  }
}

function topFunction() {
  window.scrollTo({
    top: 0,
    behavior: "smooth",
  });
}
