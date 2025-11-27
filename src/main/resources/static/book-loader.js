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

            const ligne = document.createElement("li");

            const texte = document.createElement("span");
            texte.innerText = livre.title + " ";

            const image = document.createElement("img");
            image.src = livre.img_link;

            image.style.height = "50px";
            image.style.marginLeft = "10px";
            image.style.verticalAlign = "middle";

            ligne.appendChild(texte);
            ligne.appendChild(image);

            maListe.appendChild(ligne);

            })

        })



})