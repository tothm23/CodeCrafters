document.addEventListener('DOMContentLoaded', function () {
            const Kosartartalma = document.getElementById('cart-items');

            // Kinyerjük a bejelentkezési adatokat a localStorage-ból
            const bejelentkezettFelhasznalo = JSON.parse(localStorage.getItem("bejelentkezes"));
            
            console.log(bejelentkezettFelhasznalo.id);
            // Ellenőrizzük, hogy van-e bejelentkezett felhasználó
            if ( /*bejelentkezettFelhasznalo &&*/ bejelentkezettFelhasznalo.id) {
                // Ha van bejelentkezett felhasználó, akkor használd az id-t a fetch hívásban
                fetch(`http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/kosar/${bejelentkezettFelhasznalo.id}`,)
                    .then(response => response.json())
                    .then(result => {
                        // Itt további kezelése a kapott adatoknak
                        kosartartalma(result);
                        console.log(result);
                    })
                    .catch(error => console.error(error));
            } else {
                // Ha nincs bejelentkezett felhasználó, valamilyen hiba kezelése vagy irányítás
                console.error("Nincs bejelentkezett felhasználó");
            }

            function kosartartalma(adatok) {
                Kosartartalma.innerHTML = "";

                for (let i = 0; i < adatok.length; i++) {
                    Kosartartalma.innerHTML += `
            <div class="kosar-row">
                <div class="kosar-tetel kosar-column">
                    <img class="kosar-tetel-kep" src="../kepek/jatekok/${adatok[i].kep}" width="100" height="100">
                    <span class="kosar-tetel-nev">${adatok[i].nev}</span>
                </div>
                <span class="kosar-ar kosar-column">${adatok[i].vegosszeg}</span>
                <div class="kosar-torles kosar-column"> 
                    <button class="btn btn-danger" type="button">X</button>
                </div>
            </div>
        `;
        }
    }
});