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
    const pas = document.getElementById("pas").value;
    const pas_again = document.getElementById("pas_again").value;

    if (pas != pas_again) {
      alert("A jelszavak nem egyeznek!");
    } else {

      var myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/json");

      var raw = JSON.stringify({
        "userName": username,
        "lastName": lastname,
        "firstName": firstname,
        "email": email,
        "password": pas
      });

      var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
      };
      // regisztráció fetch
      fetch("http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/user", requestOptions)
        .then(response => response.text())
        .then(result => {
          console.log(result);
          alert(result);
          if (result == "Felhasználó hozzáadva!") {
            window.location.href = "../index.html";
          }
        })
        .catch(error => console.log('error', error));
    }
  });
});