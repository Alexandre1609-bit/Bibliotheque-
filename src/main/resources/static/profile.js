document.addEventListener('DOMContentLoaded', () => {


    const userData = JSON.parse(localStorage.getItem('user'));

    if (!userData) {
        window.location.href = "index.html";
        return;
    }

    // 2. Préparation du tableau
    const myTable = document.getElementById("tbodyLoanList");

    // 3. Appel API
    fetch('/emprunts?userId=' + userData.id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
        .then(reponse => {
            return reponse.json();
        })
        .then(emprunts => {
            console.log("Liste des emprunts reçue :", emprunts);

            //Boucle d'affichage
            emprunts.forEach(loan => {

                // Création de la ligne
                const tableRow = document.createElement("tr");

                // Colonne Livre
                const tableDataLivre = document.createElement("td");

                if (loan.theBook) {
                    tableDataLivre.innerText = loan.theBook.title;
                } else {
                    tableDataLivre.innerText = "Livre inconnu";
                }
                tableRow.appendChild(tableDataLivre);

                // Colonne Date Prévue
                const tableDataDate = document.createElement("td");
                tableDataDate.innerText = loan.returnDate;


                const dateRetour = new Date(loan.returnDate);
                const aujourdhui = new Date();
                if (dateRetour < aujourdhui && !loan.trueReturnDate) {
                    tableDataDate.style.color = "red";
                    tableDataDate.style.fontWeight = "bold";
                }
                tableRow.appendChild(tableDataDate);

                // Colonne Action (Bouton)
                const tableDataRetour = document.createElement("td");

                if (loan.trueReturnDate === null) {

                    const retourBtn = document.createElement("button");
                    retourBtn.innerText = "Rendre";

                    // Clic sur le bouton Rendre
                    retourBtn.addEventListener('click', () => {

                        // Appel au serveur pour rendre
                        fetch('/retours', {
                            method: 'POST',
                            headers: { 'Content-Type': 'application/json' },
                            // On envoie juste l'ID de l'emprunt
                            body: JSON.stringify({ id: loan.id,
                                                   theBook: { book_id: loan.theBook.book_id }})
                        })
                        .then(rep => {
                            if (rep.ok) {
                                alert("Livre rendu !");
                                window.location.reload();
                            } else {
                                alert("Erreur serveur...");
                            }
                        });
                    });

                    tableDataRetour.appendChild(retourBtn);

                } else {

                    tableDataRetour.innerText = "Rendu le " + loan.trueReturnDate;
                }

                tableRow.appendChild(tableDataRetour);

                // Ajout final au tableau
                myTable.appendChild(tableRow);

            });

        })
        .catch(error => console.error("Erreur fetch:", error));



});