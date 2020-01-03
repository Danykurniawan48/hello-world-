/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UASPROGJAR;

/**
 *
 * @author - Dany Kurniawan Kusumah -
 */
public class JalankanOnlineServer {
    public static void main(String[] args){
        CariOnlineServer uas = new CariOnlineServer();
    
        String namaDomain = "google.com";
        String namaHost = "whois.internic.net";
        int port = 43;
    
        
        uas.setPort(43);
        uas.setNamaDomain("google.com");
        uas.setNamaHost("whois.internic.net");
        uas.panggilData();
    }
}
