document.addEventListener("DOMContentLoaded", function () {
  // HTML elemek kiválasztása
  const searchIn = document.querySelector('.navbar input[type="search"]');
  const minpriceIn = document.querySelector('.price input[name="min"]');
  const maxpriceIn = document.querySelector('.price input[name="max"]');
  const minpriceIn2 = document.querySelector('.price2 input[name="min"]');
  const maxpriceIn2 = document.querySelector('.price2 input[name="max"]');

  const form = document.querySelector(".navbar form");

  const checkboxok = document.querySelectorAll(".form-check-input");
  
  const product_games = document.querySelector(".products-list#games");
  const searched_text = localStorage.getItem('searched_text');

  const sale_checkbox = document.getElementById("sale_checkbox");
  const sale_checkbox2 = document.getElementById("sale_checkbox2");
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
      "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/products"
    )
    .then((response) => response.json())
    .then((data) => {
      original_data = data;

      // Kezdeti terméklista megjelenítése
      show_products(data);
      // Az oldal betöltésekor alkalmazza a szűrőket de csak mikor a betöltöt addatal egyenlő hogy ne fusson le mindig
      if (searched_text == searchIn.value) apply_fillters();
    })
    .catch((error) => alert(error));

  // Termékek megjelenítése a HTML-ben
  function createCard(imgPath,ageLimit, name, price, discount, id, url) {
    const discount_price = discount > 0 ? Math.round(price - (price / 100) * discount) : null;

    return `
      <div class="card my-4 flex-column flex-lg-row">
        <a href="${url}?id=${id}"><img src="${imgPath}" class="card-img-top" alt="${name}"></a>
        <div class="card-body">
          <h5 class="card-title">${name}</h5>
          <p class="card-text price">${
            discount_price > 0 ? `<del>${price} Ft</del>` : `${price} Ft`
          }</p>
          ${
            discount_price > 0
              ? `<p class="card-text discount_price">${discount_price} Ft</p>`
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
          `../img/games/${data[i].image}`,
          data[i].ageLimit,
          data[i].gameName,
          data[i].price,
          data[i].discount,
          data[i].id,
          "../game/game.html"
        ));
    }
  }

  // Szűrők alkalmazása és terméklista frissítése
  function apply_fillters() {
    const searched_text = searchIn.value.toLowerCase();
    const min_price = parseFloat(minpriceIn.value) || 0;
    const max_price = parseFloat(maxpriceIn.value) || Infinity;
    console.log("min:" + min_price + " max:" + max_price);
    const checked_platform = Array.from(document.querySelectorAll('.platform input:checked'))
      .map(checkbox => checkbox.value.toLowerCase());

    const checked_device = Array.from(document.querySelectorAll('.device input:checked'))
      .map(checkbox => checkbox.value.toLowerCase());
    // Csak akkor alkalmazza a szűrést, ha a checkbox be van jelölve
    const filltered_data = original_data.filter(
      (elem) =>
      (elem.gameName.toLowerCase().includes(searched_text) ||
        searched_text.includes(elem.gameName.toLowerCase())) &&
      elem.price >= min_price &&
      elem.price <= max_price &&
      //platformra szűrés
      (checked_platform.length === 0 || checked_platform.includes(elem.platform.toLowerCase())) &&
      //eszközre szűrés
      (checked_device.length === 0 || checked_device.includes(elem.device.toLowerCase())) &&
      //akciósra szűrés
      (sale_checkbox.checked == false || elem.discount > 0)
    );
    // Szűrt termékek megjelenítése
    show_products(filltered_data);
  }
});