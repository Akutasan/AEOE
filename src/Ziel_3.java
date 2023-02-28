//Importanweisung

import java.io.File;
import javax.swing.*;
import java.awt.*;

public class Ziel_3 {

    public static void main(String[] args){
        PlanetenName();
    }
    public static void PlanetenName() {
        // Pfad zum Ordner angeben / oder unseren Planeten Ordner verwenden
        File folder = new File("src\\Z-Planeten");

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
            z3_start((String) dropdown.getSelectedItem());
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
            final ImageIcon icon = new ImageIcon("src\\Z-Bilder\\abbruch.gif");

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
            final ImageIcon icon = new ImageIcon("src\\Z-Bilder\\planet.gif");

            //Ausgabetext fürs Panel
            String html = "Jolo " + PlanetName;

            //Weite
            int w = 200;

            //Ausgabe
            JOptionPane.showMessageDialog(null, String.format(html, w, w), "Ziel 3 - Exoplaneten Erfassung", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }
}
