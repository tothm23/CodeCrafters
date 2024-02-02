const link = "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/jatek";

const img = document.getElementById("img");
const game_name = document.getElementById("game_name");
const device = document.getElementById("device");
const platform = document.getElementById("platform");
const age = document.getElementById("age");
const available = document.getElementById("available");
const price = document.getElementById("price");
const sale_price = document.getElementById("sale_price");
const desc = document.getElementById("desc");

// Kiolvassa a paraméterek az URL-ből
let parameterek = new URL(document.location).searchParams;

// Kiolvassa az id paramétert
let id = parseInt(parameterek.get("id"));

// GET kérés
fetch(`${link}/${id}`)
  .then((response) => response.json())
  .then((data) => {
    if (data.id) {
      console.log(data)
      img.innerHTML = data.nev;
      var src='../img/games/'+data.kep;
      img.setAttribute("src", src);
      
      game_name.innerHTML = data.nev;
      device.innerHTML = data.eszkoz;
      platform.innerHTML = data.platform;

      // Korhatár színe
      switch (data.korhatar) {
        case 3:
          age.style.border = "3px solid #a5c301";
          break;
        case 7:
          age.style.border = "3px solid #a5c301";
          break;
        case 12:
          age.style.border = "3px solid #f7a100";
          break;
        case 16:
          age.style.border = "3px solid #f7a100";
          break;
        case 18:
          age.style.border = "3px solid #e4001b";
        default:
          break;
      }

      age.style.borderRadius = "50%";
      age.innerHTML = data.korhatar;

      // Raktáron
      if (data.mennyisegraktaron == 0) {
        available.innerHTML = "elérhető";
        available.style.color = "#95e72d";
      } else {
        available.innerHTML = "nem elérhető";
        available.style.color = "#ff0000";
      }

      price.innerHTML = data.ar + " Ft";
      //Ár megjelenítése

      if(data.akcio > 0){
        price.innerHTML=`<p class="card-text price" style="text-decoration: line-through;">${data.ar} Ft</p>`; 
      }
      else{
        price.innerHTML=`<p class="card-text price">${data.ar} Ft</p>`;
      }
      
      if(data.akcio > 0){
        // Akciós ár egész számra kerekítve
        sale_price.innerHTML = `<p class="card-text saleprice">${Math.round(data.ar - (data.ar / 100) * data.akcio)} Ft</p>`;
      }
      else{ 
        sale_price.innerHTML = "";
      }
      add_btn.innerHTML=add_btn(id);
      
      desc.innerHTML = data.leiras;
    } else {
      alert("A játék nem található!");
    }
  });
