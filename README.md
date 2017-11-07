# textgroup
Spring-boot app using hibernate-jpa,spring data and a treeview bootstrap to build and visualize a multilingual database (for equivalences), can be used for example for making a web site multilingual

1) setting up the 'application.properties' file with the 'create' option (spring.jpa.hibernate.ddl-auto=create, uncomment/comment next line if necessary) and delete the language (0 records table after first startup), use the maven command to build and run on localhost:8080 : 'mvn package spring-boot:run'<br>
2) build your language table from language.sql (for example with phpmyadmin / mysql)<br>
3) restart with 'validate' option in the 'application.properties' file<br>
4) add a domain<br>
5) add language(s)<br>
6) add text(s)<br>
<br>
Note : To read the database in a non spring-boot app,you can copy the config package in your app and update your web.xml file to configure the listener for the Init class to load the map (hm) with the values at startup.
<br>


