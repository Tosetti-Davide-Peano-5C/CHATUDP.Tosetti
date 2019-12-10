package clientudp;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tosetti.davide
 */
public class GUIchat extends JFrame implements ActionListener{
    
        JPanel jPanel1; 
        JScrollPane jScrollPane2 ;
        JScrollBar jScrollBar1  ;
        JPanel jPanel2 ; 
        JLabel labelUser;
        JTextField fieldUser ;
        JLabel labelMessaggio ;
        JTextField fieldMex;
        JButton bottone1  ; 
        JButton bottone2  ;
        JScrollPane jScrollPane3 ;
        JTextArea areaChat;
        private String indirizzoIP = "localhost";
        private String username ;
        private String messaggio;
        DatagramSocket socket;
       
            
            
            
    public GUIchat() {
        this.setVisible(true);
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel2 = new javax.swing.JPanel();
        labelUser = new javax.swing.JLabel();
        fieldUser = new javax.swing.JTextField();
        labelMessaggio = new javax.swing.JLabel();
        fieldMex = new javax.swing.JTextField();
        bottone1 = new javax.swing.JButton();
        bottone2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaChat = new javax.swing.JTextArea();
        areaChat.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelUser.setText("INSERISCI USERNAME:");

        fieldUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldUserActionPerformed(evt);
            }

            private void fieldUserActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        labelMessaggio.setText("INSERISCI IL MESSAGGIO:");
        

        bottone1.setText("LOGIN");
        bottone1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(!fieldUser.getText().equals(null) || !fieldUser.getText().equals("")){
                    username = fieldUser.getText();
                }
                JOptionPane.showMessageDialog(null, "Username Collegato Con Successo!");
                if (username != null) {
                    areaChat.append("Username: " + username);
                    areaChat.append("\n");
                }
            }

            
        });

        bottone2.setText("INVIO");
        bottone2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(username==null){
                    JOptionPane.showMessageDialog(null, "INSERISCI USERNAME");
                }else{
                    try {
                        invio(fieldMex.getText(), username);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(GUIchat.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(GUIchat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    fieldMex.setText("");
                }
            }

            
        });

        areaChat.setColumns(20);
        areaChat.setRows(5);
        jScrollPane3.setViewportView(areaChat);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labelMessaggio)
                                .addGap(0, 15, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldUser)
                            .addComponent(fieldMex, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bottone1)
                            .addComponent(bottone2))
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUser)
                    .addComponent(fieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bottone1))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMessaggio)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldMex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bottone2)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addGap(88, 88, 88))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        
        
    }// </editor-fold>             
public static  void main(String[] args) throws UnknownHostException {
    new GUIchat();
}
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      public void invio(String messaggio, String username) throws  UnknownHostException, IOException{
            DatagramPacket datagram;
            byte [] buffer;
            buffer= messaggio.getBytes("UTF-8");
            datagram= new DatagramPacket(buffer,buffer.length,InetAddress.getByName(indirizzoIP),1077);
            socket.send(datagram);
            
        }  
   public void ricezione () throws IOException{
       DatagramPacket datagram;
       byte[] buffer= new byte[8192];
       String risposta;
       datagram = new DatagramPacket(buffer, buffer.length);
       while(!Thread.interrupted()){
       socket.receive(datagram);
       risposta= new String(datagram.getData(),0,datagram.getLength(),"ISO-8859-1");
       areaChat.append(username + " # " + risposta + "\n");
   }
       socket.close();
   }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

