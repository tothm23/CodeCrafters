document.addEventListener("DOMContentLoaded", function () {
  // HTML elemek kiválasztása
  const searchIn = document.querySelector('.navbar input[type="search"]');
  const minpriceIn = document.querySelector('.ar input[name="min"]');
  const maxpriceIn = document.querySelector('.ar input[name="max"]');
  const minpriceIn2 = document.querySelector('.ar2 input[name="min"]');
  const maxpriceIn2 = document.querySelector('.ar2 input[name="max"]');

  const form = document.querySelector(".navbar form");

  const checkboxok = document.querySelectorAll(".form-check-input");
  
  const product_games = document.querySelector(".termek-lista#jatekok");
  const searched_text = localStorage.getItem('keresesSzoveg');

  const sale_checkbox = document.getElementById("AkciosCheckbox");
  const sale_checkbox2 = document.getElementById("AkciosCheckbox2");
  //akcio input mindig egy forma  
  sale_checkbox.addEventListener("change", function (e) {
    if (sale_checkbox.checked == true) sale_checkbox2.checked = true;
    else sale_checkbox2.checked = false;

  });

  sale_checkbox2.addEventListener("change", function (e) {
    if (sale_checkbox2.checked == true) sale_checkbox.checked = true;
    else if (sale_checkbox2.checked == false) sale_checkbox.checked = false;

  });


  // Eredeti adatok tárolása
  let original_data;
  //keresoben az érték új ablak menyitása esetén elvan tárolva és local storageből betöltjük
  if (searched_text) {
    searchIn.value = searched_text;
  }

  // Eseményfigyelők hozzáadása a keresőhöz és az ármezőkhöz
  form.addEventListener("submit", function (esemeny) {
    esemeny.preventDefault();
    apply_fillters();
  });


  minpriceIn.addEventListener("input", function () {
    minpriceIn2.value = minpriceIn.value;
    apply_fillters();
  });

  minpriceIn2.addEventListener("input", function () {
    minpriceIn.value = minpriceIn2.value;
    apply_fillters();
  });

  maxpriceIn.addEventListener("input", function () {
    maxpriceIn2.value = maxpriceIn.value;
    apply_fillters();
  });

  maxpriceIn2.addEventListener("input", function () {
    maxpriceIn.value = maxpriceIn2.value;
    apply_fillters();
  });

  checkboxok.forEach(function (checkbox) {
    checkbox.addEventListener("change", apply_fillters);
  });

  // Termékek lekérése a szerverről
  fetch(
      "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/termekek"
    )
    .then((valasz) => valasz.json())
    .then((adat) => {
      original_data = adat;

      // Kezdeti terméklista megjelenítése
      show_products(adat);
      // Az oldal betöltésekor alkalmazza a szűrőket de csak mikor a betöltöt addatal egyenlő hogy ne fusson le mindig
      if (searched_text == searchIn.value) apply_fillters();
    })
    .catch((hiba) => alert(hiba));

  // Termékek megjelenítése a HTML-ben
  function createCard(imgPath, name, price, sale, id, url) {
    const saleprice = sale > 0 ? Math.round(price - (price / 100) * sale) : null;

    return `
      <div class="card my-4 flex-column flex-lg-row">
        <a href="${url}?id=${id}"><img src="${imgPath}" class="card-img-top" alt="${name}"></a>
        <div class="card-body">
          <h5 class="card-title">${name}</h5>
          <p class="card-text ar">${
            saleprice > 0 ? `<del>${price} Ft</del>` : `${price} Ft`
          }</p>
          ${
            saleprice > 0
              ? `<p class="card-text saleprice">${saleprice} Ft</p>`
              : ""
          }
          ${add_btn(id)}
        </div>
      </div>
    `;
  }

  function show_products(data) {
    product_games.innerHTML = "";

    for (let i = 0; i < data.length; i++) {
      product_games.innerHTML +=
        (createCard(
          `../img/games/${data[i].kep}`,
          data[i].nev,
          data[i].ar,
          data[i].akcio,
          data[i].id,
          "../jatek/jatek.html"
        ));
    }
  }

  // Szűrők alkalmazása és terméklista frissítése
  function apply_fillters() {
    const searched_text = searchIn.value.toLowerCase();
    const minAr = parseFloat(minpriceIn.value) || 0;
    const maxAr = parseFloat(maxpriceIn.value) || Infinity;
    console.log("min:" + minAr + " max:" + maxAr);
    const kivalasztottPlatformok = Array.from(document.querySelectorAll('.platform input:checked'))
      .map(checkbox => checkbox.value.toLowerCase());

    const kivalasztottEszkozok = Array.from(document.querySelectorAll('.eszkoz input:checked'))
      .map(checkbox => checkbox.value.toLowerCase());
    // Csak akkor alkalmazza a szűrést, ha a checkbox be van jelölve
    const szurtAdatok = original_data.filter(
      (elem) =>
      (elem.nev.toLowerCase().includes(searched_text) ||
        searched_text.includes(elem.nev.toLowerCase())) &&
      elem.ar >= minAr &&
      elem.ar <= maxAr &&
      //platformra szűrés
      (kivalasztottPlatformok.length === 0 || kivalasztottPlatformok.includes(elem.platform.toLowerCase())) &&
      //eszközre szűrés
      (kivalasztottEszkozok.length === 0 || kivalasztottEszkozok.includes(elem.eszkoz.toLowerCase())) &&
      //akciósra szűrés
      (sale_checkbox.checked == false || elem.akcio > 0)
    );
    // Szűrt termékek megjelenítése
    show_products(szurtAdatok);
  }
});