/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.edu.networking;
import java.io.*;
import java.net.*;
/**
 *
 * @author 2110461
 */
 public class URLReader {
    public static void main(String[] args) throws Exception {
        URL google = new URL("http://www.google.com/");
   
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(google.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
            }
            reader.close();
        }catch (IOException x){
            System.err.println(x);
            
        }
        System.out.println("protocol = " + google.getProtocol());
        System.out.println("authority = " + google.getAuthority());
        System.out.println("host = " + google.getHost());
        System.out.println("port = " + google.getPort());
        System.out.println("path = " + google.getPath());
        System.out.println("query = " + google.getQuery());
        System.out.println("filename = " + google.getFile());
        System.out.println("ref = " + google.getRef());
  }
  
 }
