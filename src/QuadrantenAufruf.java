import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class QuadrantenAufruf {

    public static void main(String[] args) {
        PlanetenAuswahl();
    }

    /**
     * Erstellt eine Dropdown-Liste mit allen Planeten aus der Datenbank, die man auswählen kann.
     * Wenn keine Planeten in der Datenbank vorhanden sind, wird  eine Fehlermeldung angezeigt.
     * Methode ruft die Methode "Z3" auf, wenn ein Planet ausgewählt wurde.
     */
    public static void PlanetenAuswahl() {
        // Überprüfen, ob die Datenbankverbindung nicht besteht
        if (!MySQL.isConnected()) {
            // Wenn keine Verbindung besteht, eine neue Verbindung herstellen
            MySQL.connect();
        }

        // Erstelle Variable, welche alle Planet in eine 2D Array tut
        String[] DBplanet = MySQL.getAllPlanets();

        // Datenbankverbindung schließen
        MySQL.close();

        // Erstelle das Fenster
        AEOE.createUI();

        // Prüfe, ob es Planeten zur Ausgabe gibt
        if (DBplanet.length == 0) {

            // Bereite Bild vor
            final ImageIcon icon = new ImageIcon("src\\img\\abbruch.gif");

            // Ausgabetext für das Panel
            String html = "<html><body width='%1s'><h1>EXO...projekt...[F3HL3R!!!]</h1>"
                    + "<h2>keine Planeten zur verfügung...</h2>"
                    + "<p>- Bitte erfassen Sie erst einen Planeten in der Datenbank... (AEOE.java)";

            // Weite
            int w = 500;

            // Zeige Error-Nachricht
            JOptionPane.showMessageDialog(null, String.format(html, w, w), "- Keine Daten gefunden", JOptionPane.INFORMATION_MESSAGE, icon);
        } else {
            // Dropdown-Liste erzeugen
            JComboBox<String> dropdown = new JComboBox<>(DBplanet);

            // Dialogfenster erzeugen und Dropdown-Liste hinzufügen
            JPanel panel = new JPanel();
            panel.add(dropdown);
            int result = JOptionPane.showConfirmDialog(null, panel, "Ziel 3 - Planeten zum Quadranten-vergleich auswählen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Eingabe auswerten und entsprechende Methode aufrufen
            if (result == JOptionPane.CANCEL_OPTION) {
                AEOE.z3_abbruch();
            } else {
                Z3((String) dropdown.getSelectedItem());
            }
        }
    }

    /**
     * Methode zum Anzeigen der Datenbankinformationen für einen Planeten, der per Parameter übergeben wird.
     * Holt Daten aus Datenbank, um in einer Tabelle Quadranten-Bezeichnung, Gold, Silber, Uran, Kupfer, Zink, Total und Bewertung zu schreiben.
     *
     * @param Planet Der Planet-Name, der Ausgewertet werden soll.
     */
    static void Z3(String Planet) {
        // Erstelle das Fenster
        AEOE.createUI();

        // Erstelle HashMap Variable, die alle Infos von einem Planeten holt
        HashMap<String, String> planetsArray = databaseConnection(Planet);

        // Ausgabetext für das Panel
        StringBuilder html = new StringBuilder("<html><body width='%1s'><h1>Abschlussbericht des ExoProjekts</h1>"
                + "Planet: " + Planet + "<h2></h2>"
                // Erstellen einer Tabelle für eine übersichtliche Ausgabe
                + "<table>"
                + "<tr><th>Quadrant</th><th>Gold</th><th>Silber</th><th>Uran</th><th>Kupfer</th><th>Zink</th><th>Total</th><th>Bewertung</th></tr></b>");

        // Durchläuft alle Einträge der HashMap planetsArray und fügt sie in eine Tabelle in html-Format ein.
        for (Map.Entry<String, String> item : planetsArray.entrySet()) {
            // "tempSplit" wird aus dem item erzeugt und dann anhand des "=" Zeichens getrennt
            String[] tempSplit = item.toString().split("=");

            // "tempNummern" wird aus dem "tempSplit" Array erzeugt und wird anhand der "[" und "]" Zeichen entfernt und anhand des "," Zeichens getrennt
            String[] tempNummern = tempSplit[1].replace("[", "").replace("]", "").split(",");

            // Zu dem HTML Element wird ein neues Tabellenzeilen-Element hinzugefügt und die Bezeichnung des Quadranten (tempSplit[0]) wird als erstes Tabellenelement hinzugefügt
            html.append("<tr>").append("<td>").append(tempSplit[0]).append("</td>");

            // Durchläuft jedes Element von "tempNummern", ausgenommen der letzten beiden Elemente
            for (int i = 0; i < tempNummern.length - 2; i++) {
                // Das aktuelle Element von "tempNummern" wird als Tabellenelement hinzugefügt.
                html.append("<td>");
                html.append(tempNummern[i]);
                html.append("</td>");
            }

            // Letztes Element von "tempNummern" wird als Tabellenelement hinzugefügt
            html.append("<td>");
            html.append(tempNummern[6]).append("/5 Sternen</td></tr>");
        }

        // Schließt die Tabelle
        html.append("</table>");

        // Weite des Fensters
        int w = 500;

        // Zeige Fenster an
        JOptionPane.showMessageDialog(null, String.format(html.toString(), w, w), "Ziel 3 - ExoDatenbank", JOptionPane.INFORMATION_MESSAGE, null);
    }

    /**
     * Stellt eine Verbindung zur MySQL-Datenbank her und ruft alle Daten für den im Parameter weitergegebenen Planeten auf.
     * Erstellt und gibt eine HashMap mit allen Quadranten und den jeweiligen Daten als Wert zurück
     *
     * @param planet der Planet, für den die Daten abgerufen werden sollen
     * @return HashMap mit allen Quadranten und den jeweiligen Daten als Wert
     */
    static HashMap<String, String> databaseConnection(String planet) {
        // Überprüfen, ob die Datenbankverbindung nicht besteht
        if (!MySQL.isConnected()) {
            // Wenn keine Verbindung besteht, eine neue Verbindung herstellen
            MySQL.connect();
        }

        // Erstelle eine neue HashMap für den Rückgabewert
        HashMap<String, String> returnMap = new HashMap<>();

        // Hole alle Quadranten-bezeichnungen für den ausgewählten Planeten
        String[] allQuadrantsBezeichnung = MySQL.getAllQuadrantsBezeichnung(planet);

        // Für jede Quadranten-bezeichnung die Daten aus der Datenbank holen und der HashMap hinzufügen
        for (String item : allQuadrantsBezeichnung) {
            // Hole alle Daten für den Quadranten und speichere sie in einer Liste
            ArrayList<String> list = new ArrayList<>(Arrays.asList(MySQL.getAllDataFromQuadrants(planet, item)));
            returnMap.put(list.get(0), String.valueOf(list.subList(1, list.size())));
        }

        // Datenbankverbindung schließen
        MySQL.close();

        // Rückgabe der HashMap mit allen Daten für die ausgewählten Quadranten
        return returnMap;
    }
}
