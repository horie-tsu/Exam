package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

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

        String studentNo = req.getParameter("studentNo");
        String entYear   = req.getParameter("entYear");
        String classNum  = req.getParameter("classNum");
        String subjectCd = req.getParameter("subjectCd");

        StudentDao studentDao = new StudentDao();
        TestListStudentDao testDao = new TestListStudentDao();
        SubjectDao subjectDao = new SubjectDao();
        ClassNumDao classNumDao = new ClassNumDao();

        // プルダウン
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = 2020; i <= 2026; i++) {
            entYearSet.add(i);
        }

        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumDao.filter(teacher.getSchool()));
        req.setAttribute("subject_set", subjectDao.filter(teacher.getSchool()));

        // 入力保持
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("studentNo", studentNo);

        List<TestListStudent> list = new ArrayList<>();
        Student student = null;

        // ======================
        // 学生番号検索
        // ======================
        if (studentNo != null && !studentNo.isEmpty()) {

            student = studentDao.get(studentNo);

            if (student == null) {
                errors.add("学生番号が間違っています");
            } else {
                list = testDao.filterstu(studentNo);
                req.setAttribute("student", student);

                if (list.isEmpty()) {
                    errors.add("成績データが存在しません");
                }
            }

        // ======================
        // 条件検索
        // ======================
        } else if (
            (entYear != null && !entYear.isEmpty()) ||
            (classNum != null && !classNum.isEmpty()) ||
            (subjectCd != null && !subjectCd.isEmpty())
        ) {

            list = testDao.filterByCondition(entYear, classNum, subjectCd);

            if (list.isEmpty()) {
                errors.add("該当する成績が見つかりません");
            }

        } else {
            errors.add("検索条件を入力してください");
        }

        req.setAttribute("test_list", list);
        req.setAttribute("errors", errors);

        req.getRequestDispatcher("/scoremanager/main/test_list_student.jsp")
           .forward(req, res);
    }
}