/**
 * Klasse zur Demonstration der DB-Anbindung in Java
 *
 * @author Sven Dräger
 * @version 05.01.2023
 */
//Importieren einer Klasse mit Methoden zur SQL-Bearbeitung
import java.sql.*;

public class DBConnection {

    /**
     * Hilfs-Operation zum Herstellen einer DB-Verbindung. Dazu muss die komprimierte Datei mysql-connector-java-5.1.45-bin.jar
     * im gleichen Ordner wie die Projektdateien entpackt werden.
     * Die Operation gibt ein Objekt vom Typ Connection zurück und wird in der nächsten Operation verwendet
     * NUR NACH RÜCKSPRACHE VERÄNDERN
     *
     * @return Verbindung zur Datenbank
     */
    private static Connection openConnection() {
        String url = "78.46.251.172"; //127.0.0.1
        String db = "AEOE"; //z. B. "testDB"
        String user = "sudoman"; //Standardwert
        String psswd = "DdFecKnJQPRsZzGC"; //Standardwert

        try{
            Connection c = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ db, user, psswd);
            IO.show("It works !");
            return c;
        }
        //Fehlerbehandlung
        catch (Exception e){
            IO.show("Fehler beim Verbindungsaufbau");
            e.printStackTrace();
            return null;
        }
    }

    static void getAll()

    /**
     * Operation zum Ausführen von SQL-Anweisungen
     **/
    static String dbVerwenden(String statement){

        //Die Verwendung von Datenbanken kann vielfältige Fehler erzeugen, vgl. Arbeit mit Dateien
        try{
            //Verbindung wird aufgebaut und im folgenden verwendet
            Connection c = openConnection();

            //SQL-Befehl erzeugen und ausführen
            assert c != null;
            Statement st = c.createStatement();

            //SQL-Abfrage ausführen und Ergebnisse als ResultSet speichern
            ResultSet rs = st.executeQuery(statement);

            //Ausgabe jedes Datensatzes der Abfrage
            while(rs.next())
                return rs;

            //DB-Verbindung beenden
            st.close();
            c.close();
        }
        //Fehlerbehandlung
        catch(Exception e){
            IO.show("Fehler: "+ e);
        }
    }
}
