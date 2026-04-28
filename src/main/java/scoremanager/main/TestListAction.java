package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		if (teacher == null) {
		    res.sendRedirect("login.action");
		    return;
		}

		SubjectDao sDao = new SubjectDao();
		TestDao tDao = new TestDao();
		ClassNumDao cDao = new ClassNumDao();

		// パラメータ取得
		String entYearStr = req.getParameter("f1");
		String classNum   = req.getParameter("f2");
		String subjectCd  = req.getParameter("f3");

		// プルダウン用
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = 2020; i <= 2026; i++) {
		    entYearSet.add(i);
		}

		List<String> classNumList = cDao.filter(teacher.getSchool());
		List<Subject> subjectList = sDao.filter(teacher.getSchool());

		// 入力保持
		req.setAttribute("f1", entYearStr);
		req.setAttribute("f2", classNum);
		req.setAttribute("f3", subjectCd);

		// デフォルト全件表示
		List<Test> tests = tDao.findAll(teacher.getSchool());

		// JSPへ渡す
		req.setAttribute("tests", tests);
		req.setAttribute("subject_set", subjectList);
		req.setAttribute("class_num_set", classNumList);
		req.setAttribute("ent_year_set", entYearSet);
		
		
		req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}
}