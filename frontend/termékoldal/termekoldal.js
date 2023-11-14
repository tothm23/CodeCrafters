document.addEventListener("DOMContentLoaded", function() {
    var adatok = {
        "jatekok" :[
            {
                "nevBE": "Witcher 3",
                "arBE": 29.99,
                "leirasBE": "Ez egy fantasztikus játék.",
                "kepBE": "./jatekok/witcher3.jpg",
                "korhatarBE": 12,
                "akcioBE": true,
                "mennyisegraktaronBE": 50,
                "kategoriaIdBE": 1,
                "eszkozIdBE": 3,
                "platformIdBE": 2
            },
            {
                "nevBE": "Játék 2",
                "arBE": 39.99,
                "leirasBE": "Izgalmas játékélmény vár rád.",
                "kepBE": "./jatekok/miles.png",
                "korhatarBE": 16,
                "akcioBE": false,
                "mennyisegraktaronBE": 30,
                "kategoriaIdBE": 2,
                "eszkozIdBE": 1,
                "platformIdBE": 1
            },
            {
                "nevBE": "Játék 3",
                "arBE": 49.99,
                "leirasBE": "Felfedezd a világot ebben a játékban.",
                "kepBE": "./jatekok/csgo.jpg",
                "korhatarBE": 18,
                "akcioBE": true,
                "mennyisegraktaronBE": 20,
                "kategoriaIdBE": 1,
                "eszkozIdBE": 2,
                "platformIdBE": 3
            },
            {
                "nevBE": "Játék 4",
                "arBE": 19.99,
                "leirasBE": "Egyszerű, de szórakoztató játék.",
                "kepBE": "./jatekok/miles.png",
                "korhatarBE": 10,
                "akcioBE": false,
                "mennyisegraktaronBE": 45,
                "kategoriaIdBE": 2,
                "eszkozIdBE": 1,
                "platformIdBE": 2
            },
            {
                "nevBE": "Játék 5",
                "arBE": 59.99,
                "leirasBE": "Merülj el a játék világában.",
                "kepBE": "./jatekok/miles.png",
                "korhatarBE": 20,
                "akcioBE": true,
                "mennyisegraktaronBE": 15,
                "kategoriaIdBE": 1,
                "eszkozIdBE": 3,
                "platformIdBE": 1
            }
        ],
        "ajandekkartyak" :[
            {
                "nevBE": "Ajándék kártya 1",
                "arBE": 50,
                "leirasBE": "Vásárolj kedvedre a boltban ezzel a kártyával.",
                "kepBE": "./ajandekkartya/kartya.png",
                "akcioBE": false,
                "mennyisegraktaronBE": 100,
                "eszkozIdBE": 1,
                "platformIdBE": 2
            },
            {
                "nevBE": "Ajándék kártya 2",
                "arBE": 25,
                "leirasBE": "Kényeztesd magad a boltban ezzel a kártyával.",
                "kepBE": "./ajandekkartya/kartya.png",
                "akcioBE": true,
                "mennyisegraktaronBE": 75,
                "eszkozIdBE": 3,
                "platformIdBE": 1
            },
            {
                "nevBE": "Ajándék kártya 3",
                "arBE": 100,
                "leirasBE": "Válaszd ki a kedvenc termékeidet a boltban ezzel a kártyával.",
                "kepBE": "./ajandekkartya/kartya.png",
                "akcioBE": true,
                "mennyisegraktaronBE": 50,
                "eszkozIdBE": 2,
                "platformIdBE": 3
            },
            {
                "nevBE": "Ajándék kártya 4",
                "arBE": 75,
                "leirasBE": "Vásárolj bármit a boltban ezzel a kártyával.",
                "kepBE": "./ajandekkartya/kartya.png",
                "akcioBE": false,
                "mennyisegraktaronBE": 90,
                "eszkozIdBE": 1,
                "platformIdBE": 1
            },
            {
                "nevBE": "Ajándék kártya 5",
                "arBE": 200,
                "leirasBE": "Engedd szabadjára a vágyaidat ezzel a kártyával.",
                "kepBE": "./ajandekkartya/kartya.png",
                "akcioBE": true,
                "mennyisegraktaronBE": 30,
                "eszkozIdBE": 3,
                "platformIdBE": 2
            }
        ]
    };

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
        url.searchParams.set('categories', params.join('&'));
        window.history.pushState({}, '', url);
    }
});
 