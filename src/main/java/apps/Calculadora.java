package apps;

import edu.escuelaing.arep.app.anotations.Web;

public class Calculadora {

    @Web("/Suma")
    public static double suma(String a, String b){
        return Double.parseDouble(a) + Double.parseDouble(b);
    }

    @Web("/Resta")
    public static double resta(String a, String b){
        return Double.parseDouble(a) - Double.parseDouble(b);
    }
    
    @Web("/Multiplicacion")
    public static double multiplicacion(String a, String b){
        return Double.parseDouble(a) * Double.parseDouble(b);
    }
    
    @Web("/Division")
    public static double division(String a, String b){
        return Double.parseDouble(a) / Double.parseDouble(b);
    }
    
    @Web("/Cuadrado")
    public static double cuadrado(String a){
        return Math.pow(Double.parseDouble(a), 2);
    }
    
    @Web("/Raiz")
    public static double raiz(String a){
    	return Math.pow(Double.parseDouble(a), 0.5);
    }
}
