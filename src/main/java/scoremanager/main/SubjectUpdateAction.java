package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        // ===== パラメータ取得 =====
        String cd = req.getParameter("cd");

        // ===== 入力チェック =====
        if (cd == null || cd.isEmpty()) {
            req.setAttribute("errorMsg", "科目コードが指定されていません。");
            req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
               .forward(req, res);
            return;
        }

        // ===== DB取得 =====
        SubjectDao dao = new SubjectDao();
        Subject subject = dao.findByCd(cd);

        // ===== データなし =====
        if (subject == null) {
            req.setAttribute("errorMsg", "科目が存在していません。");
            req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
               .forward(req, res);
            return;
        }

        // ===== 画面表示用 =====
        req.setAttribute("cd", subject.getCd());
        req.setAttribute("name", subject.getName());

        // ===== JSPへ =====
        req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
           .forward(req, res);
    }
}