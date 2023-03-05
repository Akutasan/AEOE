import javax.swing.*;
import java.util.HashMap;


public class AusgabeFenster {

    /**
     * Methode zur Ausgabe. Konfiguriert ein Fenster und gibt alle Daten aus. Holt sich Daten aus HashMap-Parameter.
     *
     * @param rohstoffMap HashMap mit allen Daten. Key: Character Rohstoff, Value: Gefundene Menge des Rohstoffs
     */
    static void Z1(HashMap<Character, Integer> rohstoffMap) {
        // Erstellt Fenster
        AEOE.createUI();

        // Bereitet Bild vor
        final ImageIcon icon = new ImageIcon("src\\img\\erzprobescan.gif");

        // Ausgabetext für das Panel
        String html = "<html><body width='%1s'><h1>EXO...projekt...</h1>"
                + "<h2>Scan... [abgeschlossen]</h2>"
                + "<h2>Auswertung des Planeten...</h2>"
                + "<p>- Quadranten-Größe... [" + (rohstoffMap.get('t')) + " Felder]";

        // Anzeige der gefundenen Mengen
        // Berechnung des jeweiligen Gesamtanteils durch Operation getPercentage (unten)
        if (rohstoffMap.get('g') != null) {
            html = html + "<p>- Gold........................... [" + rohstoffMap.get('g') + "] (" + getPercentage(rohstoffMap, 'g') + ")";
        } else {
            rohstoffMap.put('g', 0);
            html = html + "<p>- Gold........................... [keine Daten]";
        }
        if (rohstoffMap.get('k') != null) {
            html += "<p>- Kupfer....................... [" + rohstoffMap.get('k') + "] (" + getPercentage(rohstoffMap, 'k') + ")";
        } else {
            rohstoffMap.put('k', 0);
            html += "<p>- Kupfer....................... [keine Daten]";
        }
        if (rohstoffMap.get('s') != null) {
            html += "<p>- Silber......................... [" + rohstoffMap.get('s') + "] (" + getPercentage(rohstoffMap, 's') + ")";
        } else {
            rohstoffMap.put('s', 0);
            html = html + "<p>- Silber......................... [keine Daten]";
        }
        if (rohstoffMap.get('u') != null) {
            html += "<p>- Uran........................... [" + rohstoffMap.get('u') + "] (" + getPercentage(rohstoffMap, 'u') + ")";
        } else {
            rohstoffMap.put('u', 0);
            html += "<p>- Uran........................... [keine Daten]";
        }
        if (rohstoffMap.get('z') != null) {
            html += "<p>- Zink............................ [" + rohstoffMap.get('z') + "] (" + getPercentage(rohstoffMap, 'z') + ")";
        } else {
            rohstoffMap.put('z', 0);
            html += "<p>- Zink............................ [keine Daten]";
        }

        // Gesamte Nummer von Erzen (Ohne 'x')
        int totalOhneX = rohstoffMap.get('t') - rohstoffMap.get('x');

        // Variable zur Totalen Prozentanzahl
        double totalProzent = (double) Math.round(100.00 * (totalOhneX) / rohstoffMap.get('t'));

        // Fügt die Totalen Erze zur Ausgabe hinzu
        html += "<p>- Totale Erze............... [" + totalOhneX + "] (" + Math.round(100.00 * totalOhneX / rohstoffMap.get('t')) + "%" + ")";
        html += "<h2>Auswertung... [Abgeschlossen]</h2>";

        //Bestimmung und Anzeige der Gesamtbewertung des Quadranten
        int sterne;
        if (totalProzent <= 5) {
            html += "0-5% Der Quadrant verfügt über (nahezu) keine Bodenschätze. \n⭐\n";
            sterne = 1;
        } else if (totalProzent <= 10) {
            html += "6-10% Der Quadrant verfügt über eine geringe Menge an Bodenschätzen. \n⭐⭐\n";
            sterne = 2;
        } else if (totalProzent <= 15) {
            html += "11-15% Der Quadrant verfügt über eine große Menge an Bodenschätzen. \n⭐⭐⭐\n";
            sterne = 3;
        } else if (totalProzent <= 20) {
            html += "16-20% Der Quadrant verfügt über eine sehr große Menge an Bodenschätzen. \n⭐⭐⭐⭐\n";
            sterne = 4;
        } else {
            html += ">20% Der Quadrant verfügt über eine extrem große Menge an Bodenschätzen. \n⭐⭐⭐⭐⭐\n";
            sterne = 5;
        }

        // Methode zum Verbinden der Datenbank und einfügen
        databaseConnection(rohstoffMap, sterne);

        // Zeige das Fenster an
        JOptionPane.showMessageDialog(null, html, "Ziel 2 - Exoplaneten Scan", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    /**
     * Stellt eine Verbindung zur Datenbank her und fügt Daten zur Tabelle Planet und Quadrant hinzu
     *
     * @param rohstoffMap HashMap mit allen Daten. Key: Character Rohstoff, Value: Gefundene Menge des Rohstoffs
     * @param sterne      Anzahl der Bewertungssterne
     */
    static void databaseConnection(HashMap<Character, Integer> rohstoffMap, int sterne) {
        // Prüfen, ob eine Verbindung zur Datenbank besteht, wenn nicht, dann eine herstellen
        if (!MySQL.isConnected()) {
            MySQL.connect();
        }

        // Hole Planetennamens aus dem Dateinamen
        String fullName = Einlesen.dataName.replace("src\\docs\\", "");
        char planetName = fullName.charAt(6);

        // Hole Quadrantbezeichnung aus dem Dateinamen
        String quadrant = fullName.split("-")[1].split("_")[0].replace("Q", "");

        // Einfügen des Planetennamens in die Tabelle "Planet"
        MySQL.update("INSERT INTO Planet SET Name='" + planetName + "';");

        // Einfügen von Daten in die Tabelle "Quadrant"
        MySQL.update("REPLACE INTO Quadrant(Bezeichnung, Gold, Silber, Uran, Kupfer, Zink, Total, Sterne, PName) " +
                "VALUES('" + quadrant + "','" + rohstoffMap.get('g') + "','" + rohstoffMap.get('s') + "','" +
                rohstoffMap.get('u') + "','" + rohstoffMap.get('k') + "','" + rohstoffMap.get('z') + "','" +
                rohstoffMap.get('t') + "','" + sterne + "','" + planetName + "');");

        // Schließen der Datenbankverbindung
        MySQL.close();
    }

    /**
     * Gibt den prozentualen Anteil eines bestimmten Rohstoffs an der Gesamtmenge zurück.
     *
     * @param rohstoffMap HashMap mit allen Daten. Key: Character Rohstoff, Value: Gefundene Menge des Rohstoffs
     * @param erz         Rohstoffs dessen prozentualer Anteil berechnet werden soll
     * @return prozentualer Anteil des angegebenen Rohstoffs von Gesamtmenge als String
     */
    static String getPercentage(HashMap<Character, Integer> rohstoffMap, char erz) {
        return Math.round(100.00 * rohstoffMap.get(erz) / rohstoffMap.get('t')) + "%";
    }
}
