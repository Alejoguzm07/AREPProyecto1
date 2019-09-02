package edu.escuelaing.arep.app;

/**
 * Hello world!
 *
 */
public class Controller
{
    public static void main( String[] args )
    {
        AppServer server = new AppServer();

        try {
            server.initialize();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
