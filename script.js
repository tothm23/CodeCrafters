function toggleElem() {
    var elem = document.getElementById("elrejtettElem");
    if (elem.style.display === "none") {
      elem.style.display = "block";
    } else {
      elem.style.display = "none";
    }
  }

document.addEventListener("DOMContentLoaded", function() {
    var adatok = {
        "jatekok" :[
            {
                "nevBE": "Játék 1",
                "arBE": 29.99,
                "leirasBE": "Ez egy fantasztikus játék.",
                "kepBE": "jatek1.jpg",
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
                "kepBE": "jatek2.jpg",
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
                "kepBE": "jatek3.jpg",
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
                "kepBE": "jatek4.jpg",
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
                "kepBE": "jatek5.jpg",
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
                "kepBE": "kartya1.jpg",
                "akcioBE": false,
                "mennyisegraktaronBE": 100,
                "eszkozIdBE": 1,
                "platformIdBE": 2
            },
            {
                "nevBE": "Ajándék kártya 2",
                "arBE": 25,
                "leirasBE": "Kényeztesd magad a boltban ezzel a kártyával.",
                "kepBE": "kartya2.jpg",
                "akcioBE": true,
                "mennyisegraktaronBE": 75,
                "eszkozIdBE": 3,
                "platformIdBE": 1
            },
            {
                "nevBE": "Ajándék kártya 3",
                "arBE": 100,
                "leirasBE": "Válaszd ki a kedvenc termékeidet a boltban ezzel a kártyával.",
                "kepBE": "kartya3.jpg",
                "akcioBE": true,
                "mennyisegraktaronBE": 50,
                "eszkozIdBE": 2,
                "platformIdBE": 3
            },
            {
                "nevBE": "Ajándék kártya 4",
                "arBE": 75,
                "leirasBE": "Vásárolj bármit a boltban ezzel a kártyával.",
                "kepBE": "kartya4.jpg",
                "akcioBE": false,
                "mennyisegraktaronBE": 90,
                "eszkozIdBE": 1,
                "platformIdBE": 1
            },
            {
                "nevBE": "Ajándék kártya 5",
                "arBE": 200,
                "leirasBE": "Engedd szabadjára a vágyaidat ezzel a kártyával.",
                "kepBE": "kartya5.jpg",
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
        <div class="termek">
          <img src="./kepek/${jatek.kepBE}" alt="${jatek.nevBE}">
          <div class="nev">${jatek.nevBE}</div>
          <div class="ar">${jatek.arBE} HUF</div>
          <div class="leiras">${jatek.leirasBE}</div>
          ${jatek.akcioBE ? '<div class="akcio">Akcióban!</div>' : ''}
        </div>
      `;
    });

    adatok.ajandekkartyak.forEach(function(kartya) {
      termekekHTML += `
        <div class="termek">
          <img src="./kepek/${kartya.kepBE}" alt="${kartya.nevBE}">
          <div class="nev">${kartya.nevBE}</div>
          <div class="ar">${kartya.arBE} HUF</div>
          <div class="leiras">${kartya.leirasBE}</div>
          ${kartya.akcioBE ? '<div class="akcio">Akcióban!</div>' : ''}
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
                    <img src="./kepek/${jatek.kepBE}" alt="${jatek.nevBE}">
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
                    <img src="./kepek/${kartya.kepBE}" alt="${kartya.nevBE}">
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