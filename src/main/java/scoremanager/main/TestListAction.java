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
		String classNum = req.getParameter("classNum");
		String subjectCd = req.getParameter("subjectCd");

		// デフォルト全件表示
		List<Test> tests;

		if ((classNum == null || classNum.isEmpty()) &&
		    (subjectCd == null || subjectCd.isEmpty())) {

		    // 初回 or 未選択 → 全件
		    tests = tDao.findAll(teacher.getSchool());

		} else {

		    // 条件あり → 絞り込み
		    tests = tDao.filter(teacher.getSchool(), classNum, subjectCd);
		}

		// 科目一覧
		List<Subject> subjectList = sDao.filter(teacher.getSchool());

		// クラス一覧
		List<String> classNumList = cDao.filter(teacher.getSchool());

		// JSPへ渡す
		req.setAttribute("classNum", classNum);
		req.setAttribute("subjectCd", subjectCd);
		req.setAttribute("tests", tests);
		req.setAttribute("subject_list", subjectList);
		req.setAttribute("class_num_set", classNumList);

		req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}
}