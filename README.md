Warhammer Management Application\
Spring Boot with Spring Security and Thymeleaf.\
Full stack web application for managing Warhammer miniatures.

environment variable for properties file decryption:\
JASYPT_ENCRYPTOR_PASSWORD=secret

encrypt properties with \
com.rainesinc.warhammer.util.Encryption.java

main application.properties is configured for the postgres database.\
test application.poroperties is configured for an H2 in memory database.\
(for integration tests)

todo:\
role service\
faction crud\
role crud\
role table entity should be a Java Set\
role service implementation over role repository\
unit tests, and springboot tests, with junit/Mockito [in progress]\
logging with slf4j and log4j2\
users CRUD enable/disable in edit forms,\
users CRUD pre-select roles in edit form on load\
create a clone of the project and implement a maven quickstart archetype, 
"spring-boot-thymeleaf-with-security-archetype"

    