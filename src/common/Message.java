package common;

import java.io.Serializable;

/**
 * Created by Sergey on 28.03.2017.
 */
public class Message implements Serializable{

    private Fighter fighter;
    private String text;
    private MessageType messageType;

    public Message(MessageType messageType) {
        this.messageType = messageType;
    }


    public Message(Fighter fighter, MessageType messageType) {
        this.fighter = fighter;
        this.messageType = messageType;
    }

    public Message(String text, MessageType messageType) {
        this.text = text;
        this.messageType = messageType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }
}
