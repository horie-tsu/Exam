package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session = req.getSession();

        SubjectDao subDao = new SubjectDao();
        Map<String, String> errors = new HashMap<>();
        
        
        // パラメータ取得
        String subcd = req.getParameter("cd");
        String subname = req.getParameter("name");
        
     // 科目コードチェック
        if (subDao.get(subcd) != null) {
            errors.put("subcd", "学生番号が重複しています");
        }
     // エラーがある場合
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("SubjectCreate.action").forward(req, res);
            return;
        }
     // 登録処理
        Subject sub = new Subject();
        sub.setCd(subcd);
        sub.setName(subname);

        subDao.save(sub);

        // 完了後
        res.sendRedirect("subject_create_done.jsp");

	}

}
