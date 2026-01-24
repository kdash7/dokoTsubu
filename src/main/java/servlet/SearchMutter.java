package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Mutter;
import model.SearchMutterLogic;

@WebServlet("/SearchMutter")
public class SearchMutter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("★★ SearchMutterServlet.doGet 到達 ★★");

        // 検索キーワード取得
        String keyword = request.getParameter("keyword");

        // 画面表示用にキーワードを保存
        request.setAttribute("keyword", keyword);

        // 空文字 or null 対策
        if (keyword == null || keyword.isBlank()) {
            request.setAttribute("errorMsg", "検索キーワードを入力してください。");

            // メイン画面へ戻す
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // 検索実行
        SearchMutterLogic logic = new SearchMutterLogic();
        List<Mutter> mutterList = logic.execute(keyword);

        // 結果0件の場合のメッセージ
        if (mutterList.isEmpty()) {
            request.setAttribute(
                "errorMsg",
                "「" + keyword + "」に一致するつぶやきはありません。"
            );
        }

        // 検索結果をリクエストスコープへ
        request.setAttribute("mutterList", mutterList);

        // メイン画面へフォワード
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
        dispatcher.forward(request, response);
    }
}