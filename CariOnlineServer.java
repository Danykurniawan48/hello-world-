/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UASPROGJAR;

import java.io.*;
import java.net.*;

/**
 *
 * @author - Dany Kurniawan Kusumah -
 */
public class CariOnlineServer {
        private String namaDomain = "google.com";
        private String namaHost = "whois.internic.net";
        private int port = 43;

    public String getNamaDomain() {
        return namaDomain;
    }

    public void setNamaDomain(String namaDomain) {
        this.namaDomain = namaDomain;
    }

    public String getNamaHost() {
        return namaHost;
    }

    public void setNamaHost(String namaHost) {
        this.namaHost = namaHost;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
        
                
    public void panggilData(){
        try (Socket socket = new Socket(namaHost, port)) {
            OutputStream dataOutput = socket.getOutputStream();
            PrintWriter dataPrint = new PrintWriter(dataOutput, true);
            dataPrint.println(namaDomain);
            InputStream inputData = socket.getInputStream();
            BufferedReader bacaInput = new BufferedReader(new InputStreamReader(inputData));
            String barisText;
            while ((barisText = bacaInput.readLine()) != null) {
                System.out.println(barisText);
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server tidak ditemukan: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O terjadi kesalahan: " + ex.getMessage());
     }
    
    }
}