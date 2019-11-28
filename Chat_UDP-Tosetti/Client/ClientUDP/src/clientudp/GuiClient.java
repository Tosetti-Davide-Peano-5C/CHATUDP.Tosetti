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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Acer
 */
public class GuiClient extends JFrame implements ActionListener {

    
     private JTextField Area;
     private JTextField Area2; 
     
     private JTextArea panel;
     private JButton invio = new JButton("INVIO");
     byte [] buffer;
     byte [] buffer2;
     
     DatagramSocket client;
     String messaggio;
    
     String ip = "127.0.0.1";
     
     
     JPanel pannello;
     JPanel pannello2;
     JPanel pannello3;

     
     InetAddress address = InetAddress.getByName(ip);
     
     
      public GuiClient () throws  SocketException,UnknownHostException{
          
        setTitle("UDP_CHAT");
        Area = new JTextField();
        panel = new JTextArea();
        buffer = new byte[1024];
        buffer2 = new byte[1024];
        pannello.setBorder(new TitledBorder("USERNAME && MESSAGGIO"));
        pannello.setLayout(new GridLayout(1, 3));
        pannello.add(Area);
        pannello.add(invio);
        
        
        pannello2 = new JPanel();
        
        pannello2.setLayout(new GridLayout(1, 1));
        pannello2.add(panel);
        
        
        Area2= new JTextField();
        
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        this.getContentPane().add(Area2, BorderLayout.NORTH);
        invio.addActionListener(this);
        pack();
        setSize(700, 500);
        setVisible(true);
        
        
     new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client = new DatagramSocket();
                    while (true) {
                        DatagramPacket pacchetto = new DatagramPacket(buffer, buffer.length);
                        client.receive(pacchetto);
                        String messaggio = new String(pacchetto.getData());
                        panel.append("Server: " + messaggio );
                    }
                } catch (Exception e) {
                }
            }
        }).start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
          if (e.getSource().equals(invio)) {
            try {
                String message = Area.getText();
                String username1 = Area2.getText();
                String messaggio = message + " & " + username1;
                buffer = messaggio.getBytes();
                DatagramPacket sendpack = new DatagramPacket(buffer, buffer.length, InetAddress.getLoopbackAddress(), 7676);
                client.send(sendpack);
                panel.append("l'utente e' " + username1 + " ed il messaggio e' : " + message );
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
      }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
    

