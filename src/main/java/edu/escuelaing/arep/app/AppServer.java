package edu.escuelaing.arep.app;

import edu.escuelaing.arep.app.anotations.Web;
import edu.escuelaing.arep.app.interfaces.Handler;

import javax.sound.midi.SysexMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        load("apps.App");
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
                System.err.println("Port " + port + " is being listened...");
            } catch (IOException e) {
                System.err.println("Port " + port + " could not be listened...");
                System.exit(1);
            }
            client = null;
            try {
                System.out.println("Recieving...");
                client = server.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            try {
                PrintWriter out = null;
                out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.err.println("Request: "+inputLine +" recieved");
                    if(inputLine.contains("GET")) {
                        String address = inputLine.split(" ")[1];
                        if(address.contains("/apps/")) {
                            out.println("HTTP/1.1 200 OK\r");
                            out.println("Content-Type: text/html\r");
                            out.println("\r\n");
                            System.err.println(address);
                            out.println(listURLHandler.get(address).process());
                        }else{
                            System.out.println("fvdfdsrfb");
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
