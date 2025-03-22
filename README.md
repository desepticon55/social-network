# social-network

Для локального запуска требуется Docker и Maven.

1. В корневой директории выполняем mvn clean package
2. Переходим в директорию social-network/docker 
3. Выполняем команду docker-compose build, которая соберет образ
4. Выполняем команду docker-compose up, которая развернет Postgres и приложение
5. Импортируем Postman коллекцию из корня проекта и пользуемся :)