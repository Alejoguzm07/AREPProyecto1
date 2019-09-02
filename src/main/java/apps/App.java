package apps;

import edu.escuelaing.arep.app.anotations.Web;

public class App {
    public static int id;


    public App(){

        id = 0;
    }

    @Web("/Saludo")
    public static String escribir(){

        return "Hola Mundo!";
    }

    @Web("/Prueba")
    public static String test(){

        return "Test";
    }
}
