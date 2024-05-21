const felhaszadatai = document.getElementById('felhasznalo-adatai');
const form_inner = document.getElementById('forminner');
let bejelentkezettFelhasznalo = JSON.parse(localStorage.getItem("bejelentkezes"));

document.addEventListener('DOMContentLoaded', function () {
    adatok_innerHtml();

    const felhasznalo_form = document.querySelector("form");

    felhasznalo_form.addEventListener("submit", function (event) {
        event.preventDefault();
        const felhaszInput = document.getElementById("felhasz").value;
        const vezeteknevInput = document.getElementById("vezeteknev").value;
        const keresztnevInput = document.getElementById("keresztnev").value;
        const emailInput = document.getElementById("email").value;
        const jelszo = document.getElementById("jelszo").value;
        const jelszoujra = document.getElementById("jelszoegyezes").value;

        //felhasznalo jelszavának egyezésének figyelése
        if (jelszo !== jelszoujra) {
            alert("A jelszavak nem egyeznek!");
        } else {
            // A gomb, amire kattintottál
            const clickedButton = event.submitter;
            if (clickedButton.id === "mentes") {
                // Mentési logika
                var myHeaders = new Headers();
                myHeaders.append("Content-Type", "application/json");
                var raw = JSON.stringify({
                    "felhasznaloNev": felhaszInput,
                    "vezetekNev": vezeteknevInput,
                    "keresztNev": keresztnevInput,
                    "jelszo": jelszo
                });

                var requestOptions = {
                    method: 'PUT',
                    headers: myHeaders,
                    body: raw,
                    redirect: 'follow'
                };

                fetch(`http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalo/${bejelentkezettFelhasznalo.id}`, requestOptions)
                    .then(response => response.text())
                    .then(result => console.log(result))
                    .catch(error => console.log('error', error));
                console.log("Felhasználó frissítve");
                alert("Felhasználó frissítve");

                let valszObjektum = {
                    "felhasznaloNev": felhaszInput,
                    "email": bejelentkezettFelhasznalo.email,
                    "id": bejelentkezettFelhasznalo.id,
                    "vezetekNev": vezeteknevInput,
                    "keresztNev": keresztnevInput,
                    "jelszo": jelszo
                }
                bejelentkezettFelhasznalo = localStorage.setItem("bejelentkezes", JSON.stringify(valszObjektum));
                console.log(bejelentkezettFelhasznalo);
                //még a local storageba nincsen el mentve a változtatás


            } else if (clickedButton.id === "torles") {
                // Törlés logika
                var myHeaders = new Headers();
                myHeaders.append("Content-Type", "application/json");

                var raw = JSON.stringify({
                    "felhasznaloNev": felhaszInput,
                    "vezetekNev": vezeteknevInput,
                    "keresztNev": keresztnevInput,
                    "jelszo": jelszo
                });

                var requestOptions = {
                    method: 'DELETE',
                    headers: myHeaders,
                    body: raw,
                    redirect: 'follow'
                };

                fetch(`http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalo/${bejelentkezettFelhasznalo.id}`, requestOptions)
                    .then(response => response.text())
                    .then(result => console.log(result))
                    .catch(error => console.log('error', error));
                console.log("A felhasználó törölve!");
                localStorage.removeItem('bejelentkezes');
                alert("A felhasználó törölve!");
            }
        }
    });
});



function adatok_innerHtml() {
    felhaszadatai.innerHTML +=
        `
    <div class="felhasznalo-kep">
                <img src="../kepek/felhasznalo.png" alt="Profil-kep">
            </div>
            <div id="felhasznalo-nev">${bejelentkezettFelhasznalo.felhasznaloNev}</div>
            <div class="felhasznalo-valos-nevek d-flex flex-row">
                <div id="felhasznalo-vezeteknev">${bejelentkezettFelhasznalo.vezetekNev}</div>
                <div id="felhasznalo-keresztnev">${bejelentkezettFelhasznalo.keresztNev}</div>
            </div>
    `;

    form_inner.innerHTML += `
                <form>
                    <div class="mb-3">
                        <label for="felhasz" class="form-label">Felhasználónév</label>
                        <input type="text" class="form-control" id="felhasz" placeholder="${bejelentkezettFelhasznalo.felhasznaloNev}" required>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6 mb-3">
                            <label for="vezeteknev" class="form-label">Vezetéknév</label>
                            <input type="text" class="form-control" id="vezeteknev" placeholder="${bejelentkezettFelhasznalo.vezetekNev}" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="keresztnev" class="form-label">Keresztnév</label>
                            <input type="text" class="form-control" id="keresztnev" placeholder="${bejelentkezettFelhasznalo.keresztNev}" required>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="text" class="form-control" id="email" placeholder="${bejelentkezettFelhasznalo.email}" disabled>
                    </div>
                    <div class="mb-3">
                        <label for="jelszo" class="form-label">Jelszó</label>
                        <input type="password" class="form-control" id="jelszo" required>
                    </div>
                    <div class="mb-3">
                        <label for="jelszoegyezes" class="form-label">Jelszó Újra</label>
                        <input type="password" class="form-control" id="jelszoegyezes" required>
                    </div>
                    <div id="mentes-torles" class="felhasznalo-btns">
                        <button class="btn btn-primary w-100 mt-3 mx-auto" id="mentes" type="submit">Mentés</button>
                        <button class="btn btn-danger w-100 mt-3 mx-auto" id="torles" type="submit">Törlés</button>
                    </div>
                </form>
                `;
};