package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        List<String> errors = new ArrayList<>();

        // パラメータ
        String entYearStr = req.getParameter("entYear");
        String classNum   = req.getParameter("classNum");
        String subjectCd  = req.getParameter("subjectCd");

        List<TestListSubject> list = new ArrayList<>();

        // ======================
        // 条件チェック
        // ======================
        if (entYearStr == null || entYearStr.isEmpty() ||
            classNum == null || classNum.isEmpty() ||
            subjectCd == null || subjectCd.isEmpty()) {

            errors.add("入学年度・クラス・科目を選択してください");

        } else {

            int entYear = Integer.parseInt(entYearStr);

            // Subject作成
            Subject subject = new Subject();
            subject.setCd(subjectCd);
            subject.setSchool(teacher.getSchool());

            // データ取得
            list = testDao.filtersub(subject, entYear, classNum);

            if (list.isEmpty()) {
                errors.add("該当する成績が見つかりません");
            }
        }

        // セット
        req.setAttribute("test_list", list);
        req.setAttribute("errors", errors);

        // JSPへ
        req.getRequestDispatcher("/scoremanager/main/test_list.jsp")
           .forward(req, res);
    }
}