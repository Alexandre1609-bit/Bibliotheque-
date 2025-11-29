document.addEventListener('DOMContentLoaded', () => {

    // Je récupère la liste crée en html avec son ID
    const maListe = document.getElementById('bookList');

    // J'appelle le serveur java
    fetch('http://localhost:8080/livres')
        .then(reponse => {
            return reponse.json();
        })
        .then(livres => {

            //Boucle sur chaque livre
            livres.forEach(livre => {

                //éléments HTML
                const ligne = document.createElement("li");
                const texte = document.createElement("span");
                const image = document.createElement("img");

                // infos de base
                texte.innerText = livre.title + " ";
                image.src = livre.img_link;

                //Style de l'image
                image.style.height = "50px";
                image.style.marginLeft = "10px";
                image.style.verticalAlign = "middle";
                image.style.cursor = "pointer";

                //évènement click pour la modale
                image.addEventListener('click', (e) => {
                    e.preventDefault();

                    //des éléments de la modale
                    const modalOvrl = document.getElementById('modalOverlay');
                    const modalImg = document.getElementById("modalImage");
                    const modalTitle = document.getElementById("modalTitle");
                    const modalAuthor = document.getElementById("modalAuthor");
                    const modalStock = document.getElementById("modalStock");
                    const modalSummary = document.getElementById("modalSummary");
                    const borrowBtn = document.getElementById("modalBorrowBtn");

                    //Remplissage de la modale avec le livre cliqué
                    modalImg.src = livre.img_link;
                    modalTitle.innerText = livre.title;
                    modalSummary.innerText = livre.summary;
                    modalAuthor.innerText = livre.author;
                    modalStock.innerText = livre.stock;

                    //Affichage de la fenêtre
                    if (modalOvrl) {
                        modalOvrl.style.display = 'flex';
                    }


                    //récupère l'utilisateur connecté (Fait dans "auth.js")
                    const userData = JSON.parse(localStorage.getItem('user'));

                    if (userData) { //S'il y a un utilisateur connecté !
                        const dataToSend = {
                            borrowDate: new Date().toISOString().split('T')[0], //Conflit avec l'ancien format j'ai du remplacer !!
                            borrowUser: {
                                id: userData.id
                            },
                            theBook: {
                                book_id: livre.book_id
                            }
                        };

                        //action du bouton (onclick écrase l'ancien clic pour éviter les doublons)
                        borrowBtn.onclick = () => {


                            fetch('http://localhost:8080/emprunts', {
                                    method: 'POST',
                                    headers: {
                                        'Content-Type': 'application/json',
                                    },
                                    body: JSON.stringify(dataToSend),
                                })
                                .then(reponse => {
                                    if (reponse.ok) {
                                        alert("Livre emprunté avec succès !");
                                        window.location.reload();
                                        //fermer la modale
                                        modalOvrl.style.display = 'none';
                                    } else {
                                        alert("Erreur lors de l'emprunt.");
                                    }
                                });
                        };
                    } else {
                        // Si pas connecté le bouton renvoie au login
                        borrowBtn.onclick = () => {
                            alert("Veuillez vous connecter !");
                            window.location.href = "login.html";
                        };
                    }
                });

                //Assemblage final dans la liste
                ligne.appendChild(texte);
                ligne.appendChild(image);
                maListe.appendChild(ligne);

            }); // Fin du forEach
        }); // Fin du fetch

    //GESTION DE LA FERMETURE
        const modalOvrl = document.getElementById('modalOverlay');
        const closeBtn = document.getElementById("closeModal");

        //Fermer avec la croix
        if (closeBtn) {
            closeBtn.addEventListener('click', () => {
                modalOvrl.style.display = 'none';
            });
        }

        //Fermer en cliquant à côté
        if (modalOvrl) {
            modalOvrl.addEventListener('click', (e) => {
                if (e.target === modalOvrl) {
                    modalOvrl.style.display = 'none';
                }
            });
        }

        //BOUTON ACCUEIL
        const homeBtn = document.getElementById("homeBtn");
        if (homeBtn) {
            homeBtn.addEventListener('click', () => {
                window.location.href = "index.html";
            });
        }

    });