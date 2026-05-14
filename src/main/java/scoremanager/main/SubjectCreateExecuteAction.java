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
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");

		// ログインチェック
		if (teacher == null) {

			errors.put("teacher", "ログイン情報がありません");

		}

		// 科目コードチェック
		if (cd == null || cd.isEmpty()) {

			errors.put("cd", "科目コードを入力してください");

		} else if (cd.length() != 3) {

			errors.put("cd", "科目コードは3文字で入力してください");

		} else if (subDao.get(cd, teacher.getSchool()) != null) {

			errors.put("cd", "科目コードが重複しています");
		}

		// 科目名チェック
		if (name == null || name.isEmpty()) {

			errors.put("name", "科目名を入力してください");
		}

		// エラーがある場合
		if (!errors.isEmpty()) {

			req.setAttribute("errors", errors);
			
			//入力値を保持
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);

			req.getRequestDispatcher("subject_create.jsp")
			   .forward(req, res);

			return;
		}

		// 科目登録
		Subject sub = new Subject();

		sub.setCd(cd);
		sub.setName(name);
		sub.setSchool(teacher.getSchool());

		// 保存
		subDao.save(sub);

		// 完了画面へ
		res.sendRedirect("subject_create_done.jsp");
	}
}