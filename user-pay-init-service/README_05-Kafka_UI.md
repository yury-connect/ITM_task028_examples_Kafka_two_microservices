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
String
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
String
```

- **Value**:
```json
{
   "id": "1e294266-bf2d-4838-a7d3-ce591280e8f2",
   "money": {
      "id": "902626eb-ffeb-4ef3-8149-a8590572bc46",
      "amount": 321,
      "currency": "USD"
   },
   "user": {
      "id": 1,
      "userName": "Anton"
   },
   "createDate": null
}
```

- **Headers**:
```json
{
   "__TypeId__": "com.mycompany.userservice.rest.model.Payment"
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
