document.addEventListener('DOMContentLoaded', function () {
    const product_content = document.getElementById('product_content');
    const create_btn = document.getElementById('create_btn');
    let _size = [];
    // Kinyerjük a bejelentkezési adatokat a localStorage-ból
    const logeduser_data = JSON.parse(localStorage.getItem("loged_userdata"));
    // Ellenőrizzük, hogy van-e bejelentkezett felhasználó
    console.log(logeduser_data && logeduser_data.id);

    addEventListener('load', function () {
        if (logeduser_data.id && logeduser_data.admin==1) {
            productGet();
        } else {
            // Ha nincs bejelentkezett felhasználó, valamilyen hiba kezelése vagy irányítás
            console.error("Nincs bejelentkezett felhasználó");
            create_btn.style.display = "none";
        }
    });

    function _content(data) {
        product_content.innerHTML = "";

        for (let i = 0; i < data.length; i++) {
            _size[i] = data[i].id;
            product_content.innerHTML += `
        <div class="card d-flex flex-row justify-content-center h-auto h-lg-120" data-id="${data[i].id}">
            <img class="card-img-top img-fluid justify-content-center" src="../img/games/${data[i].image}" alt="${data[i].gameName}">
            <div class="card-body d-flex flex-row justify-align-content-between border-10">
                <p class="card-text text-lg-center">${data[i].gameName}</p>
                <button id="edit_btn" class="btn btn-primary edit_btn" type="button">Szerkesztés</button>
            </div>
        </div>
    `;
        }
    }
    function productGet() {
        fetch(
            "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/products"
          )
          .then((response) => response.json())
          .then((result) => {
            _content(result);
          })
          .catch((error) => alert(error));
    }

});