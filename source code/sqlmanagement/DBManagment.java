package sqlmanagement;

import java.sql.*;

public class DBManagment {
    private static final String url = "jdbc:postgresql://ella.db.elephantsql.com:5432/euoxnnag";
    private static final String user = "euoxnnag";
    private static final String pass = "uRvGdtwsEC_jX_hBqHWZGhkZ3bDT3W6Q";

    public DBManagment() {}

    /**
     * Metoda, która nawiązuje połączenia z bazą danych
     * @see SQLException
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Polaczenie z baza danych OK ! ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    /**
     * Metoda, która zamyka połączenia z bazą danych
     * @see SQLException
     */
    public static void closeAll(Connection con, ResultSet res, PreparedStatement pst) {
        if (res != null) {
            try {
                res.close();
            } catch (SQLException e) {}
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {}
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {}
        }
    }
}
