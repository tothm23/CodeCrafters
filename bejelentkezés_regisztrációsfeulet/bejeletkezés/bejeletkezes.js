const formPopup = document.querySelector(".form-popup");
const signupLoginLink = formPopup.querySelectorAll(".bottom-link a");
const regisztracio = document.getElementById('regisztracio');
const belepes = document.getElementById('belepes');
const sikeres = document.getElementById('sikeres');

document.body.classList.toggle("show-popup");

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


