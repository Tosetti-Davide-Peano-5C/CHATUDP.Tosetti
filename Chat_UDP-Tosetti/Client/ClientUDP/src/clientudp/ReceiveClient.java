/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientudp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc15
 */
public class ReceiveClient implements Runnable {
    
    DatagramSocket socket;

    ReceiveClient(DatagramSocket socket) {
        this.socket = socket;
    }

    
    @Override
    public void run() {
        
         byte[] buffer = new byte[100];
        String received;
        DatagramPacket serverDatagram;

 
        try {
            serverDatagram = new DatagramPacket(buffer, buffer.length);
            while (!Thread.interrupted()){
                socket.receive(serverDatagram);  
                received = new String(serverDatagram.getData(), 0, serverDatagram.getLength(), "ISO-8859-1");
                System.out.println("> server: " + received);
                System.out.print("> ");
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ReceiveClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReceiveClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

        
        
    }


