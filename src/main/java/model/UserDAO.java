package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

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

    // ユーザー名が既に存在するか確認
    public boolean existsByName(String name) {

        String sql = "SELECT COUNT(*) FROM USERS WHERE NAME = ?";

        try (
            Connection conn =
                    DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ユーザー登録
    public boolean create(User user) {

        String sql = "INSERT INTO USERS(NAME, PASS) VALUES(?, ?)";

        try (
                Connection conn =
                        DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
                PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getPass());
                return pstmt.executeUpdate() == 1;
            } catch (SQLException e) {
            	// UNIQUE制約違反など
                e.printStackTrace();
                return false;
            }
        }

    // ログイン用（ユーザー検索）
    public User findByLogin(String name, String pass) {
        
        System.out.println("ログインSQL実行");

        String sql
            = "SELECT ID, NAME, PASS FROM USERS WHERE NAME = ? AND PASS = ?";
        try (
            Connection conn =
                    DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, name);
            pstmt.setString(2, pass);

            System.out.println("SQL NAME=" + name);
            System.out.println("SQL PASS=" + pass);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("ログイン成功");
                return new User(
                    rs.getInt("ID"),
                    rs.getString("NAME"),
                    rs.getString("PASS")
                );

            }
            System.out.println("ログイン失敗");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
