package scoremanager.main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Attendance;
import bean.Student;
import bean.Teacher;
import dao.AttendanceDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class AttendsListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
            return;
        }

        // 日付処理
        String dayStr = req.getParameter("day");
        LocalDate day = (dayStr == null || dayStr.isEmpty())
            ? LocalDate.now()
            : LocalDate.parse(dayStr);

        String move = req.getParameter("move");
        if ("prev".equals(move)) {
            day = day.minusDays(1);
        } else if ("next".equals(move)) {
            day = day.plusDays(1);
        }

        // 学生取得
        StudentDao sDao = new StudentDao();
        List<Student> students;

        String keyword = req.getParameter("keyword");
        if (keyword != null && !keyword.trim().isEmpty()) {
            students = sDao.findByKeyword(teacher.getSchool(), keyword);
        } else {
            students = sDao.filter(teacher.getSchool(), true);
        }

        // 出欠データ取得（前日のデータをデフォルトで表示）
        AttendanceDao aDao = new AttendanceDao();
        List<Attendance> attendanceList = aDao.findByDate(teacher.getSchool(), day);

        // student_no → attend のマップを作成
        Map<String, Boolean> attendMap = new HashMap<>();
        for (Attendance a : attendanceList) {
            attendMap.put(a.getStu().getNo(), a.isAttend());
        }

        req.setAttribute("day", day);
        req.setAttribute("keyword", keyword);
        req.setAttribute("students", students);
        req.setAttribute("attendMap", attendMap);

        req.getRequestDispatcher("AttendsAdd.jsp").forward(req, res);
    }
}