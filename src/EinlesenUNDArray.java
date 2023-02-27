
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class EinlesenUNDArray {
    /**
     * Methode ReturnEinlesen
     *
     * @param a Name der einzulesende Datei
     * @return die übergebende Datei als langen String mit N als Kennzeichen für Zeilenumbruch
     */
    private static String ReturnEinlesen(String a) {
        try {

            String s = ""; // String zum Zwischenspeichern der Eingelseneden Zeile
            String r = ""; // String zur Ausgabe aller eingelesener Zeilen
            BufferedReader in = new BufferedReader(new FileReader(a)); //Erstellen eines Objektes: Öffnen der Datei


            while (true) {   //loop muss true sein, weil man sonst die Dimensionen der Datein schon wissen müsste
                s = in.readLine(); // Zeile wird eingelesen
                if (s != null) {  // wenn die Zeile Exestier, wird sie an den Ausgabestring angehängt
                    r = s + "N" + r; // N dient als indkator für Zeilen umbrüche
                } else {  //Schleife wird gebrochen, wenn das ende der Datei erreicht ist
                    break;
                }
            }
            // IO.show(s); für das testen noch hier
            in.close(); //schließen der Datei; Position wird auf den Anfang zurück gesetzt
            return r;
        } catch (IOException ioex) {
            IO.show("Lesefehler: " + ioex);
            return "brocken";
        }
    }


    /**
     * Methode ZeilenZähler
     *
     * @param a Name der einzulesende Datei
     * @return Anzahl der Zeilen inerhalb der Taxtdatei, bis zur ersten null-Zeile
     */
    private static int ZeilenZähler(String a) {
        try {
            String s = "";
            int ZeilenZähler = 0; // Variable zum Zählen der vorhanden Zeilen
            BufferedReader in = new BufferedReader(new FileReader(a)); //Erstellen eines Objektes: Öffnen der Datei


            while (true) {  // loop funktioniert wie in ReturnEinlesen, nur dass hier die zeilen anzahl gezählt wird un kein string erstellt wird
                s = in.readLine();
                if (s != null) { //s wird null am Ende der Datei somit bricht der Lopp
                    ZeilenZähler++;
                } else {
                    break;
                }
            }
            // IO.show(s); für das testen noch hier
            in.close(); //schließen der Datei; Position wird auf den Anfang zurück gesetzt
            return ZeilenZähler;
        } catch (IOException ioex) {
            IO.show("ZeileZähl error: " + ioex);
            return 0;
        }

    }


    /**
     * Methode ZeilenLänge
     *
     * @param a Name der einzulesende Datei
     * @return Länge der Zeilen; Es wird davon ausgegangen,cdass diese Kponstant ist
     */
    private static int ZeilenLänge(String a) {
        try {
            String s = "";
            BufferedReader in = new BufferedReader(new FileReader(a)); //öfnet die Textdatei
            s = in.readLine(); // liest die Erstezeile der Textdatei aus
            int Länge = s.length(); // Ermittelt die Länge der ersten Zeil 

            in.close(); //schließt die Datei
            return Länge; // gibt die Länge zurück
        } catch (IOException ioex) {
            IO.show("ZeileZähl error: " + ioex);
            return 0;
        }


    }

    /**
     * Methode Filter
     *
     * @param Planet Ein Parameter
     * @return Der Rückgabewert
     */
    /**
     * Methode Filter
     *
     * @param Planet Ein Parameter
     * @return Der Rückgabewert
     */
    /*
    **
     * Methode Umfeld
     *
     * @param PlanetenArry Die Textdatei in Arry konvertierte Form
     * @param xkoordinate x-Koordinate des Mittelpunktes
     * @param ykoordinate y-Koordinate des Mittelpunktes
     * @param radius Radius des Bereiches,der untersucht werden soll
     * @param Länge x-Länge des Arrys
     * @param ZeilenLänge y-Länge des Arrys
     * @return einen String, der dass Umfeld der Koordinate Abbilden soll (Momentan fehlerhaft!!!)
    *
    private static String Umfeld(char[][] PlanetenArry,int xkoordinate, int ykoordinate, int radius, int Länge, int ZeilenLänge){
        String Umfeld = "";
        int i = 0;
        int k = 0;
        
        while(ykoordinate- radius + i < ykoordinate + radius +1){
            
            while(xkoordinate - radius + k < xkoordinate + radius +1){
                if(ykoordinate-radius + i <= Länge && ykoordinate-radius + i >= 0 && xkoordinate-radius + k <= ZeilenLänge && xkoordinate-radius + k >= 0){ //diese If verhindert outofbounds errors
                    Umfeld = Umfeld + PlanetenArry[ykoordinate-radius + i][xkoordinate - radius + k]; 
                }
                k++;
            }
            
            Umfeld = Umfeld + "\n"; //fügt zeilen Umbruch ein
            k = 0; // k muss auf null gesetz werde, um die xRichtung neu duchlaufen zu können 
            i++;
        }
        
        return Umfeld;
    }
    **/

    /**
     * Methode Filter
     *
     * @param Planet String aus der Public Methode
     * @return String ohne die 'N'
     */
    private static String Filter(String Planet) {
        String Filter = "";
        int i = 0;
        while (i < Planet.length()) { //loop geht durch die Länge des Strings
            if (Planet.charAt(i) != 'N') { //fügt das i-te Element an den Ausgabestring hinzu, wenn es nicht n ist
                Filter = Filter + Planet.charAt(i);
            }
            i++;
        }
        //IO.show(Filter);
        return Filter;
    }

    private static String Bewertung(String Filter) {
        double Gesamt = Filter.length();
        //IO.show(""+ Gesamt);
        int i = 0; //Zählvarible
        int g = 0; //Varible für Anzahl der Resoursen; Buchstabe ist gleich der in der Datei
        int k = 0;
        int s = 0;
        int u = 0;
        int z = 0;
        while (i < Filter.length()) { //loop geht den übergebenden Sting durch
            if (Filter.charAt(i) == 'g') { //Erhöhen des Zähler für jedes gefunden Element
                g++;
            } else if (Filter.charAt(i) == 'k') {
                k++;
            } else if (Filter.charAt(i) == 's') {
                s++;
            } else if (Filter.charAt(i) == 'u') {
                u++;
            } else if (Filter.charAt(i) == 'z') {
                z++;
            }
            i++;
        }
        double GoldProzent = (g / Gesamt) * 100; //Umrechnung der abseluten Anzahl der Ressourcen in den Relativenwert
        double KupferProzent = (k / Gesamt) * 100;
        double SilberProzent = (s / Gesamt) * 100;
        double UranProzent = (u / Gesamt) * 100;
        double ZinkProzent = (z / Gesamt) * 100;
        double Ressourcensättigung = (g + k + s + u + z) / Gesamt * 100;
        String Analyseausgabe = "Der Planet enthält " + GoldProzent + "% Gold " + "\n" + "Der Planet enthält " + KupferProzent + "% Kupfer " + "\n" + "Der Planet enthält " + SilberProzent + "% Silber " + "\n" + "Der Planet enthält " + UranProzent + "% Uran " + "\n" + "Der Planet enthält " + ZinkProzent + "% Zink " + "\n" + "Die Ressourcensättigung des Planetens beträgt " + Ressourcensättigung + "% "; //Zusammensetzen des Strings
        return Analyseausgabe;
    }

    public static void main(String[] args){
        ArrayErzeuger("src\\docs\\PlanetB-Q5_50x100.txt");
        System.out.println(Arrays.deepToString(ArrayErzeuger("src\\docs\\PlanetB-Q5_50x100.txt")));
    }


    /**
     * Methode ArrayErzeuger
     *
     * @param a           Name der einzulesendendatei
     * @param ykoordinate Teil der Koordinate des Angefragten Punktes
     * @param xkoordinate Teil der Koordinate des Angefragten Punktes
     * @return Der Rückgabewert
     */
    public static char[][] ArrayErzeuger(String a) {
        String stringeingabe = ReturnEinlesen(a); //holt sich den String aus der Datei in beötigten Format
        int Zeile = ZeilenZähler(a); //holt sich die Anzahl der Zeilen der Übergebenden Textdatei
        int Länge = ZeilenLänge(a); // Holt sich die Zeilenlänge der Textdatei
        int i = 0; //Zählvariable
        int k = 0; //Zählvariable
        int j = 0; //Zählvariable
        char PlanetenArry[][] = new char[Zeile][Länge];

        while (i <= Zeile - 1) { //loop der alle Zeilen des Arrys durchgeht
            k = 0; // k muss genullt werde, da sonst die x-Achse nicht nochmal durchlaufen wird
            while (k <= Länge - 1) { //loop der alleSpalten des Arrys durchgeht
                if (j < stringeingabe.length()) { //dies if verhindert out of bounds erros

                    if (stringeingabe.charAt(j) != 'N') { // N dient als Indkator des Zeilenumbruchs somit wird es nicht hinzugefügt
                        PlanetenArry[i][k] = stringeingabe.charAt(j); //fügt ein Zeichen in das Arry ein
                        j++;
                        k++;
                    } else { // diese else wirkt zwar unnötig anders hat es aber nichtfunktioniert
                        j++;
                    }

                }

            }
            i++;
        }

        // Ausgabe ist wichtig
        IO.show(Bewertung(Filter(stringeingabe)));
        return PlanetenArry;

    }

}
