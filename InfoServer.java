/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket2;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author - INDIEGLO -
 */
public class InfoServer {

    private final int INFO_PORT = 50000;
    private String datafromClient;

    public InfoServer() {
        BufferedReader inFromClient;
        DataOutputStream outToClient;
        Socket serverSocket;

        try {
            ServerSocket infoServer
                    = new ServerSocket(INFO_PORT);

            System.out.println("Server telah siap...");

            while (true) {
                serverSocket = infoServer.accept();
                System.out.println("Ada client "
                        + "yang terkoneksi!");

                inFromClient
                        = new BufferedReader(
                                new InputStreamReader(
                                        serverSocket.getInputStream()));
                outToClient
                        = new DataOutputStream(
                                serverSocket.getOutputStream());
                outToClient.writeBytes("InfoServer versi 0.1\n"
                        + "hanya untuk testing..\n"
                        + "Silahkan berikan perintah TIME | NET | QUIT\n");
                boolean isQUIT = false;
                while (!isQUIT) {
                    datafromClient = inFromClient.readLine();
                    if (datafromClient.startsWith("TIME")) {
                        outToClient.writeBytes(new Date().toString() + "\n");
                    } else if (datafromClient.startsWith("NET")) {
                        outToClient.writeBytes(
                                InetAddress.getByName("budsusothie").toString()
                                + "\n");
                    } else if (datafromClient.startsWith("QUIT")) {
                        isQUIT = true;
                    }
                }
                outToClient.close();
                inFromClient.close();
                serverSocket.close();

                System.out.println("Koneksi client tertutup..");
            }
        } catch (IOException ioe) {
            System.out.print("error: " + ioe);
        } catch (Exception e) {
            System.out.print("error: " + e);
        }
    }

    /* program utama */
    public static void main(String[] args) {
        new InfoServer();
    }
}