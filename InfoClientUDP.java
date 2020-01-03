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
public class InfoClientUDP {

    private final int INFO_PORT = 50000;
    private final String TargetHost = "localhost";
    private final String QUIT = "QUIT";
    private DatagramSocket clientSocket;

    public InfoClientUDP() {
        try {
            BufferedReader inFromUser
                    = new BufferedReader(
                            new InputStreamReader(System.in));

            clientSocket = new DatagramSocket();

            InetAddress IPAddress
                    = InetAddress.getByName("localhost");
            boolean isQuit = false;
            while (!isQuit) {
                byte[] byteFromServer = new byte[1024];
                byte[] byteToServer = new byte[1024];
                System.out.print("Perintah Anda : ");
                String cmd = inFromUser.readLine();

                cmd = cmd.toUpperCase();
                isQuit = cmd.equals(QUIT);
                byteToServer = cmd.getBytes();
                DatagramPacket sendPacket
                        = new DatagramPacket(byteToServer,
                                byteToServer.length, IPAddress, INFO_PORT);
                clientSocket.send(sendPacket);
                DatagramPacket receivePacket
                        = new DatagramPacket(byteFromServer,
                                byteFromServer.length);

                clientSocket.receive(receivePacket);
                String result
                        = new String(receivePacket.getData());
                System.out.println("Dari Server: " + result);
            }

            clientSocket.close();

        } catch (IOException ioe) {
            System.out.println("Error:" + ioe);
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    /* program utama */
    public static void main(String[] args) {
        new InfoClientUDP();
    }
}