# Диктант

Веб сервис «Диктант» - это небольшое Spring приложение, читающее текстовые источники с помощью Yandex SpeechKit Cloud. (Сервис пишется с целью посмотреть, что такое Spring и попробовать=)

### Что используется:
- Spring Boot + Gradle;
- Spring (MVC, Data, Security, Test);
- JPA;
- Hibernate;
- MySQL;
- JDBC;
- Thymeleaf;
- webjars: Bootstrap, jQuery;
- Yandex SpeechKit Cloud;
- IntelliJ IDEA.

### Что сделано:
 - каркас сервиса (domain, service, web);
 - cинтез речи с помощью Yandex SpeechKit Cloud;
 - работа с БД;
 - веб-интерфейс с использованием bootstrap;
 - фильтр по авторам;
 - пользователи и роли;
 - добавление новых диктантов, удаление для администраторов.
 
### TODO List
- добавить уровень сложности диктантов, сделать фильтр по уровню сложности;
- администрирование пользователей, с возможностью назначения в администраторы;
- "favorites" для пользователей;
- предложение диктантов от пользователей с модерацией администраторами;
- регистрацию пользователей;
- разобраться с возможными ошибками и исключениями.
 
