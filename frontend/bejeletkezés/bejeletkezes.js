document.addEventListener("DOMContentLoaded", (event) => {
  const formPopup = document.querySelector(".form-popup");
  const belepesGomb = document.getElementById("bejelentkezes");

  belepesGomb.addEventListener("click", (event) => {
    event.preventDefault();

    const felhaszNev = document.querySelector("#felhasz").value;
    const jelszo = document.querySelector("#jelszo").value;

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
      felhasznaloNev: felhaszNev,
      jelszo: jelszo
    });

    var requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: raw,
      redirect: 'follow'
    };

    fetch("http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalok/bejelentkezes", requestOptions)
      .then(response => response.text())
      .then(result => {
        // Sikeres bejelentkezés esetén tárold el az adatokat a local storage-ba
        localStorage.setItem("felhasznaloNev", felhaszNev);
        localStorage.setItem("bejelentkezve", "true");
        console.log("Sikeres bejelentkezés");

        // Egyéb további műveletek vagy átirányítás stb.
      })
      .catch(error => {
        console.log('Hiba történt:', error);
        // Hiba kezelése, pl. felhasználó értesítése
      });
  });
});
