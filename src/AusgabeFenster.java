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
     * Methode zur Ausgabe. Erstellt ein Fenster und gibt alle Daten aus.
     *
     * @param rohstoffMap HashMap mit allen Daten. Key: Character Rohstoff, Value: Anzahl des Rohstoff's
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


        if (rohstoffMap.get('g') != null) {
            html = html + "<p>- Gold........................... [" + rohstoffMap.get('g') + "] (" + Math.round(100.0 * rohstoffMap.get('g') / rohstoffMap.get('t')) + "%)";
        } else {
            html = html + "<p>- Gold........................... [keine Daten]";
        }
        if (rohstoffMap.get('k') != null) {
            html += "<p>- Kupfer....................... [" + rohstoffMap.get('k') + "] (" + Math.round(100.00 * rohstoffMap.get('k') / rohstoffMap.get('t')) + "%)";
        } else {
            html += "<p>- Kupfer....................... [keine Daten]";
        }
        if (rohstoffMap.get('s') != null) {
            html += "<p>- Silber......................... [" + rohstoffMap.get('s') + "] (" + Math.round(100.00 * rohstoffMap.get('s') / rohstoffMap.get('t')) + "%)";
        } else {
            html = html + "<p>- Silber......................... [keine Daten]";
        }
        if (rohstoffMap.get('u') != null) {
            html += "<p>- Uran........................... [" + rohstoffMap.get('u') + "] (" + Math.round(100.00 * rohstoffMap.get('u') / rohstoffMap.get('t')) + "%)";
        } else {
            html += "<p>- Uran........................... [keine Daten]";
        }
        if (rohstoffMap.get('z') != null) {
            html += "<p>- Zink............................ [" + rohstoffMap.get('z') + "] (" + Math.round(100.00 * rohstoffMap.get('z') / rohstoffMap.get('t')) + "%)";
        } else {
            html += "<p>- Zink............................ [keine Daten]";
        }

        html += "<h2>Auswertung... [Abgeschlossen]</h2>";

        double totalProzent = (double) Math.round(100.00 * (rohstoffMap.get('t') - rohstoffMap.get('x')) / rohstoffMap.get('t'));

        if (totalProzent <= 5){
            html += "0-5% Der Planet verfügt über (nahezu) keine Bodenschätze. \n⭐\n";
        } else if (totalProzent <= 10){
            html += "6-10% Der Planet verfügt über eine geringe Menge an Bodenschätzen. \n⭐⭐\n";
        } else if (totalProzent <= 15){
            html += "11-15% Der Planet verfügt über eine große Menge an Bodenschätzen. \n⭐⭐⭐\n";
        } else if (totalProzent <= 20){
            html += "16-20% Der Planet verfügt über eine sehr große Menge an Bodenschätzen. \n⭐⭐⭐⭐\n";
        } else {
            html += ">20% Der Planet verfügt über eine extrem große Menge an Bodenschätzen. \n⭐⭐⭐⭐⭐\n";
        }


        JOptionPane.showMessageDialog(null, html, "Ziel 2 - Exoplaneten Scan", JOptionPane.INFORMATION_MESSAGE, icon);
    }
}