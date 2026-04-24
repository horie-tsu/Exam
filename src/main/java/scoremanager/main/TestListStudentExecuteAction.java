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

        List<Integer> entYearSet = List.of(2021, 2022, 2023, 2024);

        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumDao.filter(teacher.getSchool()));
        req.setAttribute("subject_set", subjectDao.filter(teacher.getSchool()));

        List<TestListStudent> list = null;
        Student student = null;

        // ======================
        // 学生番号優先
        // ======================
        if (studentNo != null && !studentNo.isEmpty()) {

            student = studentDao.get(studentNo);

            if (student == null) {
                errors.add("学生が存在しません");
            } else {
                list = testDao.filterstu(studentNo);
                req.setAttribute("student", student);
            }

        // ======================
        // 条件検索
        // ======================
        } else if (
            entYear != null && !entYear.isEmpty() &&
            classNum != null && !classNum.isEmpty() &&
            subjectCd != null && !subjectCd.isEmpty()
        ) {
            list = testDao.filterByCondition(entYear, classNum, subjectCd);

        } else {
            errors.add("検索条件を入力してください");
        }

        req.setAttribute("test_list", list);
        req.setAttribute("errors", errors);

        req.getRequestDispatcher("/scoremanager/main/test_list_student.jsp")
           .forward(req, res);
    }
}