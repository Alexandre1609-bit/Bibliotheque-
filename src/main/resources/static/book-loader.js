document.addEventListener('DOMContentLoaded', () => {

//Je récupère la liste crée en html avec son ID
const maListe = document.getElementById('bookList');

// J'appelle le serveur java (mon controller book ou j'ai mis : "@GetMapping("/livres")"
fetch('http://localhost:8080/livres')
    .then(reponse => {

        return reponse.json();
    })
    .then(livres => {

        //Le constructeur : Je boucle sur chaque livre
        livres.forEach(livre => {

            //Création
            const ligne = document.createElement("li");
            //On prend les titres des livres
            ligne.innerText = livre.title;
            //On ajoute le tout à ma liste
            maListe.appendChild(ligne);

            })

        })



})