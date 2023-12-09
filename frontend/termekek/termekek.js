document.addEventListener("DOMContentLoaded", function () {
  // HTML elemek kiválasztása
  const keresoInput = document.querySelector('.navbar input[type="search"]');
  const minArInput = document.querySelector('.ar input[name="min"]');
  const maxArInput = document.querySelector('.ar input[name="max"]');
  const form = document.querySelector(".navbar form");
  const checkboxok = document.querySelectorAll(".form-check-input");
  const jatekokElem = document.querySelector(".termek-lista#jatekok");

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
  function createCard(kepPath, nev, ar, akcio,id,url) {
    const akciosAr = akcio > 0 ? Math.round(ar - (ar / 100) * akcio) : null;
    
    return `
      <div class="card my-4 col">
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
          <input id="hozzadas" class="my-2 p-2 btn btn-success fs-5" type="button" value="Hozzáadás a kosárhoz" />
        </div>
      </div>
    `;
  }

  function termekekMegjelenitese(adatok) {
    jatekokElem.innerHTML = "";

    for (let i = 0; i < adatok.length; i+=2) {
      jatekokElem.innerHTML +='<div class=row>'+ 
      (createCard(
        `../kepek/jatekok/${adatok[i].kep}`,
        adatok[i].nev,
        adatok[i].ar,
        adatok[i].akcio,
        adatok[i].id,
        "../jatek/jatek.html"
      ))
      +
      (createCard(
        `../kepek/jatekok/${adatok[i+1].kep}`,
        adatok[i+1].nev,
        adatok[i+1].ar,
        adatok[i+1].akcio,
        adatok[i+1].id,
        "../jatek/jatek.html"
      ))+
      '</div>';
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
