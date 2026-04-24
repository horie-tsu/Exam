package scoremanager.main;

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
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		if (teacher == null) {
		    res.sendRedirect("login.jsp"); // またはログインAction
		    return;
		}

		SubjectDao sDao = new SubjectDao();
		TestDao tDao = new TestDao();
		ClassNumDao cDao=new ClassNumDao();

		// パラメータ
		String classNum = req.getParameter("classNum");
		String subjectCd = req.getParameter("subjectCd");

		// 一覧取得（←ここが重要）
		List<Test> tests = tDao.filter(teacher.getSchool(), classNum, subjectCd);

		// 科目一覧（プルダウン用）
		List<Subject> subjectList = sDao.filter(teacher.getSchool());

		// クラス一覧
		List<String> classNumList = cDao.filter(teacher.getSchool());

		// セット
		req.setAttribute("tests", tests);
		req.setAttribute("subject_list", subjectList);
		req.setAttribute("class_num_set", classNumList);

		// JSPへ
		req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}
}
