document.addEventListener("DOMContentLoaded", (event) => {
  const formPopup = document.querySelector(".form-popup");
  const belepesGomb = document.getElementById("bejelentkezes");

  // A show-popup class hozzáadása vagy eltávolítása a body elemhez a DOMContentLoaded eseménykor
  document.body.classList.toggle("show-popup");

  // Bejelentkezés gombra kattintás eseménykezelője
  belepesGomb.addEventListener("click", (event) => {
    event.preventDefault();

    // Felhasználónév és jelszó begyűjtése az űrlapról
    const felhaszNev = document.querySelector("#felhasz").value;
    const jelszo = document.querySelector("#jelszo").value;

    // GET kérés küldése a szervernek
    fetch(`http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalok/bejelentkezes?felhasznaloNev=${felhaszNev}&jelszo=${jelszo}`, {
      method: "GET",
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    })
      .then((response) => {
        if (response.ok) {
          // Sikeres bejelentkezés, itt kezelheted a sikeres bejelentkezés eseményét
          console.log("Sikeres bejelentkezés!");
          window.location.href = "../index.html";
        } else {
          // Sikertelen bejelentkezés, itt kezelheted a sikertelen bejelentkezés eseményét
          console.error("Sikertelen bejelentkezés!");
        }
      })
      .catch((error) => {
        // Hibakezelés, ha valami nem működik a kérés során
        console.error("Hiba történt a bejelentkezés során: ", error);
      });
  });
});
