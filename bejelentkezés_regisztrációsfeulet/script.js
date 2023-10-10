const formPopup = document.querySelector(".form-popup");
const signupLoginLink = formPopup.querySelectorAll(".bottom-link a");
const regisztracio = document.getElementById('regisztracio');

const felhaszN = document.getElementById('felhasz').value;
const vezeteknev = document.getElementById('vezeteknev').value;
const keresztnev = document.getElementById('keresztnev').value;
const datum = document.getElementById('datum').value;
const email = document.getElementById('email').value;
const jelszo = document.getElementById('jelszo').value;
const orszag = document.getElementById('orszag').value;
const telefonszam = document.getElementById('telefonszam').value;


document.body.classList.toggle("show-popup");


// Show or hide signup form
signupLoginLink.forEach(link => {
    link.addEventListener("click", (e) => {
        e.preventDefault();
        formPopup.classList[link.id === 'signup-link' ? 'add' : 'remove']("show-signup");
    });
});

//inputdata
const inputdata = {
    felhasznaloNev: felhasz,
    vezetekNev: vezeteknev,
    keresztNev: keresztnev,
    szuletesiDatum: datum,
    email: email,
    jelszo: jelszo,
    orszag: orszag,
    telefon: telefonszam
}

// regisztráció fetch 
regisztracio.addEventListener('click', () => {
    fetch("http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalok", {

        method: "POST",
        body: JSON.stringify(inputdata),
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },

    }.then((response) => response.json()).then(data => { console.log(data) }));
});