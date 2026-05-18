package scoremanager.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import bean.AttendScore;
import bean.Attendance;
import bean.Student;
import bean.Teacher;
import dao.AttendDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class AttendScoreListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
            return;
        }

        // 学生一覧取得
        StudentDao sDao = new StudentDao();
        List<Student> students = sDao.filter(teacher.getSchool(), true);

        // 出席率計算
        AttendDao aDao = new AttendDao();
        List<AttendScore> scoreList = new ArrayList<>();

        for (Student s : students) {
            List<Attendance> attendList = aDao.get(s);

            int total = attendList.size();
            int attended = 0;
            for (Attendance a : attendList) {
                if (a.isAttend()) attended++;
            }

            double rate = total == 0 ? 0 : Math.round((double) attended / total * 1000) / 10.0;

            AttendScore score = new AttendScore();
            score.setStudent(s);
            score.setRate(rate);
            scoreList.add(score);
        }

        // 並び替え
        String sort = req.getParameter("sort");
        if ("bad".equals(sort)) {
            // 出席率低い順
            scoreList.sort(Comparator.comparingDouble(AttendScore::getRate));
        } else if ("perfect".equals(sort)) {
            // 100%のみ・学生番号順
            scoreList.removeIf(s -> s.getRate() < 100.0);
            scoreList.sort(Comparator.comparing(s -> s.getStudent().getNo()));
        } else {
            // 学生番号順（default）
            scoreList.sort(Comparator.comparing(s -> s.getStudent().getNo()));
        }

        req.setAttribute("scoreList", scoreList);
        req.setAttribute("sort", sort);

        req.getRequestDispatcher("Attendscorelist.jsp").forward(req, res);
    }
}