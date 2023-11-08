const formPopup = document.querySelector(".form-popup");
const signupLoginLink = formPopup.querySelectorAll(".bottom-link a");
const regisztracio = document.getElementById('regisztracio');
const belepes = document.getElementById('belepes');
const sikeres_reg = document.getElementById('"sikeres_reg"');

document.body.classList.toggle("show-popup");

// Show or hide signup form
signupLoginLink.forEach(link => {
    link.addEventListener("click", (e) => {
        e.preventDefault();
        formPopup.classList[link.id === 'signup-link' ? 'add' : 'remove']("show-signup");
    });
});

//bejelentkezés
belepes.addEventListener('click', (event) => {
    event.preventDefault();
    
    const felhaszNev = document.querySelector('.input-field:nth-child(1) input').value;
    const jelszo = document.querySelector('.input-field:nth-child(2) input').value;

    fetch("http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalok/bejelentkezes", {
        method: "GET",
        body: JSON.stringify({
            felhasznaloNev: felhaszNev,
            jelszo: jelszo
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    })
    .then((response) => {
        if (response.ok) {
            // Sikeres bejelentkezés, itt kezelheted a sikeres bejelentkezés eseményét
            console.log("Sikeres bejelentkezés!");
        } else {
            // Sikertelen bejelentkezés, itt kezelheted a sikertelen bejelentkezés eseményét
            console.error("Sikertelen bejelentkezés!");
        }
    })
    .catch((error) => {
        // Hibakezelés, ha valami nem működik a kérés során
        console.error("Hiba történt a bejelentkezés során: ", error);
    });
});


// regisztráció fetch 
regisztracio.addEventListener('click', () => {
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

    fetch("http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalok", {
        method: "POST",
        body: JSON.stringify(inputdata),
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
    }).then((valasz) => valasz.text())
    .then((adat) => {
      try {
        const elemzettAdat = JSON.parse(adat);
        sikeres_reg.innerHTML = elemzettAdat.message;
      } catch (error) {
        sikeres_reg.innerHTML = adat;
      }
    })
    .catch((hiba) => (sikeres_reg.innerHTML = hiba));
});
