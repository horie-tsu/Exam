package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ClassNum;
import bean.Student;
import bean.Subject;
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

public class TestRegistAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
	HttpSession session = req.getSession();
	Teacher teacher = (Teacher) session.getAttribute("user");
	
	if (teacher == null) {
	    res.sendRedirect("login.Action"); // またはログインAction
	    return;
	}
	
	StudentDao stDao =new StudentDao();
	SubjectDao sDao = new SubjectDao();
	TestDao tDao = new TestDao();
	ClassNumDao cDao=new ClassNumDao(); 
	LocalDate todaysDate = LocalDate.now();
	int year = todaysDate.getYear();

	// パラメータ
	// エラー
    Map<String, String> error = new HashMap<>();
	
    String entYearStr = req.getParameter("f1") != null
    	    ? req.getParameter("f1")
    	    : (String) req.getAttribute("f1");

    	String num = req.getParameter("f2") != null
    	    ? req.getParameter("f2")
    	    : (String) req.getAttribute("f2");

    	String sub = req.getParameter("f3") != null
    	    ? req.getParameter("f3")
    	    : (String) req.getAttribute("f3");

    	String no = req.getParameter("f4") != null
    	    ? req.getParameter("f4")
    	    : (String) req.getAttribute("f4");
	System.out.println("回数"+no);
	//科目表示用に返しているもの
	Subject subBean = (sub != null && !sub.isEmpty()) ? sDao.get(sub) : null;
	boolean searched = req.getParameter("f1") != null
            || req.getAttribute("f1") != null;
	
	
	// 検索欄バリデーション
	if (searched) {
	    if ((entYearStr == null || entYearStr.equals("0"))
	     || (num == null || num.equals("0"))
	     || (sub == null || sub.isEmpty())
	     || (no == null || no.equals("0"))) {
	        error.put("filter", "入学年度とクラスと科目と回数を選択してください。");
	    }
	}
	
	// 一覧
	List<Test> tests = new ArrayList<>();

	// 科目一覧
	List<Subject> subjectList =
	    sDao.filter(teacher.getSchool());

	// クラス一覧
	List<ClassNum> classNumList =
	    cDao.filter(
	        teacher.getSchool().getCd()
	    );

	// 入学年度
	List<Integer> entYearSet =
	    new ArrayList<>();

	for (int i = year - 10; i < year + 1; i++) {
	    entYearSet.add(i);
	}

	// 試験回数
	List<Integer> noSet =
	    new ArrayList<>();

	for (int j = 1; j < 3; j++) {
	    noSet.add(j);
	}

	try {

	    // エラーなし＆検索済み
	    if (error.isEmpty() && searched) {

	        // 学生一覧
	        List<Student> students =
	            stDao.filter(
	                teacher.getSchool(),
	                Integer.parseInt(entYearStr),
	                num,
	                true
	            );

	        // DBの成績
	        List<Test> dbTests =
	            tDao.filter(
	                teacher.getSchool(),
	                entYearStr,
	                num,
	                sub,
	                no
	            );

	        for (Student st : students) {

	            Test found = null;

	            // 既存成績探す
	            for (Test t : dbTests) {

	                if (t.getStudent()
	                     .getNo()
	                     .equals(st.getNo())) {

	                    found = t;
	                    break;
	                }
	            }

	            // あれば追加
	            if (found != null) {

	                tests.add(found);

	            } else {

	                // なければ空行
	                Test empty = new Test();

	                empty.setStudent(st);

	                empty.setClassNum(num);

	                Subject subject =
	                    new Subject();

	                subject.setCd(sub);

	                empty.setSubject(subject);

	                empty.setNo(
	                    Integer.parseInt(no)
	                );

	                empty.setPoint(0);

	                tests.add(empty);
	            }
	        }
	    }

	} catch(Exception e) {

	    e.printStackTrace();
	}
	
	// 入力のほうのエラーの引き継ぎ
	Object errors = req.getAttribute("errors");
	if (errors != null) {
	    req.setAttribute("errors", errors);
	}
	// セット
	req.setAttribute("error", error);
	req.setAttribute("tests", tests);
	req.setAttribute("subject_list", subjectList);
	req.setAttribute("sub", subBean);
	req.setAttribute("class_num_set", classNumList);
	req.setAttribute("ent_year_set", entYearSet);
	req.setAttribute("no_set", noSet);
	req.setAttribute("searched", searched);
	req.setAttribute("f1",entYearStr);
	req.setAttribute("f2",num);
	req.setAttribute("f3",sub);
	req.setAttribute("f4",no);

	// JSPへ
	req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}

}
