# Многомодульное Spring Boot приложение с двумя микросервисами

Воспользуемся Spring Initializr (https://start.spring.io) для генерации шаблонов двух микросервисов (например, User Service и Order Service). При генерации выберем:

- Project: Maven Project //wilddiary.com;    
- Language: Java (версия 17);  
- Spring Boot Version: последняя стабильная (например, 3.3.x) //medium.com;  

- Group: com.mycompany (условное доменное имя компании);  
- Artifact: user-service (для первого сервиса) или order-service (для второго);  
- Dependencies (галочки):  
  - Spring Web – для REST-контроллеров //springboottutorial.com;  
  - Spring Data JPA – для работы с базой данных (подключает Hibernate) //springboottutorial.com;  
  - H2 Database – встраиваемая база данных (комплектуется с Spring Boot);  
  - Spring Boot Actuator – для мониторинга приложения //baeldung.com;  
  - SpringDoc OpenAPI (Swagger UI) – для документации API //medium.com;  

Spring Initializr сгенерирует базовую структуру проектов. Затем организуем каждый сервис как многомодульный: 
создадим подмодули service-api и service-src. По аналогии с официальным гайдом по мульти-модульному проекту,
родительский pom.xml имеет <packaging>pom</packaging> и перечисляет модули. 
Модуль service-api содержит интерфейсы и модели (POJO, JPA-сущности), а service-src – собственно приложение 
с контроллерами и реализацией сервисов.

## Структура проектов (иерархия папок)
Например, для микросервиса User Service структура может быть такой:
```swift
user-service/
├── pom.xml               (родительский POM)
├── user-service-api/     (модуль API)
│   ├── pom.xml
│   └── src/
│       └── main/java/com/acme/userservice/api/...
└── user-service-src/     (модуль реализации)
├── pom.xml
└── src/
└── main/java/com/acme/userservice/app/...
```

Аналогично для Order Service:
```swift
order-service/
├── pom.xml
├── order-service-api/
│   ├── pom.xml
│   └── src/main/java/com/acme/orderservice/api/...
└── order-service-src/
    ├── pom.xml
    └── src/main/java/com/acme/orderservice/app/...

```

# Зависимости для каждого микросервиса
В Spring Initializr отмечали основные стартеры. В обоих сервисах выбраны:
- Spring Web (spring-boot-starter-web) – добавляет все для создания REST API;
- Spring Data JPA (spring-boot-starter-data-jpa) – упрощает работу с БД через репозитории;
- H2 Database (com.h2database:h2) – небольшая in-memory БД (в pom.xml как runtime-зависимость);
- Actuator (spring-boot-starter-actuator) – включает Production-ready функции (health, metrics и т.д.);
- SpringDoc OpenAPI UI (org.springdoc:springdoc-openapi-starter-webmvc-ui) – для Swagger UI (версия 2.5.0 для Spring Boot 3.x)

