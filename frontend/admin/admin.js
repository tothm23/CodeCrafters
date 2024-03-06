document.addEventListener('DOMContentLoaded', function () {
    const product_content = document.getElementById('product_content');
    const create_btn = document.getElementById('create_btn');
    let _size = [];
    // Kinyerjük a bejelentkezési adatokat a localStorage-ból
    const logeduser_data = JSON.parse(localStorage.getItem("loged_userdata"));
    // Ellenőrizzük, hogy van-e bejelentkezett felhasználó
    console.log(logeduser_data && logeduser_data.id);

    addEventListener('load', function () {
        if (logeduser_data.id && logeduser_data.admin == 1) {
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
        <div class="card d-flex flex-lg-row flex-column h-auto h-lg-120" data-id="${data[i].id}">
            <div class="img-div d-flex justify-content-center">
                <img class="img-fluid" src="../img/games/${data[i].image}" alt="${data[i].gameName}">
            </div>
            <div class="card-body d-flex flex-lg-row flex-column justify-align-content-between border-10">
                <p class="card-text text-lg-center">${data[i].gameName}</p>
                <div class="d-flex justify-align-content-center flex-row">
                    <button id="edit_btn" class="btn edit_btn" type="button"><a href="../addproduct/addproduct.html?id=${data[i].id}">Szerkesztés</a></button>
                    <button id="delete_btn" class="btn delete_btn" type="button"><i class="fa-solid fa-trash"></i></button>
                </div>
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