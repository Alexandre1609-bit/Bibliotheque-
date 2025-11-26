document.addEventListener('DOMContentLoaded', () => {

    const registerForm = document.getElementById('registerForm');

    registerForm.addEventListener('submit', (event) => { // Attention ici pas document.aDD.. mais registerForm !!

        event.preventDefault();


        const insertedName = document.getElementById('prenom').value; //Modification : J'ai enlevé l'accent de "prénom" car moins risqué.
        const insertedEmail = document.getElementById('email').value;
        const insertedPswd = document.getElementById('password').value;

        const dataToSend = {
            name: insertedName,
            email: insertedEmail,
            pswd: insertedPswd
        };

        // Fetch
        fetch('http://localhost:8080/sign-in', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dataToSend)
        })
        .then(reponse => {
            console.log("Réponse brute : ", reponse);
            return reponse.json();
        })
        .then(idRecu => { //Méfiance, toujours vérifier ce que le DAO retour ! Mon addUser retourne un INT
            console.log("ID reçu du serveur :", idRecu);

            if (idRecu > 0) {
                alert("Compte créé avec succès ! ID : " + idRecu);

                window.location.href = "login.html"; //On redirige vers le login
            } else {
                alert("Erreur : création de compte impossible.");
            }
        })
        .catch(erreur => {
            console.error("Problème technique :", erreur);
        });

    });

});