package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

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

	    // パラメータ取得（JSPと一致）
	    String entYearStr = req.getParameter("f1");
	    String classNum   = req.getParameter("f2");
	    String subjectCd  = req.getParameter("f3");

	    SubjectDao subjectDao = new SubjectDao();
	    TestListSubjectDao testDao = new TestListSubjectDao();
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
	    req.setAttribute("f1", entYearStr);
	    req.setAttribute("f2", classNum);
	    req.setAttribute("f3", subjectCd);

	    List<TestListSubject> list = new ArrayList<>();

	    if (entYearStr == null || entYearStr.isEmpty() ||
	        classNum == null || classNum.isEmpty() ||
	        subjectCd == null || subjectCd.isEmpty()) {

	        errors.add("入学年度・クラス・科目を選択してください");

	    } else {

	        int entYear = Integer.parseInt(entYearStr);

	        Subject subject = new Subject();
	        subject.setCd(subjectCd);
	        subject.setSchool(teacher.getSchool());

	        // ===== データ取得 =====
	        list = testDao.filtersub(subject, entYear, classNum);

	        // ===== 科目名取得（NULL安全）=====
	        Subject fullSubject = subjectDao.get(subjectCd);
	        if (fullSubject != null) {
	            req.setAttribute("cdname", fullSubject.getName());
	        } else {
	            req.setAttribute("cdname", subjectCd); // fallback
	        }

	        // ===== データなし =====
	        if (list.isEmpty()) {
	            errors.add("該当する成績が見つかりません");
	        }

	        // ★ 応用：最大テスト回数を渡す（JSPで列自動生成できる）
	        int maxTestNo = 0;
	        for (TestListSubject t : list) {
	            for (Integer key : t.getPoints().keySet()) {
	                if (key != null && key > maxTestNo) {
	                    maxTestNo = key;
	                }
	            }
	        }
	        req.setAttribute("maxTestNo", maxTestNo);
	    }

	    // モード
	    req.setAttribute("mode", "subject");

	    req.setAttribute("test_list", list);
	    req.setAttribute("errors", errors);

	    req.getRequestDispatcher("/scoremanager/main/test_list.jsp")
	       .forward(req, res);
	}
}