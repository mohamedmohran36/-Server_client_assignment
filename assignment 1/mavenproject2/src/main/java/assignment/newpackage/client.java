/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment.newpackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author dell
 */
public class client {

    public static void main(String[] args) throws IOException {
        try {
            //1.create socket and connect to the server
            //with IP:127.0.0.1(localhost)
            //and with portnumber: 1234
            InetAddress ip = InetAddress.getLocalHost();
            Socket s = new Socket(ip, 1234);
            //2. Create I/O streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            //3.perform IO with server 
            while (true) {
                dos.writeUTF("Hello Connect to the server");
                dos.flush();
                String received = dis.readUTF();

                if (received.equals("Hello Connected")) {
                    System.out.println("server: " + received);
                   dos.writeUTF("get the recommendation");
                   dos.flush();
                }
                received = dis.readUTF();
                if (received.equals("send the data")) 
                {
                    System.out.println("server: " + received);
                    dos.writeUTF("data");
                    dos.flush();
                }
                received = dis.readUTF();
                 if (received.equals("the recommendations")) {
                    System.out.println("server: " + received);
                    dos.writeUTF("bye");
                    dos.flush();
                
                dis.close();
                dos.close();
                s.close();
                 }
            }
        }
            catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
