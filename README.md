Warhammer Management Application\
Spring Boot with Spring Security and Thymeleaf.\
Full stack web application for managing Warhammer miniatures.

MINIATURES FUNCTIONS -> localhost:8080

USER ADMIN FUNCTIONS -> localhost:8080\users

environment variable for properties file decryption:\
JASYPT_ENCRYPTOR_PASSWORD=secret

encrypt properties with \
com.rainesinc.warhammer.util.Encryption.java

main application.properties is configured for the postgres database.\
test application.properties is configured for an H2 in memory database.\
(for services layer integration tests)

todo:

faction crud

role crud

users CRUD enable/disable in edit forms




    