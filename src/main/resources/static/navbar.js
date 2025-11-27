document.addEventListener('DOMContentLoaded', () => {


    const welcomeSpan = document.getElementById('welcomeMessage');
    const loginBtn = document.getElementById('loginBtn');
    const logoutBtn = document.getElementById('logoutBtn');
    const signinBtn = document.getElementById('signinBtn')


    const userString = localStorage.getItem('user');

    if (userString) {
        const user = JSON.parse(userString);

        // A. Gestion des boutons
        if (loginBtn) loginBtn.style.display = 'none';
        if (signinBtn) signinBtn.style.display = 'none';
        if (logoutBtn) logoutBtn.style.display = 'inline'; // On affiche "Se deconnecter" à la place !


        if (welcomeSpan) {
            welcomeSpan.innerText = "Bienvenue " + user.name;
        }


        if (logoutBtn) {

            logoutBtn.addEventListener('click', (e) => {
                e.preventDefault(); // Sécurité

                localStorage.removeItem('user');

                window.location.href = "login.html";
            });
        }

    } else {

        if (loginBtn) loginBtn.style.display = 'inline';
        if (signinBtn) signinBtn.style.diplay = 'inline';
        if (logoutBtn) logoutBtn.style.display = 'none'

        if (welcomeSpan) {
            welcomeSpan.innerText = "";
        }
    }
});