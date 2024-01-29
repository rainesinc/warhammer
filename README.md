Warhammer Management Application
Spring Boot with Spring Security and Thymeleaf.
Full stack web application for managing Warhammer miniatures.

environment variable for properties file decryption:
JASYPT_ENCRYPTOR_PASSWORD=secret

encrypt properties with 
com.rainesinc.warhammer.util.Encryption.java


todo:
role table entity should be a Java Set

unit tests with junit/Mockito
logging with slf4j and log4j2
users CRUD enable/disable in edit forms,
users CRUD pre-select roles in edit form on load,

convert to H2 database in master branch ,
convert the project to a maven quickstart archetype
spring-boot-thymeleaf-with-security-archetype

    