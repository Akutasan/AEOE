import java.util.*;

public class AEOE {
    public static void main(String[] args) {
        Einlesen.einlesenAusgeben("src\\docs\\PlanetB-Q5_50x100.txt");
        AusgabeFenster.Z1(ziel2_aufgabe3(Einlesen.liste));
    }

    /**
     * Methode ziel2_aufgabe1
     *
     * Von der ArrayList zählen, wie viele von jedem Rohstoff existieren.
     *
     * @param liste Eine ArrayList von der Ausgabe der Planeten Datei
     */
    public static HashMap<Character, Integer> ziel2_aufgabe3(ArrayList<String> liste){
        HashMap<Character, Integer> rohstoffAnzahl = new HashMap<>();
        for (String item : liste){
            // Erstelle 2D array
            for (char rohstoff : item.toCharArray()){
                // Füge eine Totale Anzahl zu als ersten index
                rohstoffAnzahl.merge('t', 1, Integer::sum);

                // Überprüfe ob der rohstoff schon in rohstoffAnzahl als key existiert, falls ja +1
                rohstoffAnzahl.merge(rohstoff, 1, Integer::sum);
            }
        }
        return rohstoffAnzahl;
    }
}
