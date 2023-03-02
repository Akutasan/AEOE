/**
 * Klasse zur Demonstration der DB-Anbindung in Java
 *
 * @author Sven Dräger
 * @version 05.01.2023
 */
//Importieren einer Klasse mit Methoden zur SQL-Bearbeitung

import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static Object getObject(String whereresult, String where, String select, String database) {
        ResultSet rs = getResult("SELECT " + select + " FROM " + database + " WHERE " + where + "='" + whereresult + "'");
        try {
            if (rs.next()) {
                return rs.getObject(select);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR";
        }

        return "ERROR";
    }

    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true",
                        username, password);
                System.out.println("Successfully connected to MySQL-Database.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error while connecting to MySQL-Database!");
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
