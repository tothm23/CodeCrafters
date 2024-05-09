const formPopup = document.querySelector(".form-popup");
const signupLoginLink = formPopup.querySelectorAll(".bottom-link a");
const regisztracio = document.getElementById("regisztracio");

document.addEventListener("DOMContentLoaded", function () {
  document.body.classList.toggle("show-popup");

  const regisztraciosForm = document.querySelector("form");

  regisztraciosForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const felhasz = document.getElementById("felhasz").value;
    const vezeteknev = document.getElementById("vezeteknev").value;
    const keresztnev = document.getElementById("keresztnev").value;
    const email = document.getElementById("email").value;
    const jelszo = document.getElementById("jelszo").value;
    const jelszoujra = document.getElementById("jelszoegyezes").value;

    if (jelszo != jelszoujra) {
      alert("A jelszavak nem egyeznek!");
    } else {

      const inputdata = {
        felhasznaloNev: felhasz,
        vezetekNev: vezeteknev,
        keresztNev: keresztnev,
        email: email,
        jelszo: jelszo,
        jogosultsagId: "2"

      };
      // regisztráció fetch
      fetch(
          "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalo", {
            method: "POST",
            body: JSON.stringify(inputdata),
            headers: {
              "Content-type": "application/json; charset=UTF-8",
            },
          }
        )
        .then((valasz) => {
          return valasz.text();
        })
        .then((adat) => {
          if(adat=="Felhasználó hozzáadva!"){
            window.location.href = "../index.html";
          }
        })
        .catch((hiba) => {
          console.error('Hiba:', hiba);
        });
    }


  });
});