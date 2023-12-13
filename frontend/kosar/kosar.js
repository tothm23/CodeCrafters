document.addEventListener('DOMContentLoaded', function () {
    const Kosartartalma = document.getElementById('kosartartalma');
    const vegosszeg = document.getElementById('vegosszeg');
    // Kinyerjük a bejelentkezési adatokat a localStorage-ból
    const bejelentkezettFelhasznalo = JSON.parse(localStorage.getItem("bejelentkezes"));

    console.log(bejelentkezettFelhasznalo && bejelentkezettFelhasznalo.id);
    // Ellenőrizzük, hogy van-e bejelentkezett felhasználó
    if (bejelentkezettFelhasznalo.id) {
        // Ha van bejelentkezett felhasználó, akkor használd az id-t a fetch hívásban
        fetch(`http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/kosar/${bejelentkezettFelhasznalo.id}`, )
            .then(response => response.json())
            .then(result => {
                // Itt további kezelése a kapott adatoknak
                kosartartalma(result);
                vegosszegkiszamitasa(result);
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
            <div class="kosar-row" data-id="${adatok[i].id}">
                <div class="kosar-tetel kosar-column">
                    <img class="kosar-tetel-kep" src="../kepek/jatekok/${adatok[i].kep}" width="100" height="100">
                    <span class="kosar-tetel-nev">${adatok[i].nev}</span>
                </div>
                <span class="kosar-ar kosar-column">${adatok[i].vegosszeg} Ft</span>
                <div class="kosar-torles kosar-column"> 
                    <button id="torol" class="btn btn-danger" type="button">X</button>
                </div>
            </div>
        `;
        }
    }

    document.addEventListener("click", function (event) {
        if (event.target && event.target.classList.contains("torol")) {
            const jatekId = event.target.closest('.kosar-row').dataset.id;
    
            //a törléshez szükséges műveleteket
            
        }
    });
    

    function vegosszegkiszamitasa(adatok) {
        vegosszeg.innerHTML = "";
        let osszesitettar = 0;
        for (let i = 0; i < adatok.length; i++) {
            osszesitettar += adatok[i].vegosszeg;
        }
        vegosszeg.innerHTML += `${osszesitettar} Ft`;
        console.log(osszesitettar);
    }
});