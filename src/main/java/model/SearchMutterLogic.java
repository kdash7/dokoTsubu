package model;

import java.util.List;

import dao.MuttersDAO;

public class SearchMutterLogic {

	// 検索処理専用のビジネスロジック
	public List<Mutter> execute(String keyword) {
        MuttersDAO dao = new MuttersDAO();
        return dao.search(keyword);
    }
}
