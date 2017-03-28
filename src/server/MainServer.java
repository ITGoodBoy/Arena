package server;

import common.Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Sergey on 28.03.2017.
 */
public class MainServer {


    public static void main(String[] args) {
        ServerSocket server = null;
        try {
             server = new ServerSocket(80);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection = null;

        while (server != null) {
            try {
                Socket socket = server.accept();
                if (connection == null) connection = new Connection(socket);
                else {
                    new ClientHandler(connection, new Connection(socket)).start();
                    connection = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
