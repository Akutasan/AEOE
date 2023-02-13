import java.io.File;
import java.util.*;

public class AEOE {
//    public static int[][] zweiDListe = new int[EinlesenDemo.liste.toArray().length][EinlesenDemo.liste.get(0).length()];
    public static void main(String[] args) {
        EinlesenDemo.einlesenAusgeben("src\\docs\\PlanetA-Q1_6x13.txt");
        ziel2_aufgabe3(EinlesenDemo.liste);
    }

    /**
     * Methode ziel2_aufgabe1
     *
     * Von der ArrayList zählen, wie viele von jedem Rohstoff existieren.
     *
     * @param liste Eine ArrayList von der Ausgabe der Planeten Datei
     */
    public static void ziel2_aufgabe3(ArrayList<String> liste){
        HashMap<Character, String> rohstoffAnzahl = new HashMap<>();
        for (String item : liste){
            // Erstelle 2D array
            for (char rohstoff : item.toCharArray()){
                // Füge eine Totale Anzahl zu als ersten index
                rohstoffAnzahl.put('t', (rohstoffAnzahl.get('t') == null) ? String.valueOf(1) : (String.valueOf(Integer.parseInt(rohstoffAnzahl.get('t')) + 1)));
                // Überprüfe ob der rohstoff schon in rohstoffAnzahl als key existiert, falls ja +1
                rohstoffAnzahl.put(rohstoff, (rohstoffAnzahl.get(rohstoff) == null) ? String.valueOf(1) : String.valueOf(Integer.parseInt(rohstoffAnzahl.get(rohstoff)) + 1));
            }
        }

        // Gehe durch alle items in der HashMap
        for (Map.Entry<Character, String> item : rohstoffAnzahl.entrySet()){
            // Checke ob der Key das Totale ist
            if (!item.getKey().equals('t')) {
                rohstoffAnzahl.put(item.getKey(), item.getValue() + ", " +
                        Math.round(100.0 * Double.parseDouble(String.valueOf(Integer.parseInt(item.getValue()))) /
                                Integer.parseInt(rohstoffAnzahl.get('t'))) + "%");
            }

        }
        IO.show(rohstoffAnzahl.toString());
    }

    /**
     * Methode ziel2_aufgabe2
     *
     * Von der ArrayList zählen, wie viele von jedem Rohstoff existieren.
     *
     * @param liste Eine ArrayList von der Ausgabe der Planeten Datei
     */
    public static void ziel2_aufgabe2(ArrayList<String> liste){
        HashMap<Character, Integer> rohstoffAnzahl = new HashMap<>();
        for (String item : liste){
            // Erstelle 2D array
            for (char rohstoff : item.toCharArray()){
                // Überprüfe ob der rohstoff schon in rohstoffAnzahl als key existiert, falls ja +1
                rohstoffAnzahl.put(rohstoff, (rohstoffAnzahl.get(rohstoff) == null) ? 1 : (rohstoffAnzahl.get(rohstoff) + 1));
            }
        }
        IO.show(rohstoffAnzahl.toString());
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
