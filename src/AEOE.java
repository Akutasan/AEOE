import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class AEOE {
    public static void main(String[] args) {
        PlanetenName();
    }

    /**
     * Methode ziel2_aufgabe1 von der ArrayList zählen, wie viele von jedem Rohstoff existieren.
     *
     * @param liste Eine ArrayList von der Ausgabe der Planeten Datei
     */
    public static HashMap<Character, Integer> ziel2_aufgabe3(ArrayList<String> liste) {
        HashMap<Character, Integer> rohstoffAnzahl = new HashMap<>();
        for (String item : liste) {
            // Erstelle 2D array
            for (char rohstoff : item.toCharArray()) {
                // Füge eine Totale Anzahl zu als ersten index
                rohstoffAnzahl.merge('t', 1, Integer::sum);

                // Überprüfe ob der rohstoff schon in rohstoffAnzahl als key existiert, falls ja +1
                rohstoffAnzahl.merge(rohstoff, 1, Integer::sum);
            }
        }
        return rohstoffAnzahl;
    }

    /**
     * Erstellt das Fenster mit "UIManager"
     */
    public static void createUI() {
        //Farbschema/Panel
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("Panel.background", Color.white);
        UIManager.put("OptionPane.background", new java.awt.Color(10, 80, 40));
        UIManager.put("Panel.background", new java.awt.Color(10, 50, 40));
    }


    /**
     * Erzeuge Dialogfenster, welches eine Dropdown-Liste mit den Namen der vorhandenen Planeten anzeigt.
     * Die entsprechende Datei wird eingelesen und an eine weitere Methode übergeben, welche die Daten des Planeten ausgibt und berechnet.
     */
    public static void PlanetenName() {
        // Pfad zum Ordner angeben / oder unseren Planeten Ordner verwenden
        File folder = new File("src\\docs");

        // Liste aller Dateien im Ordner abrufen
        File[] listOfFiles = folder.listFiles();

        //String für die Dropdown Liste
        assert listOfFiles != null;
        String[] PlanetName = new String[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            //Übertragen der Dateinamen in den Dropdown String
            PlanetName[i] = listOfFiles[i].getName();
        }

        // Dropdown-Liste erzeugen
        JComboBox<String> dropdown = new JComboBox<>(PlanetName);

        //Stiel und Farbe des Ausgabefensters
        AEOE.createUI();


        // Dialogfenster erzeugen und Dropdown-Liste hinzufügen
        JPanel panel = new JPanel();
        panel.add(dropdown);
        int result = JOptionPane.showConfirmDialog(null, panel, "Ziel 3 - Gesuchten Planeten auswählen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Eingabe auswerten
        if (result == JOptionPane.CANCEL_OPTION) {
            z3_abbruch();
        } else {
            Einlesen.einlesenAusgeben("src\\docs\\" + dropdown.getSelectedItem());
            AusgabeFenster.Z1(ziel2_aufgabe3(Einlesen.liste));
        }
    }

    /**
     * Methode, die die Auswahl eines Planeten abbricht, Dialogfenster mit einer Meldung wird angezeigt.
     */
    public static void z3_abbruch() {
        {
            // Erstelle Fenster
            AEOE.createUI();

            // Anzeige Bild
            final ImageIcon icon = new ImageIcon("src\\img\\abbruch.gif");

            // Ausgabetext für das Panel
            String html = "<html><body width='%1s'>EXO...PROJEKT...[abgebrochen]";

            // Weite
            int w = 200;

            // Ausgabefenster
            JOptionPane.showMessageDialog(null, String.format(html, w, w), "Ziel 3 - Exoplaneten Erfassung", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }
}
