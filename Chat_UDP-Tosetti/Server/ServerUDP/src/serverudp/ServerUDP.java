/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverudp;

import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class ServerUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException   {
 {
        // TODO code application logic here
        
             
        int c;
        Thread thread;
        
         try {
             
         
        UDPEcho echoserver = new UDPEcho (4444);
        thread= new Thread(echoserver);
        
        
         thread.start();
            c=System.in.read();
            //echoServer.interrupt();
            thread.interrupt();
            //echoServer.join();
            thread.join();
            System.out.println("sono il main");
         
         } catch (SocketException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        
        
    
    
} 

}
    }
}
    

