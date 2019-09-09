package edu.escuelaing.arep.app;

import edu.escuelaing.arep.app.interfaces.Handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StaticMethodHandler implements Handler {
    private Method m;

    public StaticMethodHandler(Method met){
        m = met;
    }

    public Object process(Object[] args) {
    	System.err.println(args == null);
        Object o = "<!DOCTYPE html>"
        		+ "<html>"
        		+ "<body>"
        		+ "<h1>OOOOPS!!!</h1>"
        		+ " <button type='button' href='https://evening-lake-77451.herokuapp.com/static/index.html'>volver al inicio</button>"
        		+ "</body>"
        		+ "</html>";
        try {
            o = m.invoke(null, args);
            return o;
        } catch (Exception e) {
        	return o;
        }
        
    }
}
