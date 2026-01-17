package model;

public class LoginLogic {
    public User execute(String name, String pass) { // USERSテーブルへ照会
        UserDAO dao = new UserDAO();
        System.out.println("name=" + name);
        System.out.println("pass=" + pass);
        return dao.findByLogin(name, pass); // 見つかれば User、なければ null
    }
}
