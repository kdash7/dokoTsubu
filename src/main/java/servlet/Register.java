package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.RegisterUserLogic;
import model.User;

@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // ユーザー登録画面表示
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("★★ Register.doGet 到達 ★★");

        // 登録画面へフォワード
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/jsp/registerView.jsp");
        dispatcher.forward(request, response);
    }

    // ユーザー登録処理
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 入力値取得
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        // 未入力チェック
        String errorMsg = null;

        if (name == null || name.isBlank()) {
            errorMsg = "ユーザー名を入力してください";
        } else if (pass == null || pass.isBlank()) {
            errorMsg = "パスワードを入力してください";
        }

        // エラーがあれば登録画面へ戻す
        if (errorMsg != null) {
            request.setAttribute("errorMsg", errorMsg);
            request.setAttribute("name", name); // 入力保持（任意）
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("WEB-INF/jsp/registerView.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // User 作成
        User user = new User(name, pass);

        // 登録処理
        RegisterUserLogic logic = new RegisterUserLogic();
        boolean result = logic.execute(user);

        // 結果をリクエストスコープへ
        request.setAttribute("result", result);
        request.setAttribute("user", user);

        // 結果画面へ
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/jsp/registerResult.jsp");
        dispatcher.forward(request, response);
    }
}
