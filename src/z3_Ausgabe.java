import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Beschreiben Sie hier die Klasse jksydkfgiiuzacsfhlk.
 *
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class z3_Ausgabe {

    public static void main(String[] args){
        Z3("A");
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

            databaseConnection(Planet);

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
    static void databaseConnection(String planet) {
        if (!MySQL.isConnected()) {
            MySQL.connect();
        }
        MySQL.getObject(planet, "Name", "Planet", "*");
        MySQL.close();
    }
}
