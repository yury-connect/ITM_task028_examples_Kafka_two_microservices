# Так было на проекте:
#FROM openjdk:17.0.2-slim-bullseye
#
#COPY user-service-src/target/*.jar app.jar
#
#ENTRYPOINT [ "java", "-jar", "/app.jar" ]
#


# 🛠️ Первый этап — сборка проекта
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# 👷 Создаём рабочую директорию для сборки
WORKDIR /build

# 📂 Копируем все файлы проекта в контейнер
COPY . .

# 🧪 Собираем проект, пропуская тесты
RUN mvn clean package -DskipTests

# 🧼 Второй этап — лёгкий образ только с JAR-файлом
FROM eclipse-temurin:17-jdk-alpine

# 👩‍🍳 Создаём рабочую директорию для запуска
WORKDIR /app

# 📦 Копируем собранный JAR из модуля user-service-src
COPY --from=builder /build/user-service-src/target/*.jar app.jar

# 🚀 Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]


