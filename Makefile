all: 
	- docker-compose -f docker/docker-compose.yml up

up: all

down:
	- docker-compose -f docker/docker-compose.yml down

access:
	- docker exec -ti javi-system-web-app /bin/bash

rebuild: 
	- docker restart javi-system-web-app
