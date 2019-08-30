package apps;

import edu.escuelaing.arep.app.anotations.Web;

public class App {
    int id;
    public App(){
        id = 0;
    }

    public static void escribir(){
        System.err.println("Test");
    }

    @Web("/HolaComoEstas")
    public static String test(){
        return "Test";
    }
}
