//checkbox
document.addEventListener('DOMContentLoaded', function () {
    // Eseménykezelő a mynavbar elemekre
    var myNavbarItems = document.querySelectorAll('.mynavbar .gomblink');

    myNavbarItems.forEach(function (navbarItem) {
        navbarItem.addEventListener('click', function (event) {
            // Megtaláljuk az id-t a href-ben
            var targetId = this.getAttribute('id');

            // Megtaláljuk a megfelelő checkboxot
            var checkboxId = targetId + 'input';
            var checkbox = document.getElementById(checkboxId);

            // Ellenőrizzük, hogy a checkbox megtalálható-e
            if (checkbox) {
                // Beállítjuk a checkbox állapotát (kipipáljuk)
                checkbox.checked = true;
            }
        });
    });
});

//carouselInner
var games = [
    { title: "Játék 1 címe", price: "$10", description: "Leírás 1", image: "./kepek/jatekok/miles.png" },
    { title: "Játék 2 címe", price: "$20", description: "Leírás 2", image: "./kepek/jatekok/fc24.jpg" },
    { title: "Játék 3 címe", price: "$30", description: "Leírás 3", image: "./kepek/jatekok/gta5.jpg" }
    // További játékadatokat itt adhatsz hozzá
];

var carouselInner = document.getElementById("gameCarousel");

games.forEach(function(game, index) {
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
