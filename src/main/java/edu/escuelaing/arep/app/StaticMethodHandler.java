package edu.escuelaing.arep.app;

import edu.escuelaing.arep.app.interfaces.Handler;

import java.lang.reflect.Method;

public class StaticMethodHandler implements Handler {
    private Method m;

    public StaticMethodHandler(Method met){
        m = met;
    }

    @Override
    public void process() {

    }
}
