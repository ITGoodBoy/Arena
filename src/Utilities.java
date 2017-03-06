
public class Utilities {

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static int random(int minValue, int maxValue){
        return (int) (Math.random() * (maxValue - minValue + 1)) + minValue;
    }

    public static void printText(String text) {
        System.out.println(text);
    }

    public static String getColorText(String text, String textColor) {
        return textColor + text + ANSI_BLACK;
    }

    public static String getColorText(int text, String textColor) {
        return textColor + text + ANSI_BLACK;
    }

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
