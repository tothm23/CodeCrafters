document.addEventListener('DOMContentLoaded', function () {
    // Eseménykezelő a mynavbar elemekre
    var myNavbarItems = document.querySelectorAll('.mynavbar .dropdown-item');

    myNavbarItems.forEach(function (navbarItem) {
        navbarItem.addEventListener('click', function (event) {
            // Megtaláljuk az id-t a href-ben
            var targetId = this.getAttribute('id');

            // Az aktuális kategória azonosítójának mentése az URL-be
            var url = new URL(window.location.href);
            url.searchParams.set('currentCategory', targetId);
            window.history.pushState({}, '', url);

            // Az oldal betöltése csak a Termékek kategória esetén
            if (targetId === 'termekLink') {
                loadProductPage();
            }
        });
    });

    function parseURL() {
        const url = new URL(window.location);
        const currentCategory = url.searchParams.get('currentCategory');

        if (currentCategory) {
            // Az oldal betöltése csak a Termékek kategória esetén
            if (currentCategory === 'termekLink') {
                loadProductPage();
            }
        }
    }

    function updateURL() {
        const checkboxes = document.querySelectorAll('.form-check-input');
        const params = [];

        checkboxes.forEach((checkbox) => {
            if (checkbox.checked) {
                const linkId = checkbox.parentElement.getAttribute('id');
                params.push(linkId);

                // Az aktuális kategória azonosítójának mentése az URL-be
                const url = new URL(window.location.href);
                url.searchParams.set('currentCategory', linkId);
                window.history.pushState({}, '', url);
            }
        });

        // Oldal betöltése vagy egyéb műveletek a Termékek kategória esetén
        if (params.includes('termekLink')) {
            loadProductPage();
        }
    }

    function loadProductPage() {
        // Oldal betöltése vagy egyéb műveletek a Termékek kategória esetén
        // Például: window.location.href = './termékoldal/termekekoldal.html';
        console.log('Oldal betöltése: ./termékoldal/termekekoldal.html');
    }

    const checkboxes = document.querySelectorAll('.form-check-input');
    checkboxes.forEach((checkbox) => {
        checkbox.addEventListener('change', function () {
            updateURL();
        });
    });

    parseURL();
});



//carouselInner
var games = [
  {
    title: "Játék 1 címe",
    price: "$10",
    description: "Leírás 1",
    image: "./kepek/jatekok/miles.png",
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
  },
  // További játékadatokat itt adhatsz hozzá
];

var carouselInner = document.getElementById("gameCarousel");

games.forEach(function (game, index) {
  var activeClass = index === 0 ? "active" : "";
  carouselInner.innerHTML += `
        <div class="carousel-item ${activeClass}">
            <img src="${game.image}" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
                <h5>${game.title}</h5>
                <p>Ár: ${game.price} | Leírás: ${game.description}</p>
                <!-- További adatokat itt jelenítheted meg -->
            </div>
        </div>
    `;
});
