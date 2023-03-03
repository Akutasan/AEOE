/**
 * Klasse zur Demonstration der DB-Anbindung in Java
 *
 * @author Sven Dräger
 * @version 05.01.2023
 */
//Importieren einer Klasse mit Methoden zur SQL-Bearbeitung

import org.intellij.lang.annotations.Language;

import java.sql.*;

public class MySQL {

    public static String host = "78.46.251.172"; //127.0.0.1
    public static String database = "AEOE"; //z. B. "testDB"
    public static String username = "sudoman"; //Standardwert
    public static String password = "DdFecKnJQPRsZzGC"; //Standardwert
    public static Connection con;

    /**
     * Hilfs-Operation zum Herstellen einer DB-Verbindung. Dazu muss die komprimierte Datei mysql-connector-java-5.1.45-bin.jar
     * im gleichen Ordner wie die Projektdateien entpackt werden.
     * Die Operation gibt ein Objekt vom Typ Connection zurück und wird in der nächsten Operation verwendet
     * NUR NACH RÜCKSPRACHE VERÄNDERN
     *
     * @return Verbindung zur Datenbank
     */

    public static String[] getAllQuadrantsBezeichnung(String planetName) {
        ResultSet rs = getResult("SELECT Bezeichnung FROM Quadrant WHERE PName ='" + planetName + "';");
        try {
            if (rs != null && rs.next()) {
                int columnCount = rs.getMetaData().getColumnCount();
                String[] values = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    values[i - 1] = rs.getString(i);
                }
                return values;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[]{};
        }

        return new String[]{};
    }

    public static String[] getAllDataFromQuadrants(String planetName, String Quadrant) {
        ResultSet rs = getResult("SELECT * FROM Quadrant WHERE PName ='" + planetName + "' AND Bezeichnung = '" + Quadrant + "';");
        try {
            assert rs != null;
            if (rs.next()) {
                int columnCount = rs.getMetaData().getColumnCount();
                String[] values = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    values[i - 1] = rs.getString(i);
                }
                return values;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[]{};
        }
        return new String[]{};
    }



    public static Object getObject(String whereresult, String where, String select, String database) {
        ResultSet rs = getResult("SELECT " + select + " FROM " + database + " WHERE " + where + "='" + whereresult + "';");
        try {
            assert rs != null;
            if (rs.next()) {
                return rs.getObject(select);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR";
        }

        return "ERROR";
    }

    public static void main(String[] args) {
        System.out.println(doesPlanetExist('B'));
    }

    public static boolean doesPlanetExist(char planetName) {
        ResultSet rs = getResult("SELECT Bezeichnung FROM Quadrant WHERE PName = '"+planetName+"';");
        try {
            if (rs != null) {
                if (rs.next()){
                    return true;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
                System.out.println("Success!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error!");
            }
        }
    }

    public static void close() {
        if (isConnected()) {
            try {
                con.close();
                con = null;
                System.out.println("Successfully closed MySQL-Connection.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error while closing MySQL-Database!");
            }
        }
    }

    public static boolean isConnected() {
        return con != null;
    }

    public static void update(@Language("SQL") String qry) {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static ResultSet getResult(@Language("SQL") String qry) {
        if (isConnected()) {
            try {
                return con.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
