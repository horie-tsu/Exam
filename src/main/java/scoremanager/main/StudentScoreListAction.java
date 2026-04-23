package scoremanager.main;

import java.util.List;

import bean.StudentResult;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentScoreListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String studentNo = req.getParameter("studentNo");

        TestListStudentDao dao = new TestListStudentDao();
        List<StudentResult> list = dao.filter(studentNo);

        req.setAttribute("scoreList", list);

        req.getRequestDispatcher("student_score_list.jsp").forward(req, res);
    }
}