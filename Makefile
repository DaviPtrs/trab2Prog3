all: 
	- docker-compose up &

up: all

down:
	- docker-compose down

access:
	- docker exec -ti javi-system-web-app /bin/bash

rebuild: 
	- docker restart javi-system-web-app
