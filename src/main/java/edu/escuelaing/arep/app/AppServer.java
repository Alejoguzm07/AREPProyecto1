package edu.escuelaing.arep.app;

import edu.escuelaing.arep.app.anotations.Web;
import edu.escuelaing.arep.app.interfaces.Handler;

import java.lang.reflect.Method;
import java.util.HashMap;

public class AppServer {
    private HashMap<String, Handler>  listURLHandler;

    public AppServer() {
        listURLHandler = new HashMap<String, Handler>();
    }

    public void initialize() throws ClassNotFoundException {
        Class c = Class.forName("apps.App");
        for(Method m: c.getDeclaredMethods()){
            if(m.getAnnotation(Web.class) != null){
                listURLHandler.put(m.getAnnotation(Web.class).value(), new StaticMethodHandler(m));
            }
        }
    }

    public void load(String classpath){
        
    }
}
