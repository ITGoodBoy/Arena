package server;

import common.*;



/**
 * Created by Sergey on 28.03.2017.
 */
public class ClientHandler extends Thread {

    private Connection oneConnection;
    private Connection twoConnection;

    public ClientHandler(Connection oneConnection, Connection twoConnection) {
        this.oneConnection = oneConnection;
        this.twoConnection = twoConnection;
    }

    @Override
    public void run() {
        Arena arena = new Arena(oneConnection, twoConnection);
        arena.faithing();
    }



}
