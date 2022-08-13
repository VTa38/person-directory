# person-directory
Поддерживаемое API: 

• GET /persons/ - выводит весь список зарегистрированных людей. В случае если записей не было список будет пуст

• GET / persons/ - выводит людей, с переданным id. Если нет человека с заданным id выйдет ошибка

• GET / persons/name/ - выводит список людей, с переданным именем. Если нет людей с переданным именем список будет пустым

• GET / persons/surname/ - выводит список людей, с переданной фамилией. Если нет людей с переданной фамилией список будет пустым

• GET / persons/email/ - выводит человека, с переданным адресом почты. Если человека с заданной почтой нет, выйдет ошибка

• POST /persons/ - принимает Json, на основе чего добавляет нового человека в базу данных. Пример Json запроса на добавление человека: { "name": "Иван", "surname": "Иванов", "middle_name": "Иванович", "phone_number": 8912332200, "email": "Ivan@madfgil.ru"}. (значения не могут быть пустыми). В случае если человек уже есть в БД выйдет ошибка.

• DELETE /persons/ – удаляет существующего человека по переданному id В случае если человека с заданным id не было выйдет ошибка

• PUT /persons/ – обновляет информацию о существующем человеке с переданным id по переданному Json. Примеры Json запросов на обновление записи о человеке: { "name": "Иван", "surname": "Иванов", "middle_name": "Иванович", "phone_number": 8912332200, "email": "Ivan@madfgil.ru"}. (значения могут быть пустыми) В случае если указанной записи не было выйдет ошибка. Изменить можно любые данные.

Архитектура:
![image](https://user-images.githubusercontent.com/67002782/184444787-f9e109ce-fd91-4acd-8ae0-0fc3d6992358.png)

О работе приложения: 
Записи о людях хранятся в БД. В таблице persons столбцы, хранящие информацию о человеке: id, name, surname, middle_name, phone_number, email. Получение запроса происходит в классе PersonController, который находятся в пакете controllers. PersonController поддерживает стандартные CRUD операции над таблицей persons, а также выводит список людей отфильтрованный по имени или фамилии. Так же можно получить человека по его email.
PersonController только вызывает соответствующий метод из PersonService, в котором реализована логика формирования ответа на запрос.
Ошибки, которые могут возникать в запросах, перехватываются ExceptionController, который находится в пакете controller. В конструктор ошибки передаётся константные сообщение об ошибке и статус ошибки (NOT_FOUND или BAD_REQUEST)
PersonService обращяется за даннным в репозиторий. В нём реализованны все алгоритмы поиска необходимых данных, при этом класс PersonService ничего не хранит и не кэширует, он просто собирает актуальные данные и передаёт в controller.


Интерфейс Swagger по адресу /swagger-ui/index.html#/
![image](https://user-images.githubusercontent.com/67002782/184471595-5a74358a-d46e-4b95-958c-e4259ed86a88.png)


Примеры запросов:

GET запрос поиск по id:
![image](https://user-images.githubusercontent.com/67002782/184471252-ffc3f466-5fc8-4ee7-878d-12edc7912968.png)

GET запрос поиск по почте:
![image](https://user-images.githubusercontent.com/67002782/184471295-4499d575-e003-4543-9b4f-28f0346835c3.png)

GET запрос фильтрация по имени:
![image](https://user-images.githubusercontent.com/67002782/184471349-f8c5bac5-0e74-4d33-bce7-4cb3d0ab577f.png)

GET запрос фильтрация по фамилии:
![image](https://user-images.githubusercontent.com/67002782/184471358-4b760565-4695-4e01-9469-a9f75b265e6d.png)

POST запрос добавление записи:
![image](https://user-images.githubusercontent.com/67002782/184471462-c7d519d1-ca64-4c42-bd01-971474afb2b9.png)

PUT запрос на изменение записи:
![image](https://user-images.githubusercontent.com/67002782/184471234-2176b2b1-948c-4ce7-b594-915cd4e3c465.png)

GET запрос на получение списка всех записей:
![image](https://user-images.githubusercontent.com/67002782/184471506-4acd87b7-0b74-4d11-8ac8-18b6733386fe.png)

DELETE запрос на удаление записи:
![image](https://user-images.githubusercontent.com/67002782/184471546-86e181e9-a1e1-4fa2-adff-35b8af8d0502.png)
![image](https://user-images.githubusercontent.com/67002782/184471559-88f5fbad-72a8-4200-a373-098ae468009e.png)



