import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Einlesen {
    // Globale Variablen für übergreifendes späteres Einlesen
    public static ArrayList<String> liste = new ArrayList<>();
    public static String dataName;

    /**
     * Liest eine Datei mit dem übergebenen Dateinamen ein und fügt die einzelnen Zeilen der Datei einer ArrayList hinzu.
     *
     * @param dateiname der Name der Datei, die eingelesen werden soll
     */
    static void einlesenAusgeben(String dateiname) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(dateiname));

            String st;

            // Füge jede Zeile der Datei zur Liste hinzu
            while ((st = br.readLine()) != null)
                liste.add(st);

            // Speichere den Dateinamen
            dataName = dateiname;
        }

        // Fehlerbehandlung
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}