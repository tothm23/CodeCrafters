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
      const datum = document.getElementById("datum").value;
      const email = document.getElementById("email").value;
      const jelszo = document.getElementById("jelszo").value;
      const orszag = document.getElementById("orszag").value;
      const telefonszam = document.getElementById("telefonszam").value;

      const inputdata = {
        felhasznaloNev: felhasz,
        vezetekNev: vezeteknev,
        keresztNev: keresztnev,
        szuletesiDatum: datum,
        email: email,
        jelszo: jelszo,
        orszag: orszag,
        telefon: telefonszam,
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
      .then((valasz) => valasz.text())
      .then((adat) => {
        sikeresElem.style.removeProperty('display');
        if(adat=="Felhasználó hozzáadva!"){
          sikeresElem.style.color = "green";
          sikeresElem.style.backgroundColor = "lightgreen";
          sikeresElem.style.borderColor = "darkgreen";
          sikeresElem.style.display = "block";
        }
        else{
          sikeresElem.style.color = "red";
          sikeresElem.style.backgroundColor = "lightcoral";
          sikeresElem.style.borderColor = "darkred";
          sikeresElem.style.display = "block";
        }
        try {
          const elemzettAdat = JSON.parse(adat);
          sikeresElem.innerHTML = elemzettAdat.message;
        } catch (error) {
          console.log(error);
          sikeresElem.innerHTML = adat;
          
        }
      })
      .catch((hiba) => {
        sikeresElem.innerHTML = hiba;
      });
    });
  });

