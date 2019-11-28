/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientudp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author pc15
 */




 

    public class ClientUDP{
    
    

    /**
     * @param args the command line arguments
     */
    public static  void main(String[] args) throws UnknownHostException {
        
        
        
        
      
    
        
        
        String IP_address = "127.0.0.1";
        InetAddress address = InetAddress.getByName(IP_address);
        int UDP_port = 4444;


        DatagramSocket socket;
        try {
            
            socket = new DatagramSocket();
            

            Thread receiveAndPrint = new Thread(new ReceiveClient(socket));
            receiveAndPrint.start();
            System.out.println("in ascolto");

            Thread sendUserInput = new Thread(new SendClient(socket, address, UDP_port));
            sendUserInput.start();
            System.out.println("inserisci messaggio");


            System.out.println("connessione riuscita");

           
            sendUserInput.join(); 
            receiveAndPrint.interrupt(); 
            receiveAndPrint.join(); 
            socket.close(); 

        } catch (SocketException ex) {
            System.out.println("connessione server non riuscita");
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
          
    
        
    
}




}
    

