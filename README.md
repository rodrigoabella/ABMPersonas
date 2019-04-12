# ABMPersonas
ABM de personas con spring-web y angular 6

## Dependencias:
 1. maven 3.3.9
 2. java 8
 3. node 8.11.3
 4. npm 6.3.0

## Ejecutar aplicacion en tomcat embebido: 
 1. clonar repositorio
 2. cd ABMPersonas
 3. mvn -U clean install
 4. export DB_DIR=<path_to_db_h2_dir> (variable de entorno para configurar donde guardar el archivo de base de datos ej: `export DB_DIR=/home/<user>/app`)
 5. make tomcat-local
 6. ingresar al navegador en `http://localhost:8081/app`
 
## Ejecutar aplicacion en tomcat7: 
 1. clonar repositorio
 2. cd ABMPersonas
 3. mvn -U clean install
 4. agregar linea en configuracion de tomcat luego de la definicion de JAVA_OPTS para definir la variable de entorno del directorio de la base de datos: `JAVA_OPTS="${JAVA_OPTS} -DDB_DIR=/home/<user>/web-app"` en `/etc/default/tomcat7`
 5. cp people-war/target/app.war /var/lib/tomcat7/webapps/app.war 
 6. reiniciar servicio tomcat7: `service tomcat7 restart`
 6. ingresar al navegador en `http://localhost:8080/app`
