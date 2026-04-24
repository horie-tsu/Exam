package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
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
		Teacher teacher = (Teacher) session.getAttribute("teacher");

		SubjectDao sDao = new SubjectDao();
		TestDao tDao = new TestDao(); // ←作る必要あり

		// パラメータ
		String classNum = req.getParameter("classNum");
		String subjectCd = req.getParameter("subjectCd");

		// 科目一覧
		List<Subject> subjectList = sDao.filter(teacher.getSchool());

		// クラス一覧（必要ならDAOで作る）
		List<String> classNumList = tDao.get(teacher.getSchool());

		// テスト一覧
		List<Test> tests = tDao.filter(teacher.getSchool(), classNum, subjectCd);

		// セット
		req.setAttribute("subject_list", subjectList);
		req.setAttribute("class_num_set", classNumList);
		req.setAttribute("tests", tests);

		// JSPへ
		req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}

}
