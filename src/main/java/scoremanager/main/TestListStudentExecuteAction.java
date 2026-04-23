package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
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

        List<String> errors = new ArrayList<>();

        // ===== パラメータ =====
        String studentNo = req.getParameter("studentNo");

        // ===== DAO =====
        StudentDao studentDao = new StudentDao();
        TestDao testDao = new TestDao();
        SubjectDao subjectDao = new SubjectDao();
        ClassNumDao classNumDao = new ClassNumDao();

        // ===== プルダウン =====
        req.setAttribute("ent_year_set", studentDao.getEntYearSet());
        req.setAttribute("class_num_set", classNumDao.filter(teacher.getSchoolCd()));
        req.setAttribute("subject_set", subjectDao.filter(teacher.getSchoolCd()));

        // ===== 入力チェック =====
        if (studentNo == null || studentNo.isEmpty()) {
            errors.add("学生番号を入力してください");
        }

        Student student = null;

        if (errors.size() == 0) {

            student = studentDao.get(studentNo);

            if (student == null) {
                errors.add("学生が存在しません");
            } else {

                // ===== 成績取得 =====
                List<Test> list = testDao.filter(studentNo);

                req.setAttribute("student", student);
                req.setAttribute("test_list", list);
            }
        }

        req.setAttribute("errors", errors);

        req.getRequestDispatcher("/scoremanager/main/test_list_student.jsp").forward(req, res);
    }
}