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
      title: "Játék 1 címe",
      price: "$10",
      description: "Leírás 1",
      image: "./kepek/jatekok/BF2.webp",
    },
    {
      title: "Játék 2 címe",
      price: "$20",
      description: "Leírás 2",
      image: "./kepek/jatekok/fc24.jpg",
    },
    {
      title: "Játék 3 címe",
      price: "$30",
      description: "Leírás 3",
      image: "./kepek/jatekok/gta5.jpg",
    }
  ];
  
  var carouselInner = document.getElementById("gameCarousel");
  
  games.forEach(function (game, index) {
    var activeClass = index === 0 ? "active" : "";
    var itemClass = "three-cards";
  
    carouselInner.innerHTML += `
          <div class="carousel-item ${activeClass} ${itemClass}">
              <!-- Top Card -->
              <div class="card top">
                  <img src="${game.image}" class="card-img-top" alt="...">
                  <div class="card-body">
                      <h5 class="card-title">${game.title}</h5>
                      <p class="card-text">Ár: ${game.price} | Leírás: ${game.description}</p>
                  </div>
              </div>
  
              <!-- Bottom Cards -->
              <div class="bottom">
                  <div class="card">
                      <!-- Your game content for the first bottom card goes here -->
                      <img src="${game.image}" class="card-img-top" alt="...">
                      <div class="card-body">
                          <h5 class="card-title">${game.title}</h5>
                          <p class="card-text">Ár: ${game.price} | Leírás: ${game.description}</p>
                      </div>
                  </div>
                  <div class="card">
                      <!-- Your game content for the second bottom card goes here -->
                      <img src="${game.image}" class="card-img-top" alt="...">
                      <div class="card-body">
                          <h5 class="card-title">${game.title}</h5>
                          <p class="card-text">Ár: ${game.price} | Leírás: ${game.description}</p>
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
