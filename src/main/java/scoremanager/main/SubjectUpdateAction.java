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

        String cd = req.getParameter("cd");

        SubjectDao dao = new SubjectDao();
        Subject subject = dao.findByCd(cd);

        // ★ すでに削除されている場合
        if (subject == null) {
            req.setAttribute("errorMsg", "科目が存在していません。");
            req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
               .forward(req, res);
            return;
        }

        req.setAttribute("cd", subject.getCd());
        req.setAttribute("name", subject.getName());

        req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
           .forward(req, res);
    }
}