<h1>Pay-My-Buddy</h1>
Pay My Buddy, une start-up technologique qui fournit des solutions bancaires et financières.
Une appli qui permettrait aux clients de transférer de l'argent pour gérer leurs finances ou payer leurs amis.

<h1>Diagramme UML</h1>
<img width="901" alt="uml_p6" src="https://github.com/SabHicham/Pay-My-Buddy/blob/c7d2be57946d8b7e028aa4f09b79778e44f9fd9c/digramme%20de%20classe%20UML%20PayMyBuddy.png">

<h1>Modèle physique de donnés</h1>
<img width="901" alt="uml_p6" src="https://github.com/SabHicham/Pay-My-Buddy/blob/develop/digramme%20de%20classe%20UML%20PayMyBuddy.png">

<h1>installation</h1>
<h2>lancer le projet</h2>
installez Java 11
<
installez Maven
<
installez MySql et MySqlWorkBench
<h2>Importez le script</h2>
Après avoir fait toutes les installations, importez le script MySql suivant:
https://github.com/SabHicham/Pay-My-Buddy/blob/develop/ScriptSqlPayMyBuddy.sql

<h2>Sécurité</h2>
pour sécuriser vos données dans application.properties lancez la commande:
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.datasource.username=root --spring.datasource.password=root"
</br>
Ou bien, les enregistrer dans des profils Maven dans le fichier settings.xml, afin d’y accéder depuis le code.

