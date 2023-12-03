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

    fetch("http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalok/bejelentkezes", requestOptions)
    .then(response => response.json())
    .then(result => {
        // Sikeres bejelentkezés esetén tárold el az adatokat a local storage-ba
        if(result.id){
          var valszObjektum={
            "felhasznaloNev": result.felhasznaloNev,
            "keresztNev": result.keresztNev,
            "id": result.id,
            "vezetekNev": result.vezetekNev,
            "email": result.email

          };
          localStorage.setItem("bejelentkezes",JSON.stringify(valszObjektum));
          alert("Sikeres bejelentkezés");
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
