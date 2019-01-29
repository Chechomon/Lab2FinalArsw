/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.edu.networking.punto4.subpunto4;
import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Sergio
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        
        Socket clientSocket = null;
        while(true){
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine = in.readLine();
                String outputLine, formato, resultado;
                byte[] bytes = null;
                if (inputLine != null) {
                    inputLine = inputLine.split(" ")[1];
                    if (inputLine.endsWith(".html")) {
                        bytes = Files.readAllBytes(new File("./" + inputLine).toPath());
                        resultado = "" + bytes.length;
                        formato = "text/html";
                    } else if (inputLine.endsWith(".png")) {
                        bytes = Files.readAllBytes(new File("./" + inputLine).toPath());
                        resultado = "" + bytes.length;
                        formato = "image/png";
                    } else {
                        bytes = Files.readAllBytes(new File("./index.html").toPath());
                        resultado = "" + bytes.length;
                        formato = "text/html";
                    }
                } else {
                    bytes = Files.readAllBytes(new File("./index.html").toPath());
                    resultado = "" + bytes.length;
                    formato = "text/html";
                }
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: "
                        + formato
                        + "\r\n"
                        + resultado
                        + "\r\n\r\n";

                byte[] hByte = outputLine.getBytes();
                byte[] rta = new byte[bytes.length + hByte.length];
                for (int i = 0; i < hByte.length; i++) {
                    rta[i] = hByte[i];
                }
                for (int i = hByte.length; i < hByte.length + bytes.length; i++) {
                    rta[i] = bytes[i - hByte.length];
                }
                clientSocket.getOutputStream().write(rta);
                clientSocket.close();
            } catch (IOException e) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
