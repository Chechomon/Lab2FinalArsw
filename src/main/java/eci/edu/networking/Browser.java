/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.edu.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author 2110461
 */
public class Browser {
    public static void main(String[] args) throws Exception {
        System.out.println("Escriba una URL");
        Scanner scan = new Scanner(System.in);
        String a = scan.nextLine();
        URL url = new URL(a);
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;
            BufferedWriter bw = new BufferedWriter(new FileWriter("resultado.html"));
            while ((inputLine = reader.readLine()) != null) {
                bw.write(inputLine);
            }
            reader.close();
            bw.close();
        }catch (IOException x){
            System.err.println(x);   
        }
    }
}
