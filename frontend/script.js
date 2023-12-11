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
              <input id="hozzadas" class="my-2 p-2 btn btn-success fs-5" type="button" value="Hozzáadás a kosárhoz" />
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

//kosar gomb
let dbszam = document.getElementsByClassName("darabszam");
var hozzadasButton = document.getElementById("hozzadas");
hozzadasButton.addEventListener("click", function () {
  dbszam.style.display = block;
  dbszam.innerHTML = parseInt(dbszam.innerHTML) + 1;
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
