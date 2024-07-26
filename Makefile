# I'm using a widows, therefore i haven't tested these commands
# Foreground mode:
run:
	docker-compose up
# Background mode:
run-detached:
	docker-compose up -d
down:
	docker compose down
test:
	mvn test