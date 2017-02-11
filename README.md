# textgroup
Spring-boot app using hibernate-jpa,spring data and a treeview bootstrap to build and visualize a multilingual database (for equivalences), can be used for example for making a web site multilingual

1) setting up the 'application.properties' file with the 'create' option and delete the language (0 records table after first startup), use the maven command to build and run on localhost:8080 : 'mvn package spring-boot:run'<br>
2) build your language table from language.sql (for example with phpmyadmin / mysql)<br>
3) restart with 'validate' option in the 'application.properties' file
4) add a domain<br>
5) add language(s)<br>
6) add text(s)<br>
<br>
Note : add all your languages before adding texts, however you can add at any time a language but the previously added texts will be only available with the languages used before, this case is not managed in this version<br>
<br>
<br>

