Este Codigo permite mantener usaurio con un validador de login

para poder usarlo

1. descargar el codigo y ejecutar en repositorio local
2. Abrir con algún IDE, preferencia visual Studio Code.
3. Actualizar el Maven por efecto de librerias.
4. Correr Aplicacion.
5. Existe una pequeña implementación de usuario y fechas de sql.
6. Para poder logearse revisar el import.sql para elegir un usuario y password, pueden probar con user(felipe) y password(felipe1234)
7. la ruta para logeo usando postman o alguna app parecido es:
    post http:localhost:3500/login
    {
        "user": "felipe",
        "password": "felipe1234"
    } 
    este devuelve un token el cual debe ser copiado y pegado en el header Authorization con su Bearer + el token, ejemplo:
        Authorization Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZWxpcGUiLCJ1c2VySWQiOiJmZWxpcGUiLCJpYXQiOjE2MDE5ODk4OTUsImV4cCI6MTYwMTk5MDQ5NX0.P1Ax8IzANY5xjgMULzEjLVPYaa8qMQ3hu5ufIn0DmgU

    para obtener el token, si o si debe logearse ya que el token tiene una duracion limitada

8. las rutas get ambas con autentificación son:
    http://localhost:3500/listar_usuarios
    http://localhost:3500/ver/{id_user}
9. Las Rutas Post son:
    http://localhost:3500/login
        solo con user y password;
    http://localhost:3500/save_usuario
        solo con autentificación:
        

