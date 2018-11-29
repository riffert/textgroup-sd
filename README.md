# textgroup-sd (spring-data based persistence layer)
Spring-boot app using hibernate-jpa,spring data and a treeview bootstrap to build and visualize a multilingual database (for equivalences), can be used for example for making a web site multilingual

<ul>
	<li>To build the database tables, start the application (mvn package spring-boot:run) with property <b>spring.jpa.hibernate.ddl-auto</b> set to <b>create</b> in 'application.properties' file</li>
	<li>After startup, build the language table from <b>language.sql</b></li>
	<li>Restart with <b>validate</b> option for <b>spring.jpa.hibernate.ddl-auto</b></li>
	<li>go on http://localhost:8080 and enjoy</li>
</ul>

Note : Since commit v0.3.5 the application is multidatabases session access based for jpa


