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
        Kosartartalma.innerHTML +=`
        <div class="card d-flex flex-row justify-content-center h-auto h-lg-120" data-id="${adatok[i].jatekId}">
            <img class="card-img-top img-fluid justify-content-center" src="../kepek/jatekok/${adatok[i].kep}" alt="${adatok[i].nev}">
            <div class="card-body d-flex flex-row justify-align-content-between border-10">
                <p class="card-text text-lg-center">${adatok[i].nev}</p>
                <p class="card-text d-none d-lg-block">${adatok[i].vegosszeg} Ft</p>
                <button id="torol" class="btn btn-danger h-50 h-lg-auto w-50 w-lg-auto" type="button">X</button>
            </div>
        </div>
    `;
    }
}

document.addEventListener("click", function torlse (event) {
    console.log("Click");
    var kosarElem = event.target.closest('.card');
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

