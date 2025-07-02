### Для локального запуска необходимо создать файл application-secret-localhost.yml в директории src/main/resources со следующим содержимым:

```
spring:
datasource:
url: jdbc:postgresql://localhost:5432/reminder_db
username: <ваш_пользователь>
password: <ваш_пароль>
```

