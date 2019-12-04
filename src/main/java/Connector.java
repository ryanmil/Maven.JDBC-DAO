import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private final String URL;
    private final String USER;
    private final String PASS;

    public Connector(String URL, String USER, String PASS) {
        this.URL = URL;
        this.USER = USER;
        this.PASS = PASS;
    }

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

}
