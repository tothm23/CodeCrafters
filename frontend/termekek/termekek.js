document.addEventListener("DOMContentLoaded", function () {
  // HTML elemek kiválasztása
  const termekekElem = document.getElementById("termekek");
  const keresoInput = document.querySelector('.navbar input[type="search"]');
  const minArInput = document.querySelector('.ar input[name="min"]');
  const maxArInput = document.querySelector('.ar input[name="max"]');
  const form = document.querySelector(".navbar form");

  // Eredeti adatok tárolása
  let eredetiAdatok;

  // Termékek lekérése a szerverről
  fetch(
    "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/termekek"
  )
    .then((valasz) => valasz.json())
    .then((adat) => {
      eredetiAdatok = adat;

      // Kezdeti terméklista megjelenítése
      termekekMegjelenitese(adat);

      // Eseményfigyelők hozzáadása a keresőhöz és az ármezőkhöz
      form.addEventListener("submit", function (esemeny) {
        esemeny.preventDefault();
        szurokAlkalmazasa();
      });

      minArInput.addEventListener("input", szurokAlkalmazasa);
      maxArInput.addEventListener("input", szurokAlkalmazasa);
    })
    .catch((hiba) => alert(hiba));

  // Termékek megjelenítése a HTML-ben
  function createCard(kepPath, nev, ar, akcio) {
    const akciosAr = akcio > 0 ? Math.round(ar - (ar / 100) * akcio) : null;

    return `
      <div class="card my-4">
          <img src="${kepPath}" class="card-img-top w-50" alt="${nev}">
          <div class="card-body">
              <h5 class="card-title">${nev}</h5>
              <p class="card-text ar">${
                akciosAr > 0 ? `<del>${ar} Ft</del>` : `${ar} Ft`
              }</p>
              ${
                akciosAr > 0
                  ? `<p class="card-text akciosar">${akciosAr} Ft</p>`
                  : ""
              }
              <input id="hozzadas" class="my-2 p-2 btn btn-success fs-5" type="button" value="Hozzáadás a kosárhoz" />
          </div>
      </div>
    `; 
  }

  function termekekMegjelenitese(adatok) {
    termekekElem.innerHTML = "";

    for (let i = 0; i < adatok.length / 2; i++) {
      termekekElem.innerHTML += createCard(
        `../kepek/ajandekkartya/${adatok[i].kep}`,
        adatok[i].nev,
        adatok[i].ar,
        adatok[i].akcio
      );
    }

    for (let i = adatok.length / 2; i < adatok.length; i++) {
      termekekElem.innerHTML += createCard(
        `../kepek/jatekok/${adatok[i].kep}`,
        adatok[i].nev,
        adatok[i].ar,
        adatok[i].akcio
      );
    }
  }

  // Szűrők alkalmazása és terméklista frissítése
  function szurokAlkalmazasa() {
    const keresendoSzoveg = keresoInput.value.toLowerCase();
    const minAr = parseFloat(minArInput.value) || 0;
    const maxAr = parseFloat(maxArInput.value) || Infinity;

    // Szűrt adatok kiválasztása az eredeti adatok közül
    const szurtAdatok = eredetiAdatok.filter(
      (elem) =>
        (elem.nev.toLowerCase().includes(keresendoSzoveg) ||
          keresendoSzoveg.includes(elem.nev.toLowerCase())) &&
        elem.ar >= minAr &&
        elem.ar <= maxAr
    );

    // Szűrt termékek megjelenítése
    termekekMegjelenitese(szurtAdatok);
  }

});
