import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Beschreiben Sie hier die Klasse jksydkfgiiuzacsfhlk.
 *
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class QuadrantenAufruf {

    public static void main(String[] args) {
        PlanetenAuswahl();
    }

    public static void createUI() {
        //Farbschema/Panel
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("Panel.background", Color.white);
        UIManager.put("OptionPane.background", new java.awt.Color(10, 80, 40));
        UIManager.put("Panel.background", new java.awt.Color(10, 50, 40));
    }

    public static void PlanetenAuswahl() {
        if (!MySQL.isConnected()) {
            MySQL.connect();
        }
        //String für die Dropdown Liste anpassen
        String[] DBplanet = MySQL.getAllPlanets();//eretze durch DB String pull Planeten Namen
        MySQL.close();

        createUI();
        if (DBplanet.length == 0) {

            //Anzeige Bild
            final ImageIcon icon = new ImageIcon("src\\img\\abbruch.gif");

            //Ausgabetext fürs Panel
            String html = "<html><body width='%1s'><h1>EXO...projekt...[F3HL3R!!!]</h1>"
                    + "<h2>keine Planeten zur verfügung...</h2>"
                    + "<p>- Bitte erfassen Sie erst einen Planeten in der Datenbank... (AEOE.java)";

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
        //Farbschema/Panel
        createUI();

        HashMap<String, String> planetsArray = databaseConnection(Planet);

        //Ausgabetext fürs Panel
        StringBuilder html = new StringBuilder("<html><body width='%1s'><h1>Abschlussbericht des ExoProjekts</h1>"
                + "Planet: " + Planet + "<h2></h2>"
                //erstellen einer Tabelle für eine übersichtliche Ausgabe
                + "<table>"
                + "<tr><th>Quadrant</th><th>Gold</th><th>Silber</th><th>Uran</th><th>Kupfer</th><th>Zink</th><th>Total</th><th>Bewertung</th></tr></b>");
        //Dateien werden in die Tabelle eingetragen
        for (Map.Entry<String, String> item : planetsArray.entrySet()) {
            String[] tempSplit = item.toString().split("=");
            String[] tempNummern = tempSplit[1].replace("[", "").replace("]", "").split(",");
            html.append("<tr>").append("<td>").append(tempSplit[0]).append("</td>");
            for (int i = 0; i < tempNummern.length - 2; i++) {
                html.append("<td>");
                html.append(tempNummern[i]);
                html.append("</td>");
            }
            //Spalten für jeden Quadranten ausfüllen
            html.append("<td>");
            html.append(tempNummern[6]).append("/5 Sternen</td></tr>");
        }

        html.append("</table>");

        //Weite
        int w = 500;

        JOptionPane.showMessageDialog(null, String.format(html.toString(), w, w), "Ziel 3 - ExoDatenbank", JOptionPane.INFORMATION_MESSAGE, null);
    }

    //Verbindung zur Datenbank wird hergestellt
    static HashMap<String, String> databaseConnection(String planet) {
        if (!MySQL.isConnected()) {
            MySQL.connect();
        }
        HashMap<String, String> returnMap = new HashMap<>();

        String[] allQuadrantsBezeichnung = MySQL.getAllQuadrantsBezeichnung(planet);
        for (String item : allQuadrantsBezeichnung) {
            ArrayList<String> list = new ArrayList<>(Arrays.asList(MySQL.getAllDataFromQuadrants(planet, item)));
            returnMap.put(list.get(0), String.valueOf(list.subList(1, list.size())));
        }
        MySQL.close();
        return returnMap;
    }
}
