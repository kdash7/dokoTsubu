package listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DBInitListener implements ServletContextListener {

    private static final String JDBC_URL =
            "jdbc:h2:tcp://localhost/~/dokoTsubu";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("★★ DBInitListener 起動 ★★");

        try {
            Class.forName("org.h2.Driver");

            try (Connection conn =
                     DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
                 Statement stmt = conn.createStatement()) {

                // USERS
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS USERS (
                        ID INT AUTO_INCREMENT PRIMARY KEY,
                        NAME VARCHAR(100) UNIQUE NOT NULL,
                        PASS VARCHAR(100) NOT NULL
                    )
                """);

                // MUTTERS
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS MUTTERS (
                        ID IDENTITY PRIMARY KEY,
                        USER_ID INT NOT NULL,
                        TEXT VARCHAR(255) NOT NULL
                    )
                """);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
