# ***CY-Books***
## Table of Contents
1. [Infos général](#infos-général)
2. [Compatibilité système](#compatibilité-système)
3. [Technologies utilisées](#technologies-utilisées)
4. [Téléchargement](#téléchargement)
5. [Lancement](#lancement)

## Infos général

Le projet est une application java fx qui permet de gérer une bibliothèque, avec toutes les fonctionnalités telles que l’inscription des usagers, la gestion des livres, le stock, et les différents emprunts
<br/> 

## Compatibilité système

Le site est compatible avec intellij et eclipse.
<br/>

## Technologies utilisées

* Langages de programmations : <code>Java</code>, <code>CSS</code>, <code>SQL</code>, <code>FXML</code>
<br/>
Maven pour run et build

## Téléchargement
https://github.com/allanraha/CY-Books
* Télécharger [<code>CY-Books-main.zip</code>](https://github.com/allanraha/CY-Books) et extraire le fichier .
* Télécharger une version de [<code>-php</code>], [<code>-php-xml</code>], [<code>-mySQL</code>], [<code>-mysqli</code>], [<code>-fxml</code>] et [<code>-mySQL-workbench</code>], et JavaFX (si cela n'est pas déjà fait).


## Lancement
Il faut d'abord créer la base de données: 
<ul>
<li> Ouvrir mysql-workbench</li>
<li> Créer une connexion avec un username root, avec cytech0001 pour mot de passe et cy-books pour connexion name </li>
<li>Créer 3 table, une user ( avec userId type int en primarykey, first_name type text, last_name type texte et email type text), une books ( avec title type text, isbn type text, publisher type texte et author type text) et une loan ( avec idLoan type int primarykey, userId type int en foreignkey, title type texte et author type text, editor type text, loanDate type date, returnDate type date) </li>

</ul>

<br/>

<br/><br/>
Une fois sur Intellij, ajoutez les fichier .jar du dossier lib dans les modules du projet ( file/ project structure / modules / + fichier.jar et ajoutez les deux fichiers) 
<br/>
<br/>
Enfin il faut build et run le MainApp.java pour lancer l'application java fx
<br/>
