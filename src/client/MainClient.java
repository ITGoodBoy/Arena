package client;

import common.Connection;
import common.Fighter;
import common.Message;
import common.MessageType;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Sergey on 28.03.2017.
 */
public class MainClient {

    private static AtomicLong atomicLong = new AtomicLong(1);

    public static void main(String[] args) throws IOException {
        Fighter fighter = new Fighter("Боец номер #" + atomicLong.getAndIncrement());
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
