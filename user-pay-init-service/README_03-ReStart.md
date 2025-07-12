# 🔁 Сценарий: Перезапуск после изменений в логике, контроллерах и эндпоинтах
## 📜 Скрипт команд (для Windows / Linux / macOS)

---
```bash
# 1️⃣ Перейди в корень проекта (если ещё не там)
cd путь/к/проекту

# 2️⃣ Очистить и пересобрать проект с обновлёнными классами
./mvnw clean install -DskipTests
# или, если нет wrapper:
mvn clean install -DskipTests

# 3️⃣ Перейти в папку с микросервисом, если их несколько
cd user-service-src  # или нужный сервис

# 4️⃣ Запустить приложение
# Если это Spring Boot приложение, то:
java -jar target/имя-собранного-файла.jar

# например:
java -jar target/user-service-src-0.0.1-SNAPSHOT.jar

```

---

## 🛠 Что это делает?

| Шаг           | Действие                                  |
|:--------------|:------------------------------------------|
| `clean`       | Удаляет старые `.class` и `target/` файлы |
| `install`     | Пересобирает весь проект заново           |
| `-DskipTests` | Пропускает юнит-тесты (ускоряет сборку)   |
| `java -jar`   | Запускает уже соб                         |

---
---
# 🍬️ Автоматизация _(на сладенькое 🍬)_

Хочешь всё одной командой? Сделай `.sh` или `.bat` файл:

---
### 🔸 `restart-user-service.sh` (Linux/macOS)
```bash
#!/bin/bash
cd ~/IdeaProjects/my-project/user-service-src
mvn clean install -DskipTests
java -jar target/user-service-src-0.0.1-SNAPSHOT.jar
```

---
### 🔸 `restart-user-service.bat` (Windows)
```bat
cd C:\Users\Игорек\IdeaProjects\my-project\user-service-src
mvn clean install -DskipTests
java -jar target\user-service-src-0.0.1-SNAPSHOT.jar
```

---
Потом дважды клик — и всё взлетело! 🚀

