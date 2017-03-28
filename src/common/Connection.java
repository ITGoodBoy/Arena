package common;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Sergey on 28.03.2017.
 */
public class Connection implements Closeable{

    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private final Socket socket;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void sendMessage(Message message)  {
        synchronized (out) {
            try {
                out.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Message receiveMessage() {
        synchronized (in) {
            try {
                return (Message) in.readObject();
            } catch (IOException e) {
                return null;
            } catch (ClassNotFoundException e) {
                return null;
            }
        }
    }

    @Override
    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }
}
