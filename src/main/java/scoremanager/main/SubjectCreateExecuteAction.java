package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		// セッション取得
		HttpSession session = req.getSession();

		// ログインユーザー取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		// DAO
		SubjectDao subDao = new SubjectDao();

		// エラーメッセージ用
		Map<String, String> errors = new HashMap<>();

		// パラメータ取得
		String subcd = req.getParameter("cd");
		String subname = req.getParameter("name");

		// ログインチェック
		if (teacher == null) {

			errors.put("teacher", "ログイン情報がありません");

		}

		// 科目コードチェック
		if (subcd == null || subcd.isEmpty()) {

			errors.put("subcd", "科目コードを入力してください");

		} else if (subcd.length() != 3) {

			errors.put("subcd", "科目コードは3文字で入力してください");

		} else if (subDao.get(subcd, teacher.getSchool()) != null) {

			errors.put("subcd", "科目コードが重複しています");
		}

		// 科目名チェック
		if (subname == null || subname.isEmpty()) {

			errors.put("name", "科目名を入力してください");
		}

		// エラーがある場合
		if (!errors.isEmpty()) {

			req.setAttribute("errors", errors);

			req.getRequestDispatcher("subject_create.jsp")
			   .forward(req, res);

			return;
		}

		// 科目登録
		Subject sub = new Subject();

		sub.setCd(subcd);
		sub.setName(subname);
		sub.setSchool(teacher.getSchool());

		// 保存
		subDao.save(sub);

		// 一覧画面へ戻る
		res.sendRedirect("SubjectList.action");
	}
}