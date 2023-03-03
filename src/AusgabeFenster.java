//Importanweisung

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


/**
 * Beschreiben Sie hier die Klasse IOexperiment.
 *
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class AusgabeFenster {
    /**
     * Methode zur Ausgabe. Konfiguriert ein Fenster und gibt alle Daten aus.
     *
     * @param rohstoffMap HashMap mit allen Daten. Key: Character Rohstoff, Value: Gefundene Menge des Rohstoffs
     */
    static void Z1(HashMap<Character, Integer> rohstoffMap) {
        //Farbschema/Panel
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("Panel.background", Color.white);
        UIManager.put("OptionPane.background", new Color(31, 99, 151));
        UIManager.put("Panel.background", new Color(19, 60, 91));
        //Anzeige Bild
        final ImageIcon icon = new ImageIcon("src\\img\\erzprobescan.gif");

        //Ausgabetext fürs Panel
        String html = "<html><body width='%1s'><h1>EXO...projekt...</h1>"
                + "<h2>Scan... [abgeschlossen]</h2>"
                + "<h2>Auswertung des Planeten...</h2>"
                + "<p>- Quadranten-Größe... [" + (rohstoffMap.get('t')) + " Felder]";

        //Anzeige der gefundenen Mengen
        //Berechnug des jeweiligen Gesamtansteils durch Operation getPercentage
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

        html += "<h2>Auswertung... [Abgeschlossen]</h2>";

        //Berechnung des Anteils von Bodenschätzen am Quadranten
        double totalProzent = (double) Math.round(100.00 * (rohstoffMap.get('t') - rohstoffMap.get('x')) / rohstoffMap.get('t'));
        int sterne;

        //Bestimmung und Anzeige der Gesamtbewertung des Quadranten
        if (totalProzent <= 5) {
            html += "0-5% Der Planet verfügt über (nahezu) keine Bodenschätze. \n⭐\n";
            sterne = 1;
        } else if (totalProzent <= 10) {
            html += "6-10% Der Planet verfügt über eine geringe Menge an Bodenschätzen. \n⭐⭐\n";
            sterne = 2;
        } else if (totalProzent <= 15) {
            html += "11-15% Der Planet verfügt über eine große Menge an Bodenschätzen. \n⭐⭐⭐\n";
            sterne = 3;
        } else if (totalProzent <= 20) {
            html += "16-20% Der Planet verfügt über eine sehr große Menge an Bodenschätzen. \n⭐⭐⭐⭐\n";
            sterne = 4;
        } else {
            html += ">20% Der Planet verfügt über eine extrem große Menge an Bodenschätzen. \n⭐⭐⭐⭐⭐\n";
            sterne = 5;
        }

        databaseConnection(rohstoffMap, sterne);

        JOptionPane.showMessageDialog(null, html, "Ziel 2 - Exoplaneten Scan", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    //Verbindung zur Datenbank wird hergestellt
    static void databaseConnection(HashMap<Character, Integer> rohstoffMap, int sterne) {
        if (!MySQL.isConnected()) {
            MySQL.connect();
        }
        setRohstoffinDB(rohstoffMap, sterne);
        MySQL.close();
    }

    //Erfassung der Namen und Rostoffwerte, einzelnes Einfügen dieser in Datenbanken
    static void setRohstoffinDB(HashMap<Character, Integer> rohstoffMap, int sterne){
        String fullName = Einlesen.dataName.replace("src\\docs\\", "");
        char planetName = fullName.charAt(6);
        String quadrant = fullName.split("-")[1].split("_")[0].replace("Q","");

        if (!MySQL.doesPlanetExist(planetName)) {
            MySQL.update("INSERT INTO Planet(Name) VALUES('" + planetName + "');");
        }

        MySQL.update("INSERT INTO Quadrant(Bezeichnung, Gold, Silber, Uran, Kupfer, Zink, Total, Sterne, PName) " +
                "VALUES('"+ quadrant + "','" + rohstoffMap.get('g') + "','" + rohstoffMap.get('s') + "','" +
                rohstoffMap.get('u') + "','" + rohstoffMap.get('k') + "','" + rohstoffMap.get('z') + "','" +
                rohstoffMap.get('t') + "','" + sterne + "','" + planetName + "');");
    }

    //Hilfsoperation zur Berechnung des Anteils eines Rohstoffs am Gesamtquadranten
    static String getPercentage(HashMap<Character, Integer> rohstoffMap, char erz) {
        return Math.round(100.00 * rohstoffMap.get(erz) / rohstoffMap.get('t')) + "%";
    }
}