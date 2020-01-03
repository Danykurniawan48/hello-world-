/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket2;
import java.io.*;
import java.net.*;
/**
 *
 * @author - INDIEGLO -
 */
public class ExHTTPClient {

    public static void main(String args[]) {
        try {
            Socket clientSocket
                    = new Socket(args[0], 80);
            System.out.println("Client: "
                    + clientSocket);
            getHTML(clientSocket, args[1]);
        } catch (UnknownHostException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void getHTML(Socket clientSocket,
            String fileName) {
        try {
            DataOutputStream outbound
                    = new DataOutputStream(
                            clientSocket.getOutputStream());
            DataInputStream inbound
                    = new DataInputStream(
                            clientSocket.getInputStream());
            outbound.writeBytes("GET " + fileName
                    + " HTTP/1.0\r\n\r\n");
            String responseLine;
            while ((responseLine = inbound.readLine())
                    != null) {
                System.out.println(responseLine);
            }
            outbound.close();
            inbound.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}