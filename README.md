## Дипломный проект по профессии "Тестировщик"
___

### Документация проекта
* [План автоматизации тестирования](https://github.com/b-nana/aqa-final/blob/main/docs/Plan.md)
* [Отчет о проведённом тестировании](https://github.com/b-nana/aqa-final/blob/main/docs/Report.md)
* [Отчет о проведённой автоматизации тестирования](https://github.com/b-nana/aqa-final/blob/main/docs/Summary.md)

___

### Подготовка к запуску

Для запуска проекта локально должны быть установлены:

* IntelliJ IDEA 
* Java 11
* Docker Desktop
* Git

Для клонирования проекта ввести в терминал команду `git clone https://github.com/b-nana/aqa-final.git`

---

### Запуск приложения

1. Открыть проект в IntelliJ IDEA Ultimate;
2. Запустить Docker Desktop;
3. В терминале выполнить команду`docker-compose up`;

* Для запуска приложения на СУБД MySQL выполнить команду `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`

* Для запуска приложения на СУБД PostgreSQL выполнить команду `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`

### Запуск тестов

* Для запуска тестов на СУБД MySQL выполнить команду `.\gradlew test "-Ddb.url=jdbc:mysql://localhost:3306/app"`
* Для запуска приложения на СУБД PostgreSQL выполнить команду `.\gradlew test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`

#### Отчёты

После выполнения тестов для просмотра отчётов выполнить команды `.\gradlew allureReport` и `.\gradlew allureServe`.
Завершить работу плагина - Ctrl + C.
