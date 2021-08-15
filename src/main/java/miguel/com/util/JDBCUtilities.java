package miguel.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtilities {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:" + "ProyectosConstruccion.db";
        return DriverManager.getConnection(url);
        }
}
