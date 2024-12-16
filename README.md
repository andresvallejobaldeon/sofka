# sofka

Para compilar localmente, se debe crear la base de datos ejecutando el comando:
1) Ingresar a la carpeta "accounting-dao-service/database"
2) Ejecutar docker-compose -f docker-mysql-compose.yaml up -d 

Para desplegar los contenedores se debe ejecutar en el siguiente orden:
1) docker compose -f docker-compose-accounting-dao-service.yml build
2) docker compose -f docker-compose-accounting-dao-service.yml up
3) docker compose -f docker-compose-customer-service.yml build
4) docker compose -f docker-compose-customer-service.yml up
5) docker compose -f docker-compose-accounts-service.yml build
6) docker compose -f docker-compose-accounts-service.yml up 
