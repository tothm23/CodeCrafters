function kosarTetelek(title, price) {
    var cartRow = document.createElement('div');
    cartRow.classList.add('kosar-row');

    var cartItems = document.querySelector('.kosar-tetelek');
    var cartItemTitles = cartItems.querySelectorAll('.kosar-tetel .cart-item-title');

    for (var i = 0; i < cartItemTitles.length; i++) {
        if (cartItemTitles[i].innerText == title) {
            alert('Ez a tétel már hozzá lett adva a kosárhoz.');
            return;
        }
    }

    var cartRowContents = `
        <div class="kosar-tetel kosar-column">
            <span class="cart-item-title">${title}</span>
        </div>
        <span class="kosar-ar kosar-column">${price} FT</span>
        <div class="kosar-mennyiseg kosar-column">
            <input class="kosar-mennyiseg-input" type="number" value="1" min="1">
            <button class="btn btn-danger" type="button">TÖRLÉS</button>
        </div>`;

    cartRow.innerHTML = cartRowContents;
    cartItems.appendChild(cartRow);

    var removeButton = cartRow.querySelector('.btn-danger');
    var quantityInput = cartRow.querySelector('.kosar-mennyiseg-input');

    removeButton.addEventListener('click', removeCartItem);
    quantityInput.addEventListener('change', quantityChanged);
}

// Példa adat
kosarTetelek('Játék1', 2999);
kosarTetelek('Játék2', 3999);
kosarTetelek('Játék3', 4999);

//Problémák:
// - csak az első adatot jeleníti meg
// - mennyiség nem updateli a végösszeget
// - törlés gomb nem működik