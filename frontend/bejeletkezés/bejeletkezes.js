const formPopup = document.querySelector(".form-popup");
const signupLoginLink = formPopup.querySelectorAll(".bottom-link a");
const sikeres = document.getElementById("sikeres");

document.addEventListener("DOMContentLoaded", (event) => {
  const belepes = document.getElementById("bejelentkezes");

  document.body.classList.toggle("show-popup");

  //bejelentkezés
  belepes.addEventListener("click", (event) => {
    event.preventDefault();

    const felhaszNev = document.querySelector("#felhasz").value;
    const jelszo = document.querySelector("#jelszo").value;

    fetch(
      "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalok/bejelentkezes",
      {
        method: "GET",
        body: JSON.stringify({
          felhasznaloNev: felhaszNev,
          jelszo: jelszo,
        }),
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
      }
    )
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
