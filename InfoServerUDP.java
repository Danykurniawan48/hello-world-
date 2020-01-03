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
public class InfoServerUDP {

    private final int INFO_PORT = 50000;
    private String datafromClient;

    public InfoServerUDP() {
        DatagramSocket serverSocket;

        try {
            serverSocket = new DatagramSocket(INFO_PORT);
            System.out.println("Server telah siap...");

            while (true) {
                boolean isQUIT = false;
                while (!isQUIT) {
                    byte[] byteFromClient = new byte[1024];
                    byte[] byteToClient = new byte[1024];
                    DatagramPacket receivePacket
                            = new DatagramPacket(
                                    byteFromClient, byteFromClient.length);
                    serverSocket.receive(receivePacket);

                    InetAddress IPAddress
                            = receivePacket.getAddress();
                    int port = receivePacket.getPort();

                    String data
                            = new String(receivePacket.getData());

                    if (data.startsWith("TIME")) {
                        String DateNow
                                = new String(new Date().toString());
                        byteToClient = DateNow.getBytes();

                    } else if (data.startsWith("NET")) {
                        String hostname = new String(
                                InetAddress.getByName("xxx").toString());
                        byteToClient = hostname.getBytes();
                    } else if (data.startsWith("QUIT")) {
                        isQUIT = true;
                        String thanks
                                = new String("Terima kasih!");
                        byteToClient = thanks.getBytes();
                    }
                    DatagramPacket sendPacket
                            = new DatagramPacket(byteToClient,
                                    byteToClient.length, IPAddress, port);
                    serverSocket.send(sendPacket);
                }
                System.out.println("Hub. client tertutup..");
            }
        }

    }
}
