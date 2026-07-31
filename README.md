# logging-starter-gradle

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-green)

Собственный Spring Boot стартер логирования для сервисов курса. Подключается одной зависимостью
и через автоконфигурацию добавляет:

- **`@LogExecutionTime`** — AOP-аспект логирования времени выполнения методов;
- **веб-фильтр** логирования входящих запросов и исходящих ответов (с телом);
- **Feign-логгер** REST-вызовов (направления IN/OUT);
- **обработку тела лога** цепочкой ответственности: включение → маскирование чувствительных
  полей → обрезка по размеру.

## Подключение

Артефакты публикуются в maven-репозиторий внутри самого репозитория (каталог `maven-repo/`)
и доступны по «сырой» ссылке GitHub. В `build.gradle` потребителя:

```groovy
repositories {
    mavenCentral()
    maven {
        url = uri("https://raw.githubusercontent.com/Naifff/logging-starter-gradle/main/maven-repo/")
    }
}

dependencies {
    implementation 'ru.knifffe:logging-starter-gradle:1.0.0-RC2'
}
```

Использование аннотации:

```java
@LogExecutionTime
public LinkInfoResponse createLinkInfo(CreateLinkInfoRequest request) {
    ...
}
```

## Настройки

| Свойство | По умолчанию | Назначение |
| --- | --- | --- |
| `logging.enabled` | `true` | общий выключатель стартера |
| `logging.log-exec-time` | `false` | включить аспект `@LogExecutionTime` |
| `logging.web-logging.enabled` | `true` | логировать HTTP-запросы/ответы |
| `logging.web-logging.log-body` | `false` | логировать тело запроса/ответа |
| `logging.web-logging.log-feign-requests` | `false` | логировать вызовы Feign (IN/OUT) |
| `logging.web-logging.log-feign-body` | `true` | логировать тело Feign-запросов |
| `logging.web-logging.body-max-size` | `10000` | максимальный размер тела в логе (символов) |
| `logging.web-logging.mask-fields` | `password, token, secret, otp` | поля, значения которых маскируются |

## Сборка и публикация

Требуется JDK 21 (через Gradle toolchain).

```bash
./gradlew build           # сборка + тесты
./gradlew publish         # публикация в локальный maven-repo/
```

## Структура

```
src/main/java/ru/knifffe/loggingstartergradle/
├── LoggingStarterAutoConfiguration.java   # автоконфигурация (условные бины)
├── annotation/     # @LogExecutionTime
├── aspect/         # аспект замера времени
├── bodyprocessor/  # цепочка обработки тела: enable → masking → truncating
├── dto/            # направление запроса (IN/OUT)
├── feign/          # логгер Feign-запросов
├── properties/     # LoggingProperties (logging.web-logging.*)
├── service/        # сервис логирования
├── util/           # утилиты
└── webfilter/      # фильтр и advice логирования тела
```

## Реализованные домашние задания

| ДЗ | Содержание |
| --- | --- |
| HW-3 | Основы стартера и аннотация замера времени |
| HW-10 | Вынос логирования в стартер: аспект, веб-фильтры, автоконфигурация |
| HW-17 | Логирование Feign-клиентов (IN/OUT) |
| HW-27 (опц.) | Обработка тела лога цепочкой ответственности + тумблер тела Feign |
