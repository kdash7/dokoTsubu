package model;

public class RegisterUserLogic {

    public boolean execute(User user) {

        System.out.println("★★ RegisterUserLogic.execute 開始 ★★");
        System.out.println("name=" + user.getName());

        UserDAO dao = new UserDAO();

        boolean exists = dao.existsByName(user.getName());
        System.out.println("existsByName=" + exists);

        if (exists) {
            System.out.println("★★ 既存ユーザーのため登録中止 ★★");
            return false;
        }

        boolean result = dao.create(user);
        System.out.println("create結果=" + result);

        return result;
    }
}