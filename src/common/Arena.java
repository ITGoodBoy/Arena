package common;

import java.io.IOException;

public class Arena {
    //Duration of one round
    private final long ROUND_DELAY = 2000;

    private Connection oneConnection;
    private Connection twoConnection;
    private Fighter oneFighter;
    private Fighter twoFighter;

    public Arena(Connection oneConnection, Connection twoConnection){
        this.oneConnection = oneConnection;
        this.twoConnection = twoConnection;
    }


    private void handshake() {
        Message message;
        while (oneFighter == null) {
            message = oneConnection.receiveMessage();
            if (message.getMessageType() == MessageType.FIGHTER) {
                oneFighter = message.getFighter();
            }
        }

        while (twoFighter == null) {
            message = twoConnection.receiveMessage();
            if (message.getMessageType() == MessageType.FIGHTER) {
                twoFighter = message.getFighter();
            }
        }
    }


    public void faithing(){
        handshake();
       // inform about the entrance of the fighters into the arena
        inArenaMessage(oneConnection, twoConnection, oneFighter, twoFighter);
        //Who beats first
        boolean whoFirst = Utilities.random(0, 1) == 0;

        while (true) {
            if (whoFirst) {
                twoConnection.sendMessage(new Message(oneFighter.attack(twoFighter), MessageType.TEXT));
                if (!twoFighter.defeated())
                   oneConnection.sendMessage(new Message(twoFighter.attack(oneFighter), MessageType.TEXT));
            }
            else {
                oneConnection.sendMessage(new Message(twoFighter.attack(oneFighter), MessageType.TEXT));
                if (!oneFighter.defeated())
                    twoConnection.sendMessage(new Message(oneFighter.attack(twoFighter), MessageType.TEXT));
            }
            if (oneFighter.defeated()) {
                twoFighter.setHealPoint(100);
                endFaith(oneConnection, twoConnection);
                return;
            }
            if (twoFighter.defeated()){
                oneFighter.setHealPoint(100);
                endFaith(twoConnection, oneConnection);
                return;
            }
            Utilities.sleep(ROUND_DELAY);
        }
    }

    private void inArenaMessage(Connection oneConnection, Connection twoConnection, Fighter oneFighter, Fighter twoFighter){
        oneConnection.sendMessage(new Message("ваш противник " + twoFighter + " Вошёл на арену", MessageType.TEXT));
        twoConnection.sendMessage(new Message("ваш противник " + oneFighter + " Вошёл на арену", MessageType.TEXT));
    }

    private void endFaith(Connection winner, Connection looser){
        winner.sendMessage(new Message("Поздравляем вы победили", MessageType.TEXT));
        looser.sendMessage(new Message("Жаль, но вы проиграли, подкачайтесь и приходите ещё))", MessageType.TEXT));
        try {
            oneConnection.close();
            twoConnection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
