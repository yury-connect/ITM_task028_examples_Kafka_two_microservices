
# 🚀 Пошаговая инструкция: как запустить проект

---
### 🧼 1. Убедись, что нет «зависших» контейнеров
```bash
docker compose down -v --remove-orphans
```
> Удалит старые контейнеры, которые могли помешать запуску. 
> 
>⚠️ Флаг `-v` удаляет и **volume** с базой.⚠️

---
### 🔥 BONUS: Проверка перед сборкой
Перед тем как строить Docker-образ, убедись, что **локально сборка проходит**:
```bash
mvn clean package -DskipTests
```
Если ошибка — Docker тоже не соберёт. Локальная сборка = надёжный индикатор 💪

---
### 🧰 2. Собери Docker-образ Spring Boot-приложения
```bash
docker compose build
```
> 🛠️ Это построит образ из `Dockerfile`, используя **собранный .jar** из `user-service-src/target/`

---
### 📦 3. Запусти проект вместе с PostgreSQL
```bash
docker compose up -d
```
> 🔥 Флаг `-d` запускает всё в фоновом режиме.

---
### 🩺 4. Проверь логи БД
```bash
docker logs -f postgres_db
```

### 🩺 4.1. Проверь логи приложения
```bash
docker logs -f kafkademo
```
> Имя контейнера у тебя: `kafkademo` (по `docker-compose.yml`)  
> Логи скажут, запустилось ли приложение, подключилось ли к БД и всё ли хорошо 🌈
>
---
### 🧪 5. Проверка вручную

Когда всё запущено, можно:

- 🟢 Проверить Spring Boot Health:
```bash
curl http://localhost:8080/actuator/health

```

- 🌍 Открыть в браузере:  
  [http://localhost:8080](http://localhost:8080)

> Если работает — значит, ты боженька 😎

---
## ❤️ Если хочешь **пересобрать с нуля** _(на случай изменений)_:
```bash
mvn clean package -DskipTests         # 1. Собрать .jar
docker compose build                  # 2. Пересобрать образ
docker compose up -d --force-recreate # 3. Перезапустить заново
```

---
## 🧠 Возможные грабли и как их обойти:

| 🧨 Проблема                           | 💡 Решение                                                                      |
|:--------------------------------------|:--------------------------------------------------------------------------------|
| Приложение не стартует                | Проверь логи: `docker logs -f kafkademo`                                        |
| Не подключается к БД                  | Убедись, что БД работает: `docker ps`, проверь порты                            |
| `Connection refused` или `timeout`    | В `application.yaml` используй `jdbc:postgresql://db:5432/...` (не `localhost`) |
| Не видит таблицы или ошибки Hibernate | Возможно, не применены миграции или `ddl-auto` стоит в `validate`               |

---
### Пересобрать  и перезапустить
```bash
docker compose down -v --remove-orphans
docker compose build
docker compose up -d
docker logs -f kafkademo
```
---
