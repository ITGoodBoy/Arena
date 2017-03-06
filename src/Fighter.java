public class Fighter {

    private String name;
    private int healPoint;
    private int strength;
    private int agility;
    private int intuition;

    public Fighter(String name) {
        this.name = name;
        initializeParams();
    }

    private void initializeParams() {
        healPoint = 100;
        strength = Utilities.random(1, 100);
        agility = Utilities.random(0, 100);
        intuition = Utilities.random(0, 100);

        int sumParams = strength + agility + intuition;

        double dex = 50f / sumParams;

        strength = (int) ((double) strength * dex) + 1;
        agility = (int) ((double) agility * dex);
        intuition = (int) ((double) intuition * dex);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealPoint() {
        return healPoint;
    }

    public void setHealPoint(int healPoint) {
        this.healPoint = healPoint;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntuition() {
        return intuition;
    }

    public void setIntuition(int intuition) {
        this.intuition = intuition;
    }

    public void attack(Fighter anotherFighter) {
        if (anotherFighter.evadeChance())
            Utilities.printText("боец " + anotherFighter.name + Utilities.getColorText(" уклонился ", Utilities.ANSI_BLUE));
        else {
            int randomDamage = Utilities.random(1, strength);
            if (criticalChance()) {
                int criticalDamage = randomDamage * 2;
                Utilities.printText("боец " + name + "(" +
                        Utilities.getColorText(healPoint, Utilities.ANSI_GREEN) + ") нанёс " +
                        Utilities.getColorText("Критический ", Utilities.ANSI_RED) +
                        "удар бойцу " + anotherFighter.name +
                        "(" + Utilities.getColorText(anotherFighter.healPoint, Utilities.ANSI_GREEN) + ") на " + criticalDamage);
                anotherFighter.healPoint -= criticalDamage;
            }
            else {
                Utilities.printText("боец " + name + "(" +
                        Utilities.getColorText(healPoint, Utilities.ANSI_GREEN) + ") нанёс удар бойцу "
                        + anotherFighter.name +  "(" +
                        Utilities.getColorText(anotherFighter.healPoint, Utilities.ANSI_GREEN) + ")" + " на " + randomDamage);
                anotherFighter.healPoint -= randomDamage;
            }
        }
    }

    private boolean evadeChance() {
        return Utilities.random(1, 100) <= agility;
    }

    private boolean criticalChance() {
        return Utilities.random(1, 100) <= intuition;
    }

    public boolean defeated(){
        return healPoint <= 0;
    }

    @Override
    public String toString() {
        return Utilities.getColorText(name, Utilities.ANSI_CYAN)  +
                ", " + Utilities.getColorText("healPoint ", Utilities.ANSI_RED) + Utilities.getColorText(healPoint, Utilities.ANSI_GREEN) +
                ", " + Utilities.getColorText("strength ", Utilities.ANSI_RED) + Utilities.getColorText(strength, Utilities.ANSI_GREEN) +
                ", " + Utilities.getColorText("agility ", Utilities.ANSI_BLUE) + Utilities.getColorText(agility, Utilities.ANSI_GREEN) +
                ", " + Utilities.getColorText("intuition ", Utilities.ANSI_YELLOW) + Utilities.getColorText(intuition, Utilities.ANSI_GREEN);
    }
}
