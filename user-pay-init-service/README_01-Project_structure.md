
| Где                  | Что                            | Почему                              |
|:---------------------|:-------------------------------|:------------------------------------|
| `application.yaml`   | Указываешь **дефолт**-значение | Нужно для запуска из IDE или тестов |
| `docker-compose.yml` | Переопределяешь через ENV      | Нужно для Docker-среды              |

📘 Правило: **IDE — `localhost`, Docker — имя сервиса (например, `db`)**

Предлагаю разбить конфиги на:
- `application.yaml` — базовый
- `application-docker.yaml` — для запуска в контейнерах
- `application-dev.yaml` — для работы в IDE

## ✅ Как надо по уму
1. В `docker-compose.yml` — только переменные окружения **без `SPRING_`**:
2. В `application.yaml` — всё читается через `${ENV_VAR:default}`