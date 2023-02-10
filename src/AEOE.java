import java.util.Arrays;

public class AEOE {
    public static void main(String[] args) {
        ziel1_aufgabe1();
        ziel1_aufgabe2(5);
        ziel1_aufgabe3();
    }

    /**
     * Methode ziel1_aufgabe3
     *
     * Von Reihung aus Ganzzahlen die größte und die kleinste Zahl ermittelt
     */
    public static void ziel1_aufgabe3() {
        int[][] ZDArr = zweiDimReihungen.zufallsZahlenreihung(5, 20);
        int value = ZDArr[0][0];

        for (int[] chars : ZDArr) {
            for (int aChar : chars) {
                if (aChar > value) {
                    value = aChar;
                }
            }
        }
        System.out.println(value);

        for (int[] chars : ZDArr) {
            for (int aChar : chars) {
                if (aChar < value) {
                    value = aChar;
                }
            }
        }
        System.out.println(value);
        System.out.println(Arrays.deepToString(ZDArr));
    }

    /**
     * Methode ziel1_aufgabe2
     *
     * Methode die das kleine 1x1 bis zur Zahl übergibt.
     * @param zahl wie oft die Multiplikation stattfinden soll
     */
    public static void ziel1_aufgabe2(int zahl) {
        String[][] daskleine1x1 = new String[zahl][10];
        for (int i = 0; i < zahl; i++) {
            for (int j = 0; j < 10; j++) {
                daskleine1x1[i][j] = (i + 1) + "*" + (j + 1) + "=" + ((j + 1) * (i + 1));
            }
        }
        System.out.println(Arrays.deepToString(daskleine1x1));
    }

    /**
     * Methode ziel1_aufgabe1
     *
     * Eine 2D Reihung und ein Zeichen übergeben bekommt und als Ganzzahl zurückgibt, wie oft dieses Zeichen enthalten ist.
     */
    public static void ziel1_aufgabe1() {
        char[][] ZDArr = zweiDimReihungen.zufallsZeichenreihung(5, 20);
        // Kurze Variante
        System.out.println(Arrays.deepToString(ZDArr).length() - Arrays.deepToString(ZDArr).replaceAll(String.valueOf(IO.getChar("Give me Char.")), "").length());
        System.out.println(Arrays.deepToString(ZDArr));

        // Lange Variante
        char gesucht = IO.getChar("Give me Char.");
        int counter = 0;

        for (char[] i : ZDArr) {
            for (char j : i) {
                if (j == gesucht) counter++;
            }
        }

        System.out.println(counter);
        System.out.println(Arrays.deepToString(ZDArr));
    }
}
