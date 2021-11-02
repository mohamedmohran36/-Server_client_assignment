/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment.newpackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class servers {
          public static void main(String[] args) {
        try {
            //1.open server socket

            //////////////////////////////////////////
            ServerSocket sv = new ServerSocket(1230);
            System.out.println("Servers Running...");
            while (true) {
                //2.accept connection
                Socket comp = sv.accept();
                System.out.println("Client Accepted...");
                //3.create I/O streams
                DataInputStream sis = new DataInputStream(comp.getInputStream());
                DataOutputStream sos = new DataOutputStream(comp.getOutputStream());

                //4.perform IO with client
                while (true) {
                    String usr_msg = sis.readUTF();
                    System.out.println("Client says:" + usr_msg);
                    sos.writeUTF("Hello Connected");
                    sos.flush();
                    usr_msg = sis.readUTF(); 
                    if (usr_msg.equals("get the recommendation"))
                    {
                        sos.writeUTF("send the data");
                        sos.flush();  
                    }
                    usr_msg = sis.readUTF(); 
                    if (usr_msg.equals("data"))
                    {
                        sos.writeUTF("the recommendations");
                        sos.flush();
                    }
                     usr_msg = sis.readUTF(); 
                    if (usr_msg.equals("bye"))
                    {
                        break;
                    }
                }

                //5.close connection
             sis.close();
                sos.close();
                comp.close();
            }

            //6.close server
            //sv.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
