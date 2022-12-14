# План автоматизации тестирования

## Перечень автоматизируемых сценариев

### Исходные данные:
- номер карты - 4444 4444 4444 4441, статус "ACCEPTED"
- номер карты - 4444 4444 4444 4442, статус "DECLINED"

### Форматы валидных данных:
- Поле "Номер карты": 16 цифр
- Поле "Месяц": числовое значение (от 01 до 12)
- Поле "Год": двухзначное число, не ранее текущего года
- Поле "Владелец": значение из латинских букв
- Поле "CVC/CCV": трехзначное число

### Предусловия:
1. Запустить Docker-контейнер с базой данных;
2. Запустить SUT командой 'java -jar artifacts/aqa-shop.jar';
3. Запустить симулятор командой npm start.

### Позитивные сценарии:
#### Оплата картой:
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. Заполнить поля валидными данными;
4. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные успешно отправлены, всплывает сообщение о том, что операция успешно проведена. В БД появляется запись со статусом ACCEPTED.

---
#### Оплата кредитной картой:
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить в кредит";
3. Заполнить поля валидными данными;
4. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные успешно отправлены, всплывает сообщение о том, что операция успешно проведена. В БД появляется запись со статусом ACCEPTED.

---
### Негативные сценарии:
#### Отправка пустой формы:
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. Оставить поля пустыми;
4. Нажать кнопку "Продолжить".
      
**Ожидаемый результат:** Данные не отправлены. Поля подсвечиваются красным цветом, под каждым полем появляется сообщение "Неверный формат".

---
#### Невалидный номер карты:
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Номер карты" ввести невалидный номер карты (4444 4444 4444 4442);
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные успешно отправлены, всплывает сообщение об отказе в проведении операции банком. В БД появляется запись со статусом DECLINED. 

---
#### Неполный номер карты:
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Номер карты" ввести номер карты, короче 16 цифр (4444 4444 4444 444);
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Номер карты" появляется сообщение "Неверный формат".

---
#### Невалидный номер карты (кириллица):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Номер карты" ввести значение с кириллицей (авбг 4444 4444 4441);
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Номер карты" появляется сообщение "Неверный формат".

---
#### Невалидный номер карты (латиница):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Номер карты" ввести значение с кириллицей (abcd 4444 4444 4441);
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Номер карты" появляется сообщение "Неверный формат".

---
#### Невалидный номер карты (спецсимволы):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Номер карты" ввести значение со спецсимволами (@, %, $ и т.д.);
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Номер карты" появляется сообщение "Неверный формат".

---
#### Невалидные данные в поле "Месяц":
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Месяц" ввести число меньше текущего месяца;
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Месяц" появляется сообщение "Неверно указан срок действия карты".

---
#### Проверка границ в поле "Месяц" (нижнее значение):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Месяц" ввести число 00;
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Месяц" появляется сообщение "Неверно указан срок действия карты".

---
#### Проверка границ в поле "Месяц" (верхнее значение):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Месяц" ввести число 13;
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Месяц" появляется сообщение "Неверно указан срок действия карты".

---
#### Невалидные данные в поле "Месяц" (кириллица):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Месяц" ввести значение с кириллицей;
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Месяц" появляется сообщение "Неверно указан срок действия карты".

---
#### Невалидные данные в поле "Месяц" (латиница):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Месяц" ввести значение с латиницей;
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Месяц" появляется сообщение "Неверно указан срок действия карты".

---
#### Невалидные данные в поле "Месяц" (спецсимволы):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Месяц" ввести значение со спецсимволами (@, %, $ и т.д.);
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Месяц" появляется сообщение "Неверно указан срок действия карты".

---
#### Невалидные данные в поле "Год" (прошедший год):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Год" ввести год меньше текущего;
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Год" появляется сообщение "Истёк срок действия карты".

---
#### Невалидные данные в поле "Год" (год, превышающий допустимый):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Год" ввести год больше текущего;
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Год" появляется сообщение "Неверно указан срок действия карты".

---
#### Невалидные данные в поле "Владелец" (кириллица):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Владелец" ввести значение кириллицей (например, Иван Иванов);
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Владелец" появляется сообщение "Неверный формат имени владельца".

---
#### Невалидные данные в поле "Владелец" (спецсимволы):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Владелец" ввести значение со спецсимволами (@, %, $ и т.д.);
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Владелец" появляется сообщение "Неверный формат имени владельца".

---
#### Невалидные данные в поле "Владелец" (число):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "Владелец" ввести число;
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "Владелец" появляется сообщение "Неверный формат имени владельца".

---
#### Невалидные данные в поле "CVC/CVV" (неполный код):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "CVC/CVV" ввести двузначное число;
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "CVC/CVV" появляется сообщение "Неверный формат".

-----
#### Невалидные данные в поле "CVC/CVV" (латиница):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "CVC/CVV" ввести буквенное значение латиницей (abc);
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "CVC/CVV" появляется сообщение "Неверный формат".

---
#### Невалидные данные в поле "CVC/CVV" (кириллица):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "CVC/CVV" ввести буквенное значение кириллицей (абв);
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "CVC/CVV" появляется сообщение "Неверный формат".

---
#### Невалидные данные в поле "CVC/CVV" (символы):
1. Открыть [страницу покупки тура](http://localhost:8080);
2. Нажать кнопку "Купить";
3. В поле "CVC/CVV" ввести значение с любым символом (13@);
4. Остальные поля заполнить валидными данными;
5. Нажать кнопку "Продолжить".

**Ожидаемый результат:** Данные не отправлены, под полем "CVC/CVV" появляется сообщение "Неверный формат".

---
## Перечень используемых инструментов

- Java – язык программирования с большим набором инструментов для автоматизации тестирования;
- IntelliJ IDEA – среда разработки для написания и запуска авто-тестов на Java;
- JUnit 5 – наиболее распространённая среда тестирования для Java-приложений;
- Gradle – система сборки;
- Selenide – фреймворк для авто-тестирования веб-интерфейсов;
- Docker – для развёртывания и управления СУБД;
- DBeaver - приложение для работы с базами данных;
- GitHub – облачный сервис, интегрированный с Git, предназначенный для хранения репозитория с проектом;
- Lombok – библиотека для сокращения объёма шаблонного кода и повышения его читабельности;
- Faker - плагин для генерации данных;
- Allure Report – фреймворк для создания отчётов прохождения авто-тестов.

## Перечень и описание возможных рисков при автоматизации

1. Правильность работы авто-тестов в любом случае необходимо проверять, сравнивая их с результатами ручного тестирования.
2. При частых изменениях в приложении код будет терять актуальность, авто-тесты - падать и требовать доработки.
3. Итоговая стоимость автоматизации может превысить экономическую выгоду от ее внедрения.

## Интервальная оценка с учетом рисков

1. Изучение приложения: 1 час
2. Подготовка плана автоматизации: 3 часа
3. Подготовка тестового окружения: 4 часа
4. Написание автотестов: 21 час;
5. Прогон автотестов: 1 час;
6. Составление баг-репортов: 3 часа;
7. Составление итогового отчёта: 3 часа.
---
**Итого:** 36 часов.

## План сдачи работ 

|  №  | Наименование                  | Дата сдачи |
|:---:|:------------------------------|:----------:|
|  1  | Написание и прогон автотестов | 21.11.2022 |
|  2  | Отчёт о тестировании          | 24.11.2022 |
|  3  | Отчёт об автоматизации        | 26.11.2022 |
