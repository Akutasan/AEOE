/**
 * Klasse zur Demonstration der Arbeit mit Textdateien
 *
 * @author Sven Dräger
 * @version 05.01.2023
 */
//Importieren einer Klasse mit Methoden zur Datei-Bearbeitung

import java.io.*;
import java.util.ArrayList;

public class EinlesenDemo {
    public static ArrayList<String> liste = new ArrayList<>();

    /**
     * /**
     * Die Operation demonstriert das zeilenweise Einlesen einer Datei Testdatei.txt.
     * Die eingelesenen Zeilen werden in der Demo direkt auf dem Bildschirm ausgegeben.
     * Es ist bekannt, dass die Testdatei 6 Zeilen enthält.
     *
     * @param dateiname Name der einzulesenden und auszugebenden Datei als Zeichenkette, also z. B. "Testdatei.txt"
     */
    static void einlesenAusgeben(String dateiname) {

        //Da bei der Arbeit mit Dateien vielfältige Fehler auftreten können, muss eine sogenannte "Fehlerbehandlung" erfolgen.
        //Dies wird in Java mit einer try-catch-Struktur (Fachbegriff: Exception) umgesetzt.
        //Wenn möglich wird der try-Block ausgeführt.
        //Sollte ein Fehler auftreten (z. B. Datei existiert nicht, fehlende Leseberechtigung, etc.) wird der Fehler im catch-Block aufgefangen.
        try {
            //Erstellen eines Objektes "in" für das Einlesen: Öffnen der Datei mit dem übergebenen Dateinamen
            // Creating an object of BufferedReader class
            BufferedReader br = new BufferedReader(new FileReader(dateiname));

            String st;

            while ((st = br.readLine()) != null)
                liste.add(st);
        }

        //Fehlerbehandlung
        catch (IOException ioex) {
            IO.show("Lesefehler: " + ioex);
        }
    }
}