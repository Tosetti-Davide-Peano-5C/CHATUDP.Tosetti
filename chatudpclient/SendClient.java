/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatudpclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc15
 */
public class SendClient implements Runnable {

    DatagramSocket socket;
    InetAddress address;
    int UDP_port;

    SendClient(DatagramSocket socket, InetAddress address, int UDP_port) {
        this.socket = socket;
        this.address = address;
        this.UDP_port = UDP_port;
    }

    @Override
    public void run() {

        byte[] buffer;
        String messaggio;
        String username;
        Scanner tastiera = new Scanner(System.in);
        DatagramPacket userDatagram;
        System.out.println("Inserisci username");
        username = tastiera.nextLine();
        try {
            System.out.print("> ");
            do {
                System.out.println("Inserisci messaggio");
                messaggio = tastiera.nextLine();
                messaggio = username + "#" + messaggio;
                buffer = messaggio.getBytes("UTF-8");

                userDatagram = new DatagramPacket(buffer, buffer.length, address, UDP_port);
                socket.send(userDatagram);
            } while (messaggio.compareTo("quit") != 0);
        } catch (IOException ex) {
            Logger.getLogger(SendClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
