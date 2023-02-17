//Importanweisung

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


/**
 * Beschreiben Sie hier die Klasse IOexperiment.
 *
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class AusgabeFenster {
    /*private*/
    static void Z1(HashMap<Character, Integer> rohstoffMap) {
        //Farbschema/Panel
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("Panel.background", Color.white);
        UIManager.put("OptionPane.background", new Color(31, 99, 151));
        UIManager.put("Panel.background", new Color(19, 60, 91));
        //Anzeige Bild
        final ImageIcon icon = new ImageIcon("src\\img\\erzprobescan.gif");


        // System.out.println("Sum of values in map: " + map.values().stream().mapToDouble(Float::doubleValue).sum());

        //Ausgabetext fürs Panel
        String html = "<html><body width='%1s'><h1>EXO...projekt...</h1>"
                + "<h2>Scan... [abgeschlossen]</h2>"
                + "<h2>Auswertung des Planeten...</h2>"
                + "<p>- Quadranten-Größe... [ " + (rohstoffMap.get('t')) + " Felder ]";


        if (rohstoffMap.get('g') != null) {
            html = html + "<p>- Gold........................... [ " + rohstoffMap.get('g') + " ] ( " + Math.round(100.0 * rohstoffMap.get('g') / rohstoffMap.get('t')) + "%)";
        } else {
            html = html + "<p>- Gold........................... [keine Daten]";
        }
        if (rohstoffMap.get('k') != null) {
            html += "<p>- Kupfer....................... [ " + rohstoffMap.get('k') + " ] ( " + Math.round(100.00 * rohstoffMap.get('k') / rohstoffMap.get('t')) + "%)";
        } else {
            html += "<p>- Kupfer....................... [keine Daten]";
        }
        if (rohstoffMap.get('s') != null) {
            html += "<p>- Silber......................... [ " + rohstoffMap.get('s') + " ] ( " + Math.round(100.00 * rohstoffMap.get('s') / rohstoffMap.get('t')) + "%)";
        } else {
            html = html + "<p>- Silber......................... [keine Daten]";
        }
        if (rohstoffMap.get('u') != null) {
            html += "<p>- Uran........................... [ " + rohstoffMap.get('u') + " ] ( " + Math.round(100.00 * rohstoffMap.get('u') / rohstoffMap.get('t')) + "%)";
        } else {
            html += "<p>- Uran........................... [keine Daten]";
        }
        if (rohstoffMap.get('z') != null) {
            html += "<p>- Zink............................ [ " + rohstoffMap.get('z') + " ] ( " + Math.round(100.00 * rohstoffMap.get('z') / rohstoffMap.get('t')) + "%)";
        } else {
            html += "<p>- Zink............................ [keine Daten]";
        }

        int totalProzent = rohstoffMap.get('t')-rohstoffMap.get('x') / rohstoffMap.get('t');

        switch(true) {
            case totalProzent > 5%:
                // code block
                break;
            case y:
                // code block
                break;
            default:
                // code block
        }

        html += "<h2>Auswertung... [Abgeschlossen]</h2>";

        //Weite
        int w = 500;


        JOptionPane.showMessageDialog(null, html, "Ziel 2 - Exoplaneten Scan", JOptionPane.INFORMATION_MESSAGE, icon);
    }
}