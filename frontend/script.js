document.addEventListener('DOMContentLoaded', function () {
    // Eseménykezelő a mynavbar elemekre
    var myNavbarItems = document.querySelectorAll('.mynavbar .gomblink');

    myNavbarItems.forEach(function (navbarItem) {
        navbarItem.addEventListener('click', function (event) {
            // Megtaláljuk az id-t a href-ben
            var targetId = this.getAttribute('id');

            // Megtaláljuk a megfelelő checkboxot
            var checkboxId = targetId + 'input';
            var checkbox = document.getElementById(checkboxId);

            // Ellenőrizzük, hogy a checkbox megtalálható-e
            if (checkbox) {
                // Ellenőrizzük, hogy a kattintás az aktuális oldalon történt-e
                if (window.location.href.includes(targetId)) {
                    // Beállítjuk a checkbox állapotát (kipipáljuk)
                    checkbox.checked = true;
                } else {
                    // Ha a kattintás más oldalon történt, akkor továbbítjuk a felhasználót az új oldalra
                    window.location.href = this.getAttribute('href');
                }
            }
        });
    });
});
