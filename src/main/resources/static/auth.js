document.addEventListener('DOMContentLoaded', () => {

    const loginForm = document.getElementById('loginForm');
    loginForm.addEventListener('submit', (event) => {

    event.preventDefault();

    const emailTape = document.getElementById('email').value;
    const passwordTape = document.getElementById('password').value;


    const dataToSend = {
        email: emailTape,
        pswd: passwordTape
    };

    //Fetch
    fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
        'Content-Type': 'application/json',
        },
        body: JSON.stringify(dataToSend)
    })
    .then(reponse => {

            console.log("Réponse :", reponse);
            return reponse.json();
        })
        .then(user => {

            if (user) {
                console.log("Bienvenue " + user.name);
                alert("Connexion réussie !");
            } else {
                console.log("Erreur : Utilisateur non trouvé");
                alert("Email ou mot de passe incorrect");
            }
        })
        .catch(erreur => {
            console.error("Problème technique :", erreur);
        });

        });

    });

