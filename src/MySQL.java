import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQL {
    // Globale Variablen für die Verbindung zur Datenbank
    public static String host = "78.46.251.172";
    public static String database = "AEOE";
    public static String username = "sudoman";
    public static String password = "DdFecKnJQPRsZzGC";
    public static Connection con;

    /**
     * Methode, die alle Namen/Bezeichnungen der Quadranten für einen bestimmten Planeten aus der Datenbank abruft.
     *
     * @param planetName Name des Planeten, für den die Quadranten-bezeichnungen abgerufen werden sollen.
     * @return String-Array mit Namens aller Quadranten in Planet oder ein leeres Array, wenn ein Fehler aufgetreten ist.
     */
    public static String[] getAllQuadrantsBezeichnung(String planetName) {
        // SQL-Statement, um alle Datensätze aus Tabelle Quadrant zu erhalten, die dem übergebenen Planetennamen entsprechen
        String sql = "SELECT * FROM Quadrant WHERE PName = ?";

        // Try-Catch für SQL-Exception und Rückgabe eines leeren String-Arrays
        try {
            // Prepared Statement erstellen und Werte für Platzhalter setzen
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, planetName);

            // SQL-Abfrage ausführen und Ergebnisse in ResultSet speichern
            ResultSet rs = pstmt.executeQuery();

            // Erstelle eine Liste für die Bezeichnungen der Quadranten
            List<String> bezeichnungList = new ArrayList<>();

            // Alle Ergebniszeilen durchlaufen und Bezeichnungen auslesen
            while (rs.next()) {
                // Füge die Bezeichnung des Quadranten zur Liste hinzu
                bezeichnungList.add(rs.getString("Bezeichnung"));
            }

            // Konvertiere die Liste in ein String-Array und gib es zurück
            return bezeichnungList.toArray(new String[0]);

        } catch (SQLException e) {
            e.printStackTrace();
            return new String[]{};
        }
    }

    /**
     *  Liest alle Planetennamen aus der Datenbank aus und gibt sie als String-Array zurück.
     *  @return String-Array mit allen Planetennamen oder ein leeres Array, wenn ein Fehler aufgetreten ist.
     */
    public static String[] getAllPlanets() {
        // SQL-Abfrage zum Abrufen aller Planetennamen
        String sql = "SELECT Name FROM Planet";

        // Try-Catch für SQL-Exception und Rückgabe eines leeren String-Arrays
        try {
            // PreparedStatement erstellen
            PreparedStatement pstmt = con.prepareStatement(sql);

            // SQL-Abfrage ausführen und Ergebnisse in ResultSet speichern
            ResultSet rs = pstmt.executeQuery();

            // Liste für die Planetennamen initialisieren
            List<String> bezeichnungList = new ArrayList<>();

            // Alle Ergebniszeilen durchlaufen und Planetennamen auslesen
            while (rs.next()) {
                bezeichnungList.add(rs.getString("Name"));
            }

            // Liste in ein String-Array umwandeln und zurückgeben
            return bezeichnungList.toArray(new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[]{};
        }
    }

    /**
     * Holt alle Daten aus einem bestimmten Quadranten für einen bestimmten Planeten aus der Datenbank.
     *
     * @param planetName der Name des Planeten, für den die Daten abgerufen werden sollen.
     * @param quadrant die Bezeichnung des Quadranten, für den die Daten abgerufen werden sollen.
     * @return String-Array mit allen Daten eines Quadranten oder ein leeres Array, wenn ein Fehler aufgetreten ist.
     */
    public static String[] getAllDataFromQuadrants(String planetName, String quadrant) {
        // SQL-Query erstellen, um alle Daten für den Planeten und den Quadranten abzurufen
        String sql = "SELECT * FROM Quadrant WHERE PName = ? AND Bezeichnung = ?";

        // Try-Catch für SQL-Exception und Rückgabe eines leeren String-Arrays
        try {
            // Prepared Statement erstellen und Werte für Platzhalter setzen
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, planetName);
            pstmt.setString(2, quadrant);

            // Query ausführen und ResultSet abrufen
            ResultSet rs = pstmt.executeQuery();
            List<String> dataList = new ArrayList<>();

            // ResultSet durchlaufen und alle Daten in die dataList einfügen
            while (rs.next()) {
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    dataList.add(rs.getString(i));
                }
            }

            // dataList in Array umwandeln und zurückgeben
            return dataList.toArray(new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[]{};
        }
    }

    /**
     * Stellt Verbindung zur Datenbank her, wenn keine aktive Verbindung besteht.
     */
    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
                System.out.println("Erfolg!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error!");
            }
        }
    }

    /**
     * Schließt eine aktive Verbindung zur Datenbank.
     */
    public static void close() {
        if (isConnected()) {
            try {
                con.close();
                con = null;
                System.out.println("Erfolgreich geschlossen.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error!");
            }
        }
    }

    /**
     * Prüft, ob eine aktive Verbindung zur Datenbank besteht.
     *
     * @return true, wenn eine Verbindung besteht, sonst false.
     */
    public static boolean isConnected() {
        return con != null;
    }

    /**
     * Führt ein SQL-Statement aus, wenn eine Verbindung besteht, um Daten in der MySQL-Datenbank zu aktualisieren.
     *
     * @param qry MySQL Statement
     */
    public static void update(String qry) {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate(qry);
            } catch (SQLException ignored) {
            }
        }
    }
}
