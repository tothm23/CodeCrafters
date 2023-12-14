document.addEventListener('DOMContentLoaded', function () {
    const Kosartartalma = document.getElementById('kosartartalma');
    const vegosszeg = document.getElementById('vegosszeg');
    // Kinyerjük a bejelentkezési adatokat a localStorage-ból
    const bejelentkezettFelhasznalo = JSON.parse(localStorage.getItem("bejelentkezes"));
    // Ellenőrizzük, hogy van-e bejelentkezett felhasználó
    console.log(bejelentkezettFelhasznalo && bejelentkezettFelhasznalo.id);

    addEventListener('load', function () {
        if (bejelentkezettFelhasznalo.id) {
            kosarGet();
        } else {
            // Ha nincs bejelentkezett felhasználó, valamilyen hiba kezelése vagy irányítás
            console.error("Nincs bejelentkezett felhasználó");
        }
    });


function kosartartalma(adatok) {
    Kosartartalma.innerHTML = "";

    for (let i = 0; i < adatok.length; i++) {
        //;
        Kosartartalma.innerHTML += `
            <div class="kosar-row" data-id="${adatok[i].jatekId}">
                <div class="kosar-tetel kosar-column">
                    <img class="kosar-tetel-kep" src="../kepek/jatekok/${adatok[i].kep}" width="100" height="100">
                    <span class="kosar-tetel-nev">${adatok[i].nev}</span>
                </div>
                <span class="kosar-ar kosar-column">${adatok[i].vegosszeg} Ft </span>
                <div class="kosar-torles kosar-column"> 
                    <button id="torol" class="btn btn-danger" type="button">X</button>
                </div>
            </div>
        `;
    }
}

document.addEventListener("click", function torlse (event) {
    console.log("Click");
    var kosarElem = event.target.closest('.kosar-row');
    // Olvassa ki az ID-t a data-id attribútumból
    var jatekid = kosarElem.getAttribute('data-id');
    console.log("termek id:", jatekid);
    var requestOptions = {
        method: 'DELETE',
        redirect: 'follow'
    };
    const url = `http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/kosar?felhasznaloId=${bejelentkezettFelhasznalo.id}&jatekId=${jatekid}`;
    fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));

    //az elem újra töltése
    var idozito = setTimeout(function() {
        // Az eseménykezelő hívása
        kosarGet();
    
        // Az időzítő törlése, így többször nem fut le
        idozito = null;
    }, 100);
    
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

function kosarGet() {
    fetch(`http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/kosar/${bejelentkezettFelhasznalo.id}`, )
        .then(response => response.json())
        .then(result => {
            // Itt további kezelése a kapott adatoknak
            kosartartalma(result);
            vegosszegkiszamitasa(result);
            console.log(result);
        })
        .catch(error => console.error(error));
}

});

