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
        Object o = null;
        try {
            o = m.invoke(null, args);
        } catch (Exception e) {
        	o = "HTTP/1.1 200 OK\r" +
            "Content-Type: text/html\r" +
            "\r\n" +
            "<html><body><h1>OOOPS!</h1></body> </html>";
            return o;
        }
        return o;
    }
}
