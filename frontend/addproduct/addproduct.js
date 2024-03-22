document.addEventListener('DOMContentLoaded', function () {

    // Kinyerjük a bejelentkezési adatokat a localStorage-ból
    const logeduser_data = JSON.parse(localStorage.getItem("loged_userdata"));
    const logeduser_token = localStorage.getItem("loged_usertoken");
    let preview=document.getElementById("preview");
    let imageInput=document.getElementById("imageInput");
    let productName=document.getElementById("productName");
    let price=document.getElementById("price");
    let discount=document.getElementById("discount");
    let stock=document.getElementById("stock");
    let agelimit = document.getElementById("age").options;
    let platform = document.getElementById("platform").options;
    let device = document.getElementById("device").options;
    let desc = document.getElementById("desc");
    let data;
    // Ellenőrizzük, hogy van-e bejelentkezett felhasználó
    console.log(logeduser_data && logeduser_data.id);

    // Kiolvassa a paraméterek az URL-ből
    let parameterek = new URL(document.location).searchParams;
    // Kiolvassa az id paramétert
    let id = parseInt(parameterek.get("id"));
    let link = "http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/game";
    addEventListener('load', function () {
        if (logeduser_data.id && logeduser_data.admin == 1) {} else {
            // Ha nincs bejelentkezett felhasználó, valamilyen hiba kezelése vagy irányítás
            console.error("Nincs bejelentkezett felhasználó");
            create_btn.style.display = "none";
        }
    });

    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };

    fetch(`${link}/${id}`, requestOptions)
        //a kapott adatokat json formátumban használjuk tovább
        .then((response) => response.json())
        .then((result) => {
            console.log(result);
            
            data = result;

            console.log("Data: ",data);
            preview.src= "../img/games/" + result.image;
            preview.style.display = "block";
            productName.value = result.gameName;
            price.value = result.price;
            discount.value = result.discount;
            stock.value = result.inStock;
            for (let i = 0; i < platform.length; i++) {
                console.log(platform[i]);
                if (platform[i].value == result.platformName) {
                    console.log(platform[i].value);
                    platform[i].selected = true;
                    console.log(platform.selectedIndex);
                    break;
                }
            }
            
            for (let i = 0; i < agelimit.length; i++) {
                console.log(agelimit[i]);
                if (agelimit[i].value == result.ageLimit) {
                    console.log(agelimit[i].value);
                    agelimit[i].selected = true;
                    console.log(agelimit.selectedIndex);
                    break;
                }
            }

            for (let i = 0; i < device.length; i++) {
                console.log(device[i]);
                if (device[i].value == result.deviceName) {
                    console.log(device[i].value);
                    device[i].selected = true;
                    console.log(device.selectedIndex);
                    break;
                }
            }

            desc.value = result.description;

        })
        .catch((error) => console.error(error));

    document.getElementById("save_btn").addEventListener("click", () => save(logeduser_token,id));

});

// Assuming result.image is the image name received from the fetch request
var imageInput = document.getElementById("imageInput");
imageInput.accept = "image/*;capture=camera"; // Updating the accept attribute with the image URL

function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function () {
        var preview = document.getElementById('preview');
        preview.src = reader.result;
        preview.style.display = "block";
    }
    reader.readAsDataURL(event.target.files[0]);
}

function save(logeduser_token,id) {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    console.log(JSON.stringify(myHeaders));
    console.log(logeduser_token);
    myHeaders.append("Authorization", `Bearer ${logeduser_token}`);
    console.log(JSON.stringify(myHeaders));
    const raw = JSON.stringify({
        "name": productName.value,
        "price": price.value,
        "description": desc.value,
        "image": "imagename",
        "ageLimit": agelimit.selected.value,
        "discount": discount.value,
        "inStock": stock.value,
        "deviceId": device.selectedIndex,
        "platformId": platform.selectedIndex
    });

    const requestOptions = {
        method: "PUT",
        headers: myHeaders,
        body: raw,
        redirect: "follow"
    };

    fetch(`http://localhost:8080/CodeCraftersWebshop-1.0-SNAPSHOT/webresources/game/${id}`, requestOptions)
        .then((response) => response.text())
        .then((result) => console.log(result))
        .catch((error) => console.error(error));
}