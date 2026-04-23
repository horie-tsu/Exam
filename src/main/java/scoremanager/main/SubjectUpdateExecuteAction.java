package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        req.setCharacterEncoding("UTF-8");

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        SubjectDao dao = new SubjectDao();

        // ② 更新前に存在チェック（別画面で削除された場合）
        Subject before = dao.findByCd(cd);
        if (before == null) {
            req.setAttribute("errorMsg", "科目が存在していません。");
            req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
               .forward(req, res);
            return;
        }

        // ① 科目名未入力チェック
        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("errorMsg", "科目名を入力してください。");
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);

            req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
               .forward(req, res);
            return;
        }

        if (name.length() > 20) {
            req.setAttribute("errorMsg", "科目名は20文字以内で入力してください。");
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);

            req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
               .forward(req, res);
            return;
        }

        // 更新処理
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);

        dao.update(subject);

        req.getRequestDispatcher("/scoremanager/main/subject_update_done.jsp")
           .forward(req, res);
    }
}