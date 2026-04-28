package scoremanager.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher) session.getAttribute("user");

    	SubjectDao subjectDao = new SubjectDao();
    	ClassNumDao classNumDao = new ClassNumDao();

    	// 年度リスト
    	List<Integer> entYearSet = new ArrayList<>();
    	for (int i = 2020; i <= 2026; i++) {
    	    entYearSet.add(i);
    	}

    	// JSP用データセット（←これが無いのが原因）
    	request.setAttribute("ent_year_set", entYearSet);
    	try {
			request.setAttribute("class_num_set", classNumDao.filter(teacher.getSchool()));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	try {
			request.setAttribute("subject_set", subjectDao.filter(teacher.getSchool()));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	request.setAttribute("mode", "student");
        List<String> errors = new ArrayList<>();

        // 学生番号取得
        String studentNo = request.getParameter("f4");

        // 入力チェック
        if (studentNo == null || studentNo.isBlank()) {
            errors.add("学生番号を入力してください。");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/scoremanager/main/test_list_student.jsp")
                   .forward(request, response);
            return;
        }

        try {
            StudentDao studentDao = new StudentDao();
            TestDao testDao = new TestDao();

            // 学生情報取得（★ 修正）
            Student student = studentDao.get(studentNo);

            if (student == null) {
                errors.add("該当する学生が存在しません。");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/scoremanager/main/test_list.jsp")
                       .forward(request, response);
                return;
            }

            // 成績一覧取得
            List<Test> testList = testDao.findByStudentNo(studentNo);

            // JSP に渡す
            request.setAttribute("student", student);
            request.setAttribute("test_list", testList);

        } catch (Exception e) {
            e.printStackTrace();
            errors.add("処理中にエラーが発生しました。");
            request.setAttribute("errors", errors);
        }

        // 画面遷移
        request.getRequestDispatcher("/scoremanager/main/test_list.jsp")
               .forward(request, response);
    }
}
