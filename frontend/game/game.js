const link = "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/game";

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
      img.innerHTML;
      var src='../img/games/'+data.image;
      img.setAttribute("src", src);
      
      game_name.innerHTML = data.gameName;
      device.innerHTML = data.deviceName;
      platform.innerHTML = data.platformName;

      // Korhatár színe
      switch (data.ageLimit) {
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
      age.innerHTML = data.ageLimit;

      // Raktáron
      if (data.inStock > 0) {
        available.innerHTML = "elérhető";
        available.style.color = "#95e72d";
      } else {
        available.innerHTML = "nem elérhető";
        available.style.color = "#ff0000";
      }

      price.innerHTML = data.price + " Ft";
      //Ár megjelenítése
      
      if(data.discount > 0){
        price.innerHTML=`<p class="card-text price" style="text-decoration: line-through;">${data.price} Ft</p>`; 
      }
      else{
        price.innerHTML=`<p class="card-text price">${data.price} Ft</p>`;
      }
      
      if(data.discount > 0){
        // Akciós ár egész számra kerekítve
        sale_price.innerHTML = `<p class="card-text saleprice">${Math.round(data.price - (data.price / 100) * data.discount)} Ft</p>`;
      }
      else{ 
        sale_price.innerHTML = "";
      }
      document.getElementById("add_btn").innerHTML=add_btn(id);
      
      desc.innerHTML = data.description;
    } else {
      alert("A játék nem található!");
    }
  });
