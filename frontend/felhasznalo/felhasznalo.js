const felhaszadatai= document.getElementById('felhasznalo-adatai');
const bejelentkezettFelhasznalo = JSON.parse(localStorage.getItem("bejelentkezes"));

document.addEventListener('DOMContentLoaded', function(){

    felhaszadatai.innerHTML+=
    `
    <div class="felhasznalo-kep">
                    <img src="../kepek/felhasznalo.png" alt="Profil-kep">
                </div>
                <div class="felhasznalo-nev">Mákosguba1</div>
                <div class="felhasznalo-valos-nevek">
                    <div class="felhasznalo-vezeteknev">Gipsz</div>
                    <div class="felhasznalo-keresztnev">Elek</div>
                </div>
    `;

});