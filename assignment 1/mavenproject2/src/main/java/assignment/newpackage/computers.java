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

/**
 *
 * @author dell
 */
public class computers {

    public static void main(String[] args) {
        try {
            //1.open server socket

            //////////////////////////////////////////
            ServerSocket sv = new ServerSocket(1234);
            System.out.println("Server Running...");
            while (true) {
                //2.accept connection
                Socket s = sv.accept();
                System.out.println("Client Accepted...");
                //3.create I/O streams
                DataInputStream comis = new DataInputStream(s.getInputStream());
                DataOutputStream comos = new DataOutputStream(s.getOutputStream());

                //4.perform IO with client
                while (true) {
                    String usr_msg = comis.readUTF();
                    System.out.println("Client says:" + usr_msg);
                    comos.writeUTF("Hello Connected");
                    comos.flush();
                    if (usr_msg.equals("get the recommendation")) {
                        comos.writeUTF("send the data");
                        comos.flush();
                    }
                    usr_msg = comis.readUTF();
                    if (usr_msg.equals("data"))
                    {
                        InetAddress ip = InetAddress.getLocalHost();
                        Socket server = new Socket(ip, 1230);
                        //2. Create I/O streams
                        DataInputStream sis = new DataInputStream(server.getInputStream());
                        DataOutputStream sos = new DataOutputStream(server.getOutputStream());
                        while (true) {
                            sos.writeUTF("Hello Connect to the server");
                            sos.flush();
                            String received = sis.readUTF();
                            if (received.equals("Hello Connected"))
                            {
                                System.out.println("server: " + received);
                                sos.writeUTF("get the recommendation");
                                sos.flush();
                            }
                            received = sis.readUTF();
                            if (received.equals("send the data"))
                            {
                                System.out.println("server: " + received);
                                sos.writeUTF(usr_msg);
                                sos.flush();
                            }
                            received = sis.readUTF();
                           if (received.equals("the recommendations"))
                            {
                                System.out.println("server: " + received);
                                sos.writeUTF("bye");
                                sos.flush();
                                comos.writeUTF(received);
                                comos.flush();
                            
                            sis.close();
                            sos.close();
                            server.close();
                            break;
                            }

                        }
                        
                    }
                    usr_msg = comis.readUTF();
                    if (usr_msg.equals("bye")) {
                        break;
                    }
                }

                //5.close connection
                comis.close();
                comos.close();
                s.close();
            }

            //6.close server
            //sv.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
