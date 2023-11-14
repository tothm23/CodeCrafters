const link = "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/jatek";

const kep = document.getElementById("kep");
const nev = document.getElementById("nev");
const eszkoz = document.getElementById("eszkoz");
const platform = document.getElementById("platform");
const korhatar = document.getElementById("korhatar");
const elerheto = document.getElementById("elerheto");
const eredeti_ar = document.getElementById("eredeti-ar");
const akcios_ar = document.getElementById("akcios-ar");
const leiras = document.getElementById("leiras");

// Kiolvassa a paraméterek az URL-ből
let parameterek = new URL(document.location).searchParams;

// Kiolvassa az id paramétert
let id = parseInt(parameterek.get("id"));

// GET kérés
fetch(`${link}/${id}`)
  .then((response) => response.json())
  .then((data) => {
    if (data.id) {
      // kep.innerHTML = data.nev;
      // kep.setAttribute("src", data.kep);

      nev.innerHTML = data.nev;
      eszkoz.innerHTML = data.eszkoz;
      platform.innerHTML = data.platform;

      // Korhatár színe
      switch (data.korhatar) {
        case 3:
          korhatar.style.border = "3px solid #a5c301";
          break;
        case 7:
          korhatar.style.border = "3px solid #a5c301";
          break;
        case 12:
          korhatar.style.border = "3px solid #f7a100";
          break;
        case 16:
          korhatar.style.border = "3px solid #f7a100";
          break;
        case 18:
          korhatar.style.border = "3px solid #e4001b";
        default:
          break;
      }

      korhatar.style.borderRadius = "50%";
      korhatar.innerHTML = data.korhatar;

      // Raktáron
      if (data.mennyisegraktaron == 0) {
        elerheto.innerHTML = "elérhető";
        elerheto.style.color = "#95e72d";
      } else {
        elerheto.innerHTML = "nem elérhető";
        elerheto.style.color = "#ff0000";
      }

      eredeti_ar.innerHTML = data.ar + " Ft";

      // Akciós ár egész számra kerekítve
      akcios_ar.innerHTML = Math.round(data.ar - (data.ar / 100) * data.akcio) + " Ft";
      leiras.innerHTML = data.leiras;
    } else {
      alert("A játék nem található!");
    }
  });
