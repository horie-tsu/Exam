package scoremanager.main;

import java.util.List;

import bean.Attendance;
import bean.Student;
import bean.Teacher;
import dao.AttendDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class AttendScoreListExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
            return;
        }

        // 学生番号取得
        String no = req.getParameter("no");

        // 学生取得
        StudentDao sDao = new StudentDao();
        Student student = sDao.get(no);

        // 出欠一覧取得
        AttendDao aDao = new AttendDao();
        List<Attendance> attendList = aDao.get(student);

        // 集計
        int total = attendList.size();
        int attended = 0;
        for (Attendance a : attendList) {
            if (a.isAttend()) attended++;
        }
        double rate = total == 0 ? 0 : Math.round((double) attended / total * 1000) / 10.0;

        req.setAttribute("student", student);
        req.setAttribute("attendList", attendList);
        req.setAttribute("total", total);
        req.setAttribute("attended", attended);
        req.setAttribute("rate", rate);

        req.getRequestDispatcher("Attendscore_stu.jsp").forward(req, res);
    }
}