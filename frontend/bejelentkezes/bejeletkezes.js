document.addEventListener("DOMContentLoaded", (event) => {
  const formPopup = document.querySelector(".form-popup");
  const belepesGomb = document.getElementById("bejelentkezes");

  belepesGomb.addEventListener("click", (event) => {
    event.preventDefault();

    const felhaszNev = document.querySelector("#felhasz").value;
    const jelszo = document.querySelector("#jelszo").value;

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
      felhasznaloNev: felhaszNev,
      jelszo: jelszo
    });

    var requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: raw,
      redirect: 'follow'
    };

    fetch("http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalo/bejelentkezes", requestOptions)
    .then(response => response.json())
    .then(result => {
        // Sikeres bejelentkezés esetén tárold el az adatokat a local storage-ba
        if(result.token!=""){
        //token
        function parseJwt (token) {
            var base64Url = token.split('.')[1];
            var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));
        
            return JSON.parse(jsonPayload);
        }

        let valszObjektum=(JSON.stringify(parseJwt
          (result.token)));
          localStorage.setItem("bejelentkezes",valszObjektum);
          alert("Sikeres bejelentkezés");
          window.location.href = "../index.html";
        }
        else{
          alert("Sikertelen bejelentkezés");
        }
      
    }) 
    .catch(error => {
      console.log('Hiba történt:', error);
      // Hiba kezelése, pl. felhasználó értesítése
      alert("Hiba történt")
    });
  });
});
