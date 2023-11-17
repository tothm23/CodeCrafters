document.addEventListener("DOMContentLoaded", function() {
    // GET kérés
  fetch("http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/termekek")
  .then((valasz) => valasz.json())
  .then((adat) => {
    var termekek = document.getElementById("termekek");

    console.log(adat);
    for (let i = 0; i < adat.length; i++) {
      termekek.innerHTML += `
          <div class="card my-4">
              <img src="../kepek/ajandekkartya/${adat[i].kep}" class="card-img-top w-50" alt="${adat[i].nev}">
                  
              <div class="card-body">
                  <h5 class="card-title">${adat[i].nev}</h5>
                  <p class="card-text ar">${adat[i].ar} Ft</p>
                  <p class="card-text akciosar">${Math.round(adat[i].ar - (adat[i].ar / 100) * adat[i].akcio)}Ft</p>
                  <input id="hozzadas" class="my-2 p-2 btn btn-success fs-5" type="button" value="Hozzáadás a kosárhoz" />
          </div>
      `;
    }
  })
  .catch((hiba) => alert(hiba));

    var termekekHTML = "";
    adatok.jatekok.forEach(function(jatek) {
      termekekHTML += `
        <div class="termekjatek">
          <img src="../kepek/${jatek.kepBE}" alt="${jatek.nevBE}">
          <div class="adatok">
              <div class="nev">${jatek.nevBE}</div>
              <div class="ar">${jatek.arBE} HUF</div>
              <div class="leiras">${jatek.leirasBE}</div>
              ${jatek.akcioBE ? '<div class="akcio">Akcióban!</div>' : ''}
          </div>
        </div>
      `;
    });

    adatok.ajandekkartyak.forEach(function(kartya) {
      termekekHTML += `
        <div class="termekkartyak">
          <img src="../kepek/${kartya.kepBE}" alt="${kartya.nevBE}">
          <div class="adatok">
              <div class="nev">${kartya.nevBE}</div>
              <div class="ar">${kartya.arBE} HUF</div>
              <div class="leiras">${kartya.leirasBE}</div>
              ${kartya.akcioBE ? '<div class="akcio">Akcióban!</div>' : ''}
          </div>
        </div>
      `;
    });
    var termekekDiv = document.querySelector(".termekek");
    termekekDiv.innerHTML = termekekHTML;
    
    /*ár kereső*/
    var minPriceInput = document.querySelector('input[name="min"]');
    var maxPriceInput = document.querySelector('input[name="max"]');
    
    minPriceInput.addEventListener('input', updateProducts);
    maxPriceInput.addEventListener('input', updateProducts);

    function updateProducts() {
        var minPrice = parseFloat(minPriceInput.value) || 0;
        var maxPrice = parseFloat(maxPriceInput.value) || Number.MAX_VALUE;

        var filteredProducts = adatok.jatekok.filter(function(jatek) {
            return jatek.arBE >= minPrice && jatek.arBE <= maxPrice;
        });

        var filteredKartyak = adatok.ajandekkartyak.filter(function(kartya) {
            return kartya.arBE >= minPrice && kartya.arBE <= maxPrice;
        });

        var filteredHTML = "";
        filteredProducts.forEach(function(jatek) {
            filteredHTML += `
                <div class="termekjatek">
                    <img src="../kepek/${jatek.kepBE}" alt="${jatek.nevBE}">
                    <div class="adatok">
                        <div class="nev">${jatek.nevBE}</div>
                        <div class="ar">${jatek.arBE} HUF</div>
                        <div class="leiras">${jatek.leirasBE}</div>
                        ${jatek.akcioBE ? '<div class="akcio">Akcióban!</div>' : ''}
                    </div>
                </div>
            `;
        });

        filteredKartyak.forEach(function(kartya) {
            filteredHTML += `
                <div class="termekkartyak">
                    <img src="../kepek/${kartya.kepBE}" alt="${kartya.nevBE}">
                    <div class="adatok">
                        <div class="nev">${kartya.nevBE}</div>
                        <div class="ar">${kartya.arBE} HUF</div>
                        <div class="leiras">${kartya.leirasBE}</div>
                        ${kartya.akcioBE ? '<div class="akcio">Akcióban!</div>' : ''}
                    </div>
                </div>
            `;
        });

        var termekekDiv = document.querySelector(".termekek");
        termekekDiv.innerHTML = filteredHTML;
    }
  });


  //checkbox manipulálása a navbárból
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

                // Frissítjük az URL-t a kipipált checkbox alapján
                updateURL();
            }
        });
    });

    // Az alábbi részt hozzáadva az URL frissítése a kipipált checkboxok alapján
    function updateURL() {
        const checkboxes = document.querySelectorAll('.form-check-input');
        const params = [];

        checkboxes.forEach((checkbox) => {
            if (checkbox.checked) {
                params.push(checkbox.id.replace('input', ''));
            }
        });

        const url = new URL('../termékoldal/termekoldal.html', window.location.origin);
        url.searchParams.set('categories', params.join('re'));
        window.history.pushState({}, '', url);
    }
});
 