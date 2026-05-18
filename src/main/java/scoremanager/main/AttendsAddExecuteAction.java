package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Attendance;
import bean.School;
import bean.Student;
import bean.Teacher;
import dao.AttendDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class AttendsAddExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        String classNum = req.getParameter("classNum");

        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/scoremanager/Login.action");
            return;
        }

        // 日付
        String dayStr = req.getParameter("day");
        LocalDate day = (dayStr == null || dayStr.isEmpty())
            ? LocalDate.now()
            : LocalDate.parse(dayStr);

        // 送信データ取得
        String[] studentNos = req.getParameterValues("studentNo[]");
        String[] attendValues = req.getParameterValues("attend[]");

        if (studentNos == null) {
            req.getRequestDispatcher("AttendsAdd.jsp").forward(req, res);
            return;
        }

        // チェックされた学生番号のセット
        List<String> checkedNos = new ArrayList<>();
        if (attendValues != null) {
            for (String v : attendValues) {
                checkedNos.add(v);
            }
        }

        // 保存
        AttendDao aDao = new AttendDao();
        School school = teacher.getSchool();

        for (String no : studentNos) {
            Attendance a = new Attendance();
            a.setDay(day);
            a.setAttend(checkedNos.contains(no));  // チェックあり=出席、なし=欠席

            Student st = new Student();
            st.setNo(no);
            st.setSchool(school);
            st.setClassNum(classNum);  
            a.setStu(st);

            aDao.save(a);
        }

        req.getRequestDispatcher("/scoremanager/main/AttendsAdd_done.jsp")
            .forward(req, res);
    }
}