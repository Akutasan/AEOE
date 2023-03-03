import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Beschreiben Sie hier die Klasse jksydkfgiiuzacsfhlk.
 *
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class QuadrantenAufruf {

    public static void main(String[] args) {
//        PlanetenAuswahl("B");
    }

    public static void PlanetenAuswahl() {
        //pullrequest für Planeten Namen aus Datenbank

        //String für die Dropdown Liste anpassen
        String[] DBplanet = {"2"};//eretze durch DB String pull Planeten Namen

        UIManager UI = new UIManager();
        //Farbschema/Panel
        UI.put("OptionPane.messageForeground", Color.white);
        UI.put("Panel.background", Color.white);
        UI.put("OptionPane.background", new java.awt.Color(31, 99, 151));
        UI.put("Panel.background", new java.awt.Color(19, 60, 91));
        if (DBplanet.length == 0) {
            //WAs wenn keine Planeten

            //Anzeige Bild
            final ImageIcon icon = new ImageIcon("./Z-Bilder/abbruch.gif");

            //Ausgabetext fürs Panel
            String html = "<html><body width='%1s'><h1>EXO...projekt...[F3HL3R!!!]</h1>"
                    + "<h2>keine Planeten zur verfügung...</h2>"
                    + "<p>- Bitte erfassen Sie erst einen Planeten in der Datenbank...";

            //Weite
            int w = 500;

            JOptionPane.showMessageDialog(null, String.format(html, w, w), "- Keine Daten gefunden", JOptionPane.INFORMATION_MESSAGE, icon);

        } else {
            //Dropdown-Liste erzeugen
            JComboBox<String> dropdown = new JComboBox<>(DBplanet);

            // Dialogfenster erzeugen und Dropdown-Liste hinzufügen
            JPanel panel = new JPanel();
            panel.add(dropdown);
            int result = JOptionPane.showConfirmDialog(null, panel, "Ziel 3 - Planeten zum Quadrantenvergleich auswählen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Eingabe auswerten und entsprechende Methode aufrufen
            if (result == JOptionPane.CANCEL_OPTION) {
                AEOE.z3_abbruch();
            } else {
                Z3((String) dropdown.getSelectedItem());
            }
        }
    }

    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    /*private*/
    static void Z3(String Planet) {
        {
            //Variablen
            int menge = 3;

            //Farbschema/Panel
            UIManager UI = new UIManager();
            UI.put("OptionPane.messageForeground", Color.white);
            UI.put("Panel.background", Color.white);
            UI.put("OptionPane.background", new Color(31, 99, 151));
            UI.put("Panel.background", new Color(19, 60, 91));

            System.out.println(databaseConnection(Planet));

            //Ausgabetext fürs Panel
            String html = "<html><body width='%1s'><h1>Abschlussbericht des ExoProjekts</h1>"
                    + "Planet: " + Planet + "<h2></h2>"
                    //erstellen einer Tabelle für eine übersichtliche Ausgabe
                    + "<table>"
                    + "<tr><td>QuadrantenName</td><td>Gold</td><td>Silber</td><td>Uran</td><td>Kupfer</td><td>Zink</td><td>Total</td><td>Bewertung</td></tr>";
            //Dateien werden in die Tabelle eingetragen
            for (int i = 0; i < menge; i++) {
                //Spalten für jeden Quadranten ausfüllen
                html = html + "<tr><td>" + "Name" + "</td><td>" + "Goldwert" + "</td><td>" + "Silberwert" + "</td><td>" + "Uranwert" + "</td><td>" + "Kupferwert" + "</td><td>" + "Zinkwert" + "</td><td>" + "Totalwert" + "</td><td>" + "Bewertungswert" + "</td></tr>";
            }

            html = html + "</table>";

            //Weite
            int w = 500;

            JOptionPane.showMessageDialog(null, String.format(html, w, w), "Ziel 3 - ExoDatenbank", JOptionPane.INFORMATION_MESSAGE, null);
        }
    }

    //Verbindung zur Datenbank wird hergestellt
    static HashMap<String, String> databaseConnection(String planet) {
        if (!MySQL.isConnected()) {
            MySQL.connect();
        }
        HashMap<String, String> returnMap = new HashMap<>();

        String[] allQuadrantsBezeichnung = MySQL.getAllQuadrantsBezeichnung(planet);
        if (allQuadrantsBezeichnung.length != 0) {
            for (String item : allQuadrantsBezeichnung) {
                ArrayList<String> list = new ArrayList<>(Arrays.asList(MySQL.getAllDataFromQuadrants(planet, item)));
                returnMap.put(list.get(0), String.valueOf(list.subList(1, list.size())));
            }
        }
        MySQL.close();
        return returnMap;
    }
}
