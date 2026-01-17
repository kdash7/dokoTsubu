package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        // 未入力チェック
        String errorMsg = null;

        if (name == null || name.isBlank()) {
            errorMsg = "ユーザー名を入力してください";
        } else if (pass == null || pass.isBlank()) {
            errorMsg = "パスワードを入力してください";
        }

        // エラーがあればトップ画面へ戻す
        if (errorMsg != null) {
            request.setAttribute("errorMsg", errorMsg);
            request.setAttribute("name", name); // 入力保持
            RequestDispatcher dispatcher =
                request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // ログイン処理
        LoginLogic loginLogic = new LoginLogic();
        User loginUser = loginLogic.execute(name, pass);

        // ログイン成功時の処理
        if (loginUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);
            response.sendRedirect("Main");
        } else {
            request.setAttribute("errorMsg", "ユーザー名またはパスワードが違います");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}