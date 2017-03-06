
public class Arena extends Thread {
    //Duration of one round
    private final long ROUND_DELAY = 200;

    private Fighter oneFighter;
    private Fighter twoFighter;

    private Fighter winner;

    public Arena(){
        this(new Fighter("Petya"), new Fighter("Vasya"));
    }

    public Arena(Fighter oneFighter, Fighter twoFighter){
        this.oneFighter = oneFighter;
        this.twoFighter = twoFighter;
    }

    public Fighter getWinner() {
        return winner;
    }

    @Override
    public void run() {
        faithing(ROUND_DELAY);
    }

    public void faithing(long roundDelay){
       // inform about the entrance of the fighters into the arena
        inArenaMessage(oneFighter);
        inArenaMessage(twoFighter);
        //Who beats first
        boolean whoFirst = Utilities.random(0, 1) == 0;

        while (true) {
            if (whoFirst) {
                oneFighter.attack(twoFighter);
                if (!twoFighter.defeated())
                    twoFighter.attack(oneFighter);
            }
            else {
                twoFighter.attack(oneFighter);
                if (!oneFighter.defeated())
                    oneFighter.attack(twoFighter);
            }
            if (oneFighter.defeated()) {
                twoFighter.setHealPoint(100);
                winner = endFaith(twoFighter);
                return;
            }
            if (twoFighter.defeated()){
                oneFighter.setHealPoint(100);
                winner = endFaith(oneFighter);
                return;
            }
            Utilities.sleep(roundDelay);
        }
    }

    private void inArenaMessage(Fighter fighter){
        Utilities.printText(fighter + " Вошёл на арену");
    }

    private Fighter endFaith(Fighter fighter){
        Utilities.printText("Победитель " + fighter);
        return fighter;
    }

}
