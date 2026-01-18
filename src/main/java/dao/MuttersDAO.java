package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MuttersDAO {
    // データベース接続に使用する情報
    private static final String JDBC_URL = "jdbc:h2:mem:dokoTsubu;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "";

    static {
        try {
            Class.forName("org.h2.Driver");

            Connection conn =
                DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
            Statement stmt = conn.createStatement();

            // テーブルがなければ作成
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS mutters (
                    id IDENTITY PRIMARY KEY,
                    name VARCHAR(100),
                    text VARCHAR(255)
                )
            """);

            stmt.close();
            conn.close();

            System.out.println("★★ MUTTERS テーブル作成確認 ★★");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // つぶやき一覧取得
    public List<Mutter> findAll() {
    	
    	System.out.println("★★ MuttersDAO.findAll 呼ばれた ★★");
    	
        List<Mutter> mutterList = new ArrayList<>();
    
        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // SELECT文の準備
            String sql = "SELECT ID,NAME,TEXT FROM MUTTERS ORDER BY ID DESC";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            // SELECT文を実行
            ResultSet rs = pStmt.executeQuery();

            // SELECT文の結果をArrayListに格納
            while (rs.next()) {
                int id = rs.getInt("ID");
                String userName = rs.getString("NAME");
                String text = rs.getString("TEXT");
                Mutter mutter = new Mutter(id, userName, text);
                mutterList.add(mutter);
            }
      
            System.out.println("★★ つぶやき取得件数: " + mutterList.size());
      
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return mutterList;
    }
  
    // つぶやき投稿
    public boolean create(Mutter mutter) {

    	System.out.println("★★ MuttersDAO.create 呼ばれた ★★");
    	
        // データベース接続
        try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // INSERT文の準備(idは自動連番なので指定しなくてよい）
            String sql = "INSERT INTO MUTTERS(NAME, TEXT) VALUES(?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
      
            // INSERT文中の「?」に使用する値を設定しSQLを完成
            pStmt.setString(1, mutter.getUserName());
            pStmt.setString(2, mutter.getText());

            // INSERT文を実行（resultには追加された行数が代入される）
            int result = pStmt.executeUpdate();
            System.out.println("★★ つぶやきINSERT結果: " + result);
            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}