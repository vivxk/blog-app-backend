# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.6/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.6/reference/htmlsingle/#using-boot-devtools)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.6/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.6/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

## Spring Security Default config
* Adding Spring security dependency in the project and then running the APIs shows **401 Unauthorized**
* Spring Security uses form-based authentication. 
* Use generated security password by Spring Security. New Password is generated everytime the project is run.
* Hit API url in browser, will be shown a login form - **Username - user; Password- ***password***;**
* Password for this project has been changed and has been set in *application.properties* file
* In postman use Basic authorization under Authorization tab to view data.


## Spring Security Project specific config
* for 1st user in db, the password has been generated as encrypted and added  manually to db.
* when logging in supply the unencrypted password and copy the corresponding JWT token generated.
* This token will be used to access other endpoints.
* In postman select No auth under Authorization tab drop-down and add Authorization Header under Headers tab. e.g. Authorization Bearer <**YOUR_TOKEN_HERE**>
* 

## Swagger-ui
* go to browser open **http://localhost:<PORT-NUMBER>** to access swagger-ui