document.addEventListener('DOMContentLoaded', () => {

    const navbarContainer = document.getElementById("navbar-container");


    const navHTML = `
            <nav id="balNav">
                <span id="welcomeMessage" style="font-weight: bold; margin-left: 10px;"></span>
                <a href="index.html">Accueil</a>
                <a href="browse.html">Parcourir</a>
                <a href="profile.html" id="profilBtn" style="display: none;">Mon profil</a>
                <a href="login.html" id="loginBtn">Se connecter</a>
                <a href="#" id="logoutBtn" style="display: none;">Se deconnecter</a>
                <a href="register.html" id="signinBtn">S'inscrire</a>
            </nav>
        `;


    if (navbarContainer) {
        navbarContainer.innerHTML = navHTML
    };


    const welcomeSpan = document.getElementById('welcomeMessage');
    const loginBtn = document.getElementById('loginBtn');
    const logoutBtn = document.getElementById('logoutBtn');
    const signinBtn = document.getElementById('signinBtn');
    const profilBtn = document.getElementById('profilBtn');

    const userString = localStorage.getItem('user');

    if (userString) {
        const user = JSON.parse(userString);

        // A. Gestion des boutons
        if (loginBtn) loginBtn.style.display = 'none';
        if (signinBtn) signinBtn.style.display = 'none';
        if (logoutBtn) logoutBtn.style.display = 'inline'; // On affiche "Se deconnecter" à la place !
        if (profilBtn) profilBtn.style.display = 'inline'; // On affiche le profil

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
        if (signinBtn) signinBtn.style.display = 'inline';
        if (logoutBtn) logoutBtn.style.display = 'none';
        if (profilBtn) profilBtn.style.display = 'none';

        if (welcomeSpan) {
            welcomeSpan.innerText = "";
        }
    }
});