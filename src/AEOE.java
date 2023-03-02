import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;

public class AEOE {
    public static void main(String[] args) {
        PlanetenName();
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
        UIManager UI = new UIManager();
        UI.put("OptionPane.messageForeground", Color.white);
        UI.put("Panel.background", Color.white);
        UI.put("OptionPane.background", new java.awt.Color(31, 99, 151));
        UI.put("Panel.background", new java.awt.Color(19, 60, 91));


        // Dialogfenster erzeugen und Dropdown-Liste hinzufügen
        JPanel panel = new JPanel();
        panel.add(dropdown);
        int result = JOptionPane.showConfirmDialog(null, panel, "Ziel 3 - Gesuchten Planeten auswählen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Eingabe auswerten
        if (result == JOptionPane.CANCEL_OPTION) {
            z3_abbruch();
        } else {
            Einlesen.einlesenAusgeben("src\\docs\\"+ dropdown.getSelectedItem());
            AusgabeFenster.Z1(ziel2_aufgabe3(Einlesen.liste));
        }
    }

    private static void z3_abbruch() {
        {
            //Farbschema/Panel
            UIManager UI = new UIManager();
            UI.put("OptionPane.messageForeground", Color.white);
            UI.put("Panel.background", Color.white);
            UI.put("OptionPane.background", new java.awt.Color(31, 99, 151));
            UI.put("Panel.background", new java.awt.Color(19, 60, 91));

            //Anzeige Bild
            final ImageIcon icon = new ImageIcon("src\\img\\abbruch.gif");

            //Ausgabetext fürs Panel
            String html = "<html><body width='%1s'>EXO...PROJEKT...[abgebrochen]";

            //Weite
            int w = 200;

            //Ausgabe
            JOptionPane.showMessageDialog(null, String.format(html, w, w), "Ziel 3 - Exoplaneten Erfassung", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }

    private static void z3_start(String PlanetName) {
        {
            //Farbschema/Panel
            UIManager UI = new UIManager();
            UI.put("OptionPane.messageForeground", Color.white);
            UI.put("Panel.background", Color.white);
            UI.put("OptionPane.background", new java.awt.Color(31, 99, 151));
            UI.put("Panel.background", new java.awt.Color(19, 60, 91));

            //Anzeige Bild
            final ImageIcon icon = new ImageIcon("src\\img\\planet.gif");

            //Ausgabetext fürs Panel
            String html = "Jolo " + PlanetName;

            //Weite
            int w = 200;

            //Ausgabe
            JOptionPane.showMessageDialog(null, String.format(html, w, w), "Ziel 3 - Exoplaneten Erfassung", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }
}
