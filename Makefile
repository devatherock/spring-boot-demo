clean:
	./gradlew clean
build:
	./gradlew build
run:
	docker-compose down
	docker-compose up -d
	./gradlew bootrun -Dspring.profiles.active=local