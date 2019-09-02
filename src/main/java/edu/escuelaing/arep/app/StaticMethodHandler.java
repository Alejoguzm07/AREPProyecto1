package edu.escuelaing.arep.app;

import edu.escuelaing.arep.app.interfaces.Handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StaticMethodHandler implements Handler {
    private Method m;

    public StaticMethodHandler(Method met){
        m = met;
    }

    @Override
    public Object process() {
        Object o = null;
        try {
            o = m.invoke(null, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return o;
    }
}
