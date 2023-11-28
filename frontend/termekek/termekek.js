document.addEventListener("DOMContentLoaded", function () {
  // HTML elemek kiválasztása
  const keresoInput = document.querySelector('.navbar input[type="search"]');
  const minArInput = document.querySelector('.ar input[name="min"]');
  const maxArInput = document.querySelector('.ar input[name="max"]');
  const form = document.querySelector(".navbar form");
  const checkboxok = document.querySelectorAll(".form-check-input");
  const jatekokElem = document.querySelector(".termek-lista#jatekok");
  const ajandekKartyakElem = document.querySelector(
    ".termek-lista#ajandekKartyak"
  );

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
      checkboxok.forEach(function (checkbox) {
        checkbox.addEventListener("change", szurokAlkalmazasa);
      });
    })
    .catch((hiba) => alert(hiba));

  // Termékek megjelenítése a HTML-ben
  function createCard(kepPath, nev, ar, akcio, kategoria) {
    const akciosAr = akcio > 0 ? Math.round(ar - (ar / 100) * akcio) : null;
    if (
      (kategoria.toLowerCase() === "ajandekkartyak" && document.getElementById("ajandekKartyakCheckbox").checked==true) ||
      (kategoria.toLowerCase() === "jatekok" && document.getElementById("jatekokCheckbox").checked==true) || 
      (document.getElementById("jatekokCheckbox").checked==false && document.getElementById("ajandekKartyakCheckbox").checked==false) 
    ) {
      return `
      <div class="card my-4 ${kategoria}">
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
    } else {
      return ""; // Üres string, mert nem kell kártyát létrehozni
    }
  }

  function termekekMegjelenitese(adatok) {
    jatekokElem.innerHTML = "";
    ajandekKartyakElem.innerHTML = "";
    for (let i = 0; i < adatok.length / 2; i++) {
      ajandekKartyakElem.innerHTML += createCard(
        `../kepek/ajandekkartya/${adatok[i].kep}`,
        adatok[i].nev,
        adatok[i].ar,
        adatok[i].akcio,
        "ajandekKartyak"
      );
    }

    for (let i = adatok.length / 2; i < adatok.length; i++) {
      jatekokElem.innerHTML += createCard(
        `../kepek/jatekok/${adatok[i].kep}`,
        adatok[i].nev,
        adatok[i].ar,
        adatok[i].akcio,
        "jatekok"
      );
    }
  }

  // Szűrők alkalmazása és terméklista frissítése
  function szurokAlkalmazasa() {
    const keresendoSzoveg = keresoInput.value.toLowerCase();
    const minAr = parseFloat(minArInput.value) || 0;
    const maxAr = parseFloat(maxArInput.value) || Infinity;
  
    const kivalasztottPlatformok = Array.from(document.querySelectorAll('.platform input:checked'))
      .map(checkbox => checkbox.value.toLowerCase());
  
    const kivalasztottEszkozok = Array.from(document.querySelectorAll('.eszkoz input:checked'))
      .map(checkbox => checkbox.value.toLowerCase());
    const kivalasztottAkcios=document.getElementById("AkciosCheckbox");

    // Csak akkor alkalmazza a szűrést, ha a checkbox be van jelölve
    const szurtAdatok = eredetiAdatok.filter(
      (elem) =>
        (elem.nev.toLowerCase().includes(keresendoSzoveg) ||
          keresendoSzoveg.includes(elem.nev.toLowerCase())) &&
        elem.ar >= minAr &&
        elem.ar <= maxAr &&
        //platformra szűrés
        (kivalasztottPlatformok.length === 0 || kivalasztottPlatformok.includes(elem.platform.toLowerCase())) &&
        //eszközre szűrés
        (kivalasztottEszkozok.length === 0 || kivalasztottEszkozok.includes(elem.eszkoz.toLowerCase())) &&
        //akciósra szűrés
        (kivalasztottAkcios.checked == false || elem.akcio > 0)
    );
    // Szűrt termékek megjelenítése
    termekekMegjelenitese(szurtAdatok);
  }
});
