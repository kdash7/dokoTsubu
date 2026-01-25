package listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DBInitListener implements ServletContextListener {

    // データベース接続に使用する情報(ローカル,Render両用)
    private static final String JDBC_URL =
        System.getenv("DB_URL") != null
            ? System.getenv("DB_URL")
            : "jdbc:h2:mem:dokotsubu;DB_CLOSE_DELAY=-1";

    private static final String DB_USER =
        System.getenv("DB_USER") != null
            ? System.getenv("DB_USER")
            : "sa";

    private static final String DB_PASS =
        System.getenv("DB_PASS") != null
            ? System.getenv("DB_PASS")
            : "";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("★★ DBInitListener 起動 ★★");

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
                    ID INT AUTO_INCREMENT PRIMARY KEY,
                    USER_ID INT NOT NULL,
                    TEXT VARCHAR(255) NOT NULL,
                    FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
                )
            """);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
