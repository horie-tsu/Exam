package scoremanager.main;

import java.time.LocalDate;
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

public class TestRegistAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
	HttpSession session = req.getSession();
	Teacher teacher = (Teacher) session.getAttribute("user");
	
	if (teacher == null) {
	    res.sendRedirect("login.jsp"); // またはログインAction
	    return;
	}
	

	SubjectDao sDao = new SubjectDao();
	TestDao tDao = new TestDao();
	ClassNumDao cDao=new ClassNumDao(); 
	LocalDate todaysDate = LocalDate.now();
	int year = todaysDate.getYear();

	// パラメータ
	String entYearStr = req.getParameter("year");
	String num = req.getParameter("num");
	String sub = req.getParameter("sub");
	String no = req.getParameter("no");

	// 一覧取得（←ここが重要）
	List<Test> tests = tDao.findAll(teacher.getSchool());

	// 科目一覧（プルダウン用）
	List<Subject> subjectList = sDao.filter(teacher.getSchool());

	// クラス一覧
	List<String> classNumList = cDao.filter(teacher.getSchool());
	
	//入学年度
	List<Integer> entYearSet = new ArrayList<>();
	// 10年前から1年後まで年をリストに追加
	for (int i = year - 10; i < year + 1; i++) {
		entYearSet.add(i);
	}
	
	//試験回数
	List<Integer> noSet = new ArrayList<>();
	// 10年前から1年後まで年をリストに追加
	for (int j = 1; j < 11; j++) {
		noSet.add(j);
	}
	
	
	
	// セット
	req.setAttribute("tests", tests);
	req.setAttribute("subject_list", subjectList);
	req.setAttribute("class_num_set", classNumList);
	req.setAttribute("ent_year_set", entYearSet);
	req.setAttribute("no_set", noSet);
	
	req.setAttribute("f1",entYearStr);
	req.setAttribute("f2",num);
	req.setAttribute("f3",sub);
	req.setAttribute("f4",no);

	// JSPへ
	req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}

}
