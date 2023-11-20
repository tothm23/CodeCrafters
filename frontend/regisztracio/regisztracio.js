const formPopup = document.querySelector(".form-popup");
const signupLoginLink = formPopup.querySelectorAll(".bottom-link a");
const regisztracio = document.getElementById("regisztracio");

document.addEventListener("DOMContentLoaded", function () {
    document.body.classList.toggle("show-popup");

    const sikeresElem = document.getElementById("Alert");
    const regisztraciosForm = document.querySelector("form");

    regisztraciosForm.addEventListener("submit", function (event) {
      event.preventDefault();
      const felhasz = document.getElementById("felhasz").value;
      const vezeteknev = document.getElementById("vezeteknev").value;
      const keresztnev = document.getElementById("keresztnev").value;
      const email = document.getElementById("email").value;
      const jelszo = document.getElementById("jelszo").value;

      const inputdata = {
          felhasznaloNev: felhasz,
          keresztNev: keresztnev,
          vezetekNev: vezeteknev,
          email: email,
          jelszo: jelszo

      };
      // regisztráció fetch
      fetch(
        "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalok",
        {
          method: "POST",
          body: JSON.stringify(inputdata),
          headers: {
            "Content-type": "application/json; charset=UTF-8",
          },
        }
      )
      .then((valasz) => {
        console.log('Válasz érkezett:', valasz);
        return valasz.text();
      })
      .then((adat) => {
        alert('Sikeres kérés! Válasz: ' + adat); // Sikeres kérés!
        console.log('Adatok érkeztek:', adat);
      })
      .catch((hiba) => {
        alert('Hiba történt a kérés során: ' + hiba); // Alert hiba
        console.error('Hiba:', hiba);
      });
    });
  });

