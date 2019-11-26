/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverudp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc15
 */
public class Messaggi implements Runnable {
    
    private ArrayList<String> memoria = new ArrayList<String>();
    private DatagramSocket ss;
    private Clients utenti;

    public Messaggi() {
        
    }
    
    public void NuovoMessaggio(String messaggio){
     
        if (memoria.size() <= 10) {

               memoria.add(messaggio);
       
        } else {
            
            memoria.remove(0);

                memoria.add(messaggio);
        }

    
}
    
    
    
    public void MessaggiSalvati(DatagramSocket socket, Clients u){  
        this.ss = socket;
        this.utenti = u;
        
        new Thread(this).start();
    }


    
    @Override
    public void run() {
        
        for(int i = 0; i < memoria.size() ; i++) {
           
            try {
                byte[] buffer = new byte[memoria.get(i).length()];
                buffer = memoria.get(i).getBytes("UTF-8");
                DatagramPacket mess = new DatagramPacket(buffer,buffer.length,utenti.addr,utenti.port);
                ss.send(mess);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Messaggi.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Messaggi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    
}
