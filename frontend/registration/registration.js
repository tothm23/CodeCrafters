const formPopup = document.querySelector(".form-popup");
const signupLoginLink = formPopup.querySelectorAll(".bottom-link a");
const registration_id = document.getElementById("registration");

document.addEventListener("DOMContentLoaded", function () {
  document.body.classList.toggle("show-popup");

  const regisztraciosForm = document.querySelector("form");

  regisztraciosForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const username = document.getElementById("username").value;
    const lastname = document.getElementById("lastname").value;
    const firstname = document.getElementById("firstname").value;
    const email = document.getElementById("email").value;
    const pas = document.getElementById("jelszo").value;
    const pas_again = document.getElementById("jelszoegyezes").value;

    if (pas != pas_again) {
      alert("A jelszavak nem egyeznek!");
    } else {

      const inputdata = {
        felhasznaloNev: username,
        vezetekNev: lastname,
        keresztNev: firstname,
        email: email,
        jelszo: pas

      };
      // regisztráció fetch
      fetch(
          "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/felhasznalo", {
            method: "POST",
            body: JSON.stringify(inputdata),
            headers: {
              "Content-type": "application/json; charset=UTF-8",
            },
          }
        )
        .then((valasz) => {
          return valasz.text();
        })
        .then((adat) => {
          if(adat=="Felhasználó hozzáadva!"){
            window.location.href = "../index.html";
          }
        })
        .catch((hiba) => {
          console.error('Hiba:', hiba);
        });
    }


  });
});