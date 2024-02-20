document.addEventListener('DOMContentLoaded', function () {
    const cart_content = document.getElementById('cart_content');
    let cart_size = [];
    const final_price = document.getElementById('final_price');
    const order_btn = document.getElementById('order_btn');
    // Kinyerjük a bejelentkezési adatokat a localStorage-ból
    const logeduser_data = JSON.parse(localStorage.getItem("loged_userdata"));
    // Ellenőrizzük, hogy van-e bejelentkezett felhasználó
    console.log(logeduser_data && logeduser_data.id);

    addEventListener('load', function () {
        if (logeduser_data.id) {
            cartGet();
        } else {
            // Ha nincs bejelentkezett felhasználó, valamilyen hiba kezelése vagy irányítás
            console.error("Nincs bejelentkezett felhasználó");
            order_btn.style.display="none";
        }
    });

    function _content(data) {
        cart_content.innerHTML = "";

        for (let i = 0; i < data.length; i++) {
            cart_size[i] = data[i].jatekId;
            cart_content.innerHTML += `
        <div class="card d-flex flex-row justify-content-center h-auto h-lg-120" data-id="${data[i].jatekId}">
            <img class="card-img-top img-fluid justify-content-center" src="../img/games/${data[i].kep}" alt="${data[i].nev}">
            <div class="card-body d-flex flex-row justify-align-content-between border-10">
                <p class="card-text text-lg-center">${data[i].nev}</p>
                <p class="card-text d-none d-lg-block">${data[i].vegosszeg} Ft</p>
                <button id="torol" class="btn btn-danger h-50 h-lg-auto w-50 w-lg-auto" type="button">X</button>
            </div>
        </div>
    `;
        }
    }

    document.addEventListener("click", function torlese(event) {
        console.log("Click");
        var closest_card = event.target.closest('.card');
        // Olvassa ki az ID-t a data-id attribútumból
        var gameid = closest_card.getAttribute('data-id');
        console.log("termek id:", gameid);
        cart_delete(gameid);

    });

    function cart_delete(gameId) {
        var requestOptions = {
            method: 'DELETE',
            redirect: 'follow'
        };
        const url = `http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/basket?userId=${logeduser_data.id}&gameId=${gameId}`;
        fetch(url, requestOptions)
            .then(response => response.text())
            .then(result => {
                console.log(result);
                cartGet();

            })
            .catch(error => console.log('error', error));
    }

    order_btn.addEventListener('click', function () {
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        var raw = JSON.stringify({
            "felhasznaloId": logeduser_data.id
        });

        var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: raw,
            redirect: 'follow'
        };

        fetch("http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/basket", requestOptions)
            .then(response => response.text())
            .then(result => {
                console.log(result);
                for (let i = 0; i < cart_size.length; i++) {
                    cart_delete(cart_size[i]);
                }
                cartGet();
            })
            .catch(error => console.log('error', error));
    });

    function final_price_cal(data) {
        final_price.innerHTML = "";
        let sum_price = 0;
        for (let i = 0; i < data.length; i++) {
            sum_price += data[i].vegosszeg;
        }
        if (sum_price == 0) order_btn.disabled = true;
        else order_btn.disabled = false;
        final_price.innerHTML += `${sum_price} Ft`;
        console.log(sum_price);
    }

    function cartGet() {
        fetch(`http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/basket/${logeduser_data.id}`, )
            .then(response => response.json())
            .then(result => {
                // Itt további kezelése a kapott adatoknak
                _content(result);
                final_price_cal(result);
                console.log(result);
            })
            .catch(error => console.error(error));
    }

});