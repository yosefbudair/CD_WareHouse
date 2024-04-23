start-db:
	docker-compose up -d

stop-db:
	docker-compose down

test:
	mvn test

run:
	mvn clean package
	java -cp target/my-app-1.0-SNAPSHOT.jar com.example.Main
