// Amikor az oldal betöltődik, várjuk meg a DOMContentLoaded eseményt
document.addEventListener("DOMContentLoaded", (event) => {
  // Kiválasztjuk a form-popup osztályú elemet és a bejelentkezes azonosítójú gombot
  const formPopup = document.querySelector(".form-popup");
  const belepesGomb = document.getElementById("bejelentkezes");

  // A bejelentkezes gombra kattintás eseménykezelője
  belepesGomb.addEventListener("click", (event) => {
    // Megakadályozzuk a gomb alapértelmezett viselkedését (pl. űrlap küldés)
    event.preventDefault();

    // Begyűjtjük a felhasználónév és jelszó értékeit az űrlapról
    const felhaszNev = document.querySelector("#felhasz").value;
    const jelszo = document.querySelector("#jelszo").value;

    // Felkészülünk a fetch kéréshez szükséges fejlécekre és testre
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    // Az adatokat JSON formátumba alakítjuk
    var raw = JSON.stringify({
      felhasznaloNev: felhaszNev,
      jelszo: jelszo
    });

    // Fetch kérés beállításai
    var requestOptions = {
      method: 'GET',  // A kérés típusa: GET
      headers: myHeaders,
      body: raw,  // A kérés testje
      redirect: 'follow'
    };

    // Fetch kérés küldése a szervernek
    fetch("http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalok/bejelentkezes", requestOptions)
      .then(response => response.text())
      .then(result => console.log("sikeres"))
      .catch(error => console.log('error', error));
  });
});
