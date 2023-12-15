document.addEventListener("DOMContentLoaded", function () {
  // HTML elemek kiválasztása
  const keresoInput = document.querySelector('.navbar input[type="search"]');
  const minArInput = document.querySelector('.ar input[name="min"]');
  const maxArInput = document.querySelector('.ar input[name="max"]');
  const minArInput2 = document.querySelector('.ar2 input[name="min"]');
  const maxArInput2 = document.querySelector('.ar2 input[name="max"]');

  const form = document.querySelector(".navbar form");

  const checkboxok = document.querySelectorAll(".form-check-input");
  const checkboxok2 = document.querySelectorAll(".form-check-input2");
  
  const jatekokElem = document.querySelector(".termek-lista#jatekok");
  const keresoSzoveg = localStorage.getItem('keresesSzoveg');

  const kivalasztottAkcios = document.getElementById("AkciosCheckbox");
  const kivalasztottAkcios2 = document.getElementById("AkciosCheckbox2");

  kivalasztottAkcios.addEventListener("change", function (e) {
    if (kivalasztottAkcios.checked == true) kivalasztottAkcios2.checked = true;
    else kivalasztottAkcios2.checked = false;

  });

  kivalasztottAkcios2.addEventListener("change", function (e) {
    if (kivalasztottAkcios2.checked == true) kivalasztottAkcios.checked = true;
    else if (kivalasztottAkcios2.checked == false) kivalasztottAkcios.checked = false;

  });


  // Eredeti adatok tárolása
  let eredetiAdatok;
  //keresoben az érték új ablak menyitása esetén elvan tárolva és local storageből betöltjük
  if (keresoSzoveg) {
    keresoInput.value = keresoSzoveg;
  }

  // Eseményfigyelők hozzáadása a keresőhöz és az ármezőkhöz
  form.addEventListener("submit", function (esemeny) {
    esemeny.preventDefault();
    szurokAlkalmazasa();
  });


  minArInput.addEventListener("input", function () {
    minArInput2.value = minArInput.value;
    szurokAlkalmazasa();
  });

  minArInput2.addEventListener("input", function () {
    minArInput.value = minArInput2.value;
    szurokAlkalmazasa();
  });

  maxArInput.addEventListener("input", function () {
    maxArInput2.value = maxArInput.value;
    szurokAlkalmazasa();
  });

  maxArInput2.addEventListener("input", function () {
    maxArInput.value = maxArInput2.value;
    szurokAlkalmazasa();
  });

  checkboxok.forEach(function (checkbox) {
    checkbox.addEventListener("change", szurokAlkalmazasa);
  });

  // Termékek lekérése a szerverről
  fetch(
      "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/termekek"
    )
    .then((valasz) => valasz.json())
    .then((adat) => {
      eredetiAdatok = adat;

      // Kezdeti terméklista megjelenítése
      termekekMegjelenitese(adat);
      // Az oldal betöltésekor alkalmazza a szűrőket de csak mikor a betöltöt addatal egyenlő hogy ne fusson le mindig
      if (keresoSzoveg == keresoInput.value) szurokAlkalmazasa();
    })
    .catch((hiba) => alert(hiba));

  // Termékek megjelenítése a HTML-ben
  function createCard(kepPath, nev, ar, akcio, id, url) {
    const akciosAr = akcio > 0 ? Math.round(ar - (ar / 100) * akcio) : null;

    return `
      <div class="card my-4 flex-column flex-lg-row">
        <a href="${url}?id=${id}"><img src="${kepPath}" class="card-img-top" alt="${nev}"></a>
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
          ${hozzaadasGomb(id)}
        </div>
      </div>
    `;
  }

  function termekekMegjelenitese(adatok) {
    jatekokElem.innerHTML = "";

    for (let i = 0; i < adatok.length; i++) {
      jatekokElem.innerHTML +=
        (createCard(
          `../kepek/jatekok/${adatok[i].kep}`,
          adatok[i].nev,
          adatok[i].ar,
          adatok[i].akcio,
          adatok[i].id,
          "../jatek/jatek.html"
        ));
    }
  }

  // Szűrők alkalmazása és terméklista frissítése
  function szurokAlkalmazasa() {
    const keresendoSzoveg = keresoInput.value.toLowerCase();
    const minAr = parseFloat(minArInput.value) || 0;
    const maxAr = parseFloat(maxArInput.value) || Infinity;
    console.log("min:" + minAr + " max:" + maxAr);
    const kivalasztottPlatformok = Array.from(document.querySelectorAll('.platform input:checked'))
      .map(checkbox => checkbox.value.toLowerCase());

    const kivalasztottEszkozok = Array.from(document.querySelectorAll('.eszkoz input:checked'))
      .map(checkbox => checkbox.value.toLowerCase());
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