# Proyecto
## Estudiante: José Alejandro Naranjo Guzmán
## Profesor: Luis Daniel Benavides Navarro
### Despliegue: [App Heroku](https://evening-lake-77451.herokuapp.com/static/index.html)

Para este proyecto se desarrolló un framework para soportar y desplegar aplicaciones web. Por medio de meta data en java, se realizan invocaciones a métodos de las clases, que también están plasmadas en el lenguaje con la abstracción de clase, es decir, con una clase que representa clases. Con el uso de anotaciones y caracteres específicos se creó un estándar para que los usuarios accedieran desde la web a las funcionalidades del servidor, por ejemplo, para enviar parámetros via URL.
### Diagrama de arquitectura
![Arquitectura](https://raw.githubusercontent.com/Alejoguzm07/AREPProyecto1/master/images/arquitectura.png)
### Diagrama de despliegue
![Arquitectura](https://raw.githubusercontent.com/Alejoguzm07/AREPProyecto1/master/images/despliegue.png)

Si se desea obtener recursos estáticos (png, jpg, jpeg o html) se debe usar el siguiente path:
- /static/recurso.extención
Si se desea obtener el resultado de los metodos creados por los programadores:
- /apps/metodo?parametro1,parametro2,...,parametroN
