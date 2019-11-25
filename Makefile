all: 
	- docker-compose up &

up: all

down:
	- docker-compose down

access:
	- docker exec -ti javi-system-web-app /bin/bash

kill: 
	- docker exec javi-system-web-app pkill -f launcher.sh

rebuild: kill
	- docker-compose up &
