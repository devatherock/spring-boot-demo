clean:
	./gradlew clean
build:
	./gradlew build
run:
	./gradlew bootrun

podman-run:
	podman build -t spring-boot-demo:latest .
	podman run --rm \
		-p 8080:8080 \
		-p 5005:5005 \
		spring-boot-demo:latest