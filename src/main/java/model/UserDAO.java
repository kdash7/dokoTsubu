package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
	
    // DB接続情報
    private static final String JDBC_URL = "jdbc:h2:file:./dokotsubu\n";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "";

    // ユーザー名が既に存在するか確認
    public boolean existsByName(String name) {

        String sql = "SELECT COUNT(*) FROM USERS WHERE NAME = ?";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
         } catch (SQLException e) {
             e.printStackTrace();
             return false;
         }
     }

    // ユーザー登録
    public boolean create(User user) {

        String sql = "INSERT INTO USERS(NAME, PASS) VALUES(?, ?)";

        try (
                Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
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

        String sql = "SELECT name, pass FROM users WHERE name = ? AND pass = ?";
        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, name);
            pstmt.setString(2, pass);


            System.out.println("SQL name=" + name);
            System.out.println("SQL pass=" + pass);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("name"), rs.getString("pass"));
            }
            System.out.println("ログイン成功");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
