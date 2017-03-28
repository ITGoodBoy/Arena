package client;

import common.*;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Sergey on 28.03.2017.
 */
public class MainClient {

    public static void main(String[] args) throws IOException {
        Fighter fighter = new Fighter("Боец номер #" + Utilities.random(1, 10000));
        Socket socket = new Socket("localhost", 80);
        Connection connection = new Connection(socket);
        connection.sendMessage(new Message(fighter, MessageType.FIGHTER));

        while (true) {
            Message message = connection.receiveMessage();
            if (message == null) return;
            if (message.getMessageType() == MessageType.TEXT) {
                String text = message.getText();
                if (text != null) {
                    System.out.println(text);
                }
            }
        }
    }
}
