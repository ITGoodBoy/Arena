import java.util.ArrayList;
import java.util.Collections;

public class MegaArena {

    private final static ArrayList<Arena> arenaList = new ArrayList<>();
    static {
        Collections.synchronizedList(arenaList);
    }
    private ArrayList<Fighter> fighterList = new ArrayList<>();

    public MegaArena(int arenaCount){
        for (int i = 0; i < arenaCount; i++) {
            arenaList.add(new Arena(new Fighter("Случайный боец #" + Utilities.random(1, 100000)),
                    new Fighter("Случайный боец #" + Utilities.random(1, 100000))));
        }
    }

    public void megaFaithing(){
        while (true) {
            for (Arena arena : arenaList) {
                arena.start();
            }

            for (Arena arena : arenaList) {
                try {
                    arena.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (Arena arena : arenaList) {
                fighterList.add(arena.getWinner());
            }
            if (fighterList.size() == 1){
                endFaith(fighterList.get(0));
                break;
            }
            arenaList.clear();

            while (fighterList.size() > 1){
                arenaList.add(new Arena(fighterList.get(0), fighterList.get(1)));
                fighterList.remove(0);
                fighterList.remove(0);
            }
        }
    }

    private Fighter endFaith(Fighter fighter){
        Utilities.printText("Повелитель арен, великий и непревзойдённый " + fighter);
        return fighter;
    }
}
