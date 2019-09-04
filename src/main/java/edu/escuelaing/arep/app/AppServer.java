package edu.escuelaing.arep.app;

import edu.escuelaing.arep.app.anotations.Web;
import edu.escuelaing.arep.app.interfaces.Handler;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppServer {
    private HashMap<String, Handler>  listURLHandler;
    private static Socket client;
    private static ServerSocket server;


    public AppServer() {
        listURLHandler = new HashMap<String, Handler>();
    }

    public void initialize() throws ClassNotFoundException {
        File f = new File(System.getProperty("user.dir") + "/src/main/java/apps");
        File[] ficheros = f.listFiles();
        for (int x=0;x<ficheros.length;x++){
            String name = ficheros[x].getName();
            load("apps." + name.substring(0,name.indexOf(".")));
            System.out.println("Class " + name.substring(0,name.indexOf(".")) + " was loaded.");
        }
        listen();
    }

    private void listen() {
        while(true) {
            server = null;
            int port;
            if (System.getenv("PORT") != null) {
                port = Integer.parseInt(System.getenv("PORT"));
            } else {
                port = 4567;
            }
            try {
                server = new ServerSocket(port);
                System.out.println("Port " + port + " is being listened...");
            } catch (IOException e) {
                System.out.println("Port " + port + " could not be listened...");
                System.exit(1);
            }
            client = null;
            try {
                System.out.println("Recieving...");
                client = server.accept();
            } catch (IOException e) {
                System.out.println("Accept failed.");
                System.exit(1);
            }
            try {
                PrintWriter out = null;
                out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Request: "+ inputLine +" recieved");
                    if(inputLine.contains("GET")) {
                        String address = inputLine.split(" ")[1];
                        if(address.contains("/apps/")) {
                            out.println("HTTP/1.1 200 OK\r");
                            out.println("Content-Type: text/html\r");
                            out.println("\r\n");
                            System.out.println(address);
                            String[] parameters = address.split("\\?");
                            if(parameters.length == 1){
                            	out.println(listURLHandler.get(address).process(null));
                            }else {                            	
                            	address = address.substring(0,address.indexOf("?"));
                            	String[] arguments = parameters[1].split(",");                            	
                            	out.println(listURLHandler.get(address).process(arguments));
                            }                            
                        }if(address.contains("/web/")){
                            System.out.println("fldsmdfr");
                        }
                    }
                    if (!in.ready()) {
                        break;
                    }
                }
                in.close();
                out.close();
                client.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void load(String classpath){
        Class c = null;
        try {
            c = Class.forName(classpath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for(Method m: c.getDeclaredMethods()){
            if(m.getAnnotation(Web.class) != null){
                listURLHandler.put("/apps" + m.getAnnotation(Web.class).value(), new StaticMethodHandler(m));
            }
        }
    }
}
