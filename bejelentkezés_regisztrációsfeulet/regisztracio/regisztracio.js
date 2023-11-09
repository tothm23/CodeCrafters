const formPopup = document.querySelector(".form-popup");
const signupLoginLink = formPopup.querySelectorAll(".bottom-link a");
const regisztracio = document.getElementById('regisztracio');

const sikeresElem = document.getElementById("sikeresAlert");
const regisztraciosForm = document.querySelector("form");


document.body.classList.toggle("show-popup");

document.addEventListener("DOMContentLoaded", function () {
    document.body.classList.toggle("show-popup");
  
    const sikeresElem = document.getElementById("sikeresAlert"); // Módosítottuk a változó nevét
    const regisztraciosForm = document.querySelector("form");
  
    // regisztráció fetch
    regisztraciosForm.addEventListener("submit", async (event) => {
      event.preventDefault(); // Az űrlap alapértelmezett működésének megakadályozása
  
    const felhasz = document.getElementById('felhasz').value;
    const vezeteknev = document.getElementById('vezeteknev').value;
    const keresztnev = document.getElementById('keresztnev').value;
    const datum = document.getElementById('datum').value;
    const email = document.getElementById('email').value;
    const jelszo = document.getElementById('jelszo').value;
    const orszag = document.getElementById('orszag').value;
    const telefonszam = document.getElementById('telefonszam').value;
  
      const inputdata = {
        felhasznaloNev: felhasz,
        vezetekNev: vezeteknev,
        keresztNev: keresztnev,
        szuletesiDatum: datum,
        email: email,
        jelszo: jelszo,
        orszag: orszag,
        telefon: telefonszam
      };
  
      try {
        const valasz = await fetch(
          "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalok",
          {
            method: "POST",
            body: JSON.stringify(inputdata),
            headers: {
              "Content-type": "application/json; charset=UTF-8",
            },
          }
        );
  
        if (valasz.ok) {
          const adat = await valasz.json();
          sikeresElem.textContent = adat.message;
          sikeresElem.style.display = "block";
  
          // Alert üzenet megjelenítése
          alert("Sikeres: " + adat.message);
        } else {
          throw new Error("A regisztráció nem sikerült.");
        }
      } catch (hiba) {
        sikeresElem.textContent = hiba.message;
        console.error("Hiba:", hiba);
  
        // Alert üzenet megjelenítése hiba esetén
        alert("Hiba: " + hiba.message);
      }
    });
  });
  