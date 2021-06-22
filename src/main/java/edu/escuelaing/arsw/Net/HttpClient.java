package edu.escuelaing.arsw.Net;

import java.io.*;
import java.net.*;

public class HttpClient {
    static long startTime = System.nanoTime();
    static int i = 1;
    static double seconds = 0;
    /**
     *  Cliente que se conecta al server y empieza a generar solicitudes generando 200 hilos para corroborar la concurrencia
     * Al final muestra los hilos implementados y el tiempo en el que se realiza toda la actividad
     * @param args
     * @throws IOException
     */
    public static void main(String... args) throws IOException {
        while (i <= 200) {
            Socket clientSocket = null;
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                clientSocket = new Socket("127.0.0.1", 35000);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println(in + " " + i);
            } catch (UnknownHostException e) {
                System.err.println("Don’t know about host!.");
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Couldn’t get I/O for " + "the connection to: localhost.");
                System.exit(1);
            }
            out.close();
            in.close();
            clientSocket.close();
            i++;
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        seconds = (double)totalTime / 1_000_000_000.0;
        System.out.println("Runtime of the 200 request in nanoseconds: "+ seconds);

    }

    public double getSeconds(){
        return seconds;
    }

}
