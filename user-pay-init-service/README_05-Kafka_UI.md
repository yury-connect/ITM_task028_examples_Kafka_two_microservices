# Kafka-UI _(запуск вручную)_

---
## Убедись, что все сервисы запущены

```bash
docker-compose up -d
```

Затем проверь статус контейнеров:
```bash
docker ps
```

Убедись, что **запущены минимум**:
- `kafka`
- `zookeeper`
- `kafka-ui`

---
## Открой браузер и перейди по адресу:
```arduino
http://localhost:9081
```

### Интерпретация
- **Key** — ключ сообщения (если ты его задавал в продюсере).
- **Value** — JSON, который ты отправил.
- **Timestamp** — когда сообщение было отправлено.
- **Headers** — если ты отправляешь кастомные заголовки.

---
# Для ручной отправки сообщений в Kafka через UI:

## Topics: `status-payments` _(проверка статуса платежа)_

- **Key**:
```text
Тут любая 'String', включая 'null'
```

- **Value**:
```json
{
  "paymentId": "1e294266-bf2d-4838-a7d3-ce591280e8f2",
  "statusPayment": "PROCESSING"
}
```

- **Headers**:
```json
{
	"__TypeId__": "com.mycompany.userservice.src.model.kafka.StatusPaymentDTO"
}
```

## Topics: `status-payments`

- **Key**:
```text
Тут любая 'String', включая 'null'
```

- **Value**:
```json
{
  "id": "6806e9b3-5f3e-4a4d-a642-22e1c753b97d",
  "money": {
    "id": "cddf6226-07f7-435e-a270-5297051cd70e",
    "amount": 321,
    "currency": "USD"
  },
  "user": {
    "id": 1,
    "userName": "Anton10"
  },
  "createDate": "2025-07-16T15:56:09"
}
```

- **Headers**:
```json
{
  "__TypeId__": "com.mycompany.userservice.src.model.kafka.PaymentDTO"
}
```

---
### 🧼 Дополнительно — убери все висящие контейнеры и сети, если нужно:

```bash
docker-compose down -v
```

---

---

---
## 🧪 Протестируй Kafka вручную
```bash
docker exec -it user-pay-init-service-kafka-1 kafka-topics --bootstrap-server localhost:9092 --list
```
Если всё хорошо — увидишь список топиков или пусто (если ни одного ещё не создано).

---
