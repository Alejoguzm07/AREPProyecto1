package apps;

import edu.escuelaing.arep.app.anotations.Web;

public class App2 {
    public static int id;


    public App2(){

        id = 0;
    }

    @Web("/Saludo2")
    public static String escribir(){

        return "Hola Mundo 2!";
    }

    @Web("/Prueba2")
    public static String test(){

        return "Test 2";
    }
}
