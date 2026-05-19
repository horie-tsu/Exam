package scoremanager.main;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentDao;
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
	    StudentDao studentDao = new StudentDao();
	    TestListSubjectDao testDao = new TestListSubjectDao();
	    ClassNumDao classNumDao = new ClassNumDao();
	    
	    int year = Year.now().getValue();
	    // 入学年度リスト
	 		List<Integer> entYearSet = new ArrayList<>();

	 		// 10年前〜現在年
	 		for (int i = year - 10; i <= year; i++) {
	 			entYearSet.add(i);
	 		}

	    req.setAttribute("ent_year_set", entYearSet);
	    req.setAttribute(
	    	    "class_num_set",
	    	    classNumDao.filter(
	    	        teacher.getSchool().getCd()
	    	    )
	    	);
	    req.setAttribute("subject_set", subjectDao.filter(teacher.getSchool()));

	    // 入力保持
	    req.setAttribute("f1", entYearStr);
	    req.setAttribute("f2", classNum);
	    req.setAttribute("f3", subjectCd);

	    List<TestListSubject> list = new ArrayList<>();

	    if (entYearStr == null || entYearStr.isEmpty() ||
	        classNum == null || classNum.isEmpty() ||
	        subjectCd == null || subjectCd.isEmpty()) {

	        errors.add("入学年度とクラスと科目を選択してください");

	    } else {

	        int entYear = Integer.parseInt(entYearStr);

	        Subject subject = new Subject();
	        subject.setCd(subjectCd);
	        subject.setSchool(teacher.getSchool());

	        // ===== データ取得 =====
	        List<Student> students = studentDao.filter(teacher.getSchool(), entYear, classNum, true);
	        List<TestListSubject> scored = testDao.filtersub(subject, entYear, classNum);
	        
	        Map<String, TestListSubject> map = new HashMap<>();
	        
	        for (TestListSubject t : scored) {
	            map.put(t.getStudentNo(), t);
	        }
	        
	        // ===== 科目名取得（NULL安全）=====
	        Subject fullSubject = subjectDao.get(subjectCd);
	        if (fullSubject != null) {
	            req.setAttribute("cdname", fullSubject.getName());
	        } else {
	            req.setAttribute("cdname", subjectCd); // fallback
	        }

	        
	        
	        // ===== データなし処理 =====
	        for (Student s : students) {

	            if (map.containsKey(s.getNo())) {
	                // 成績あり
	                list.add(map.get(s.getNo()));
	            } else {
	                // ★ 未受験者を追加
	                TestListSubject cleanList = new TestListSubject();
	                cleanList.setEntYear(s.getEntYear());
	                cleanList.setStudentNo(s.getNo());
	                cleanList.setStudentName(s.getName());
	                cleanList.setClassNum(s.getClassNum());

	                // ★ points は空 Map（null は絶対ダメ）
	                cleanList.setPoints(new LinkedHashMap<>());

	                list.add(cleanList);
	            }
	        }
	        
	        
	        //pointを除く要素がどれかnullであればエラーを返す
	        if (list.isEmpty()) {
	            errors.add("学生情報が存在しませんでした");
	        }

	        // ★ 応用：最大テスト回数を渡す（JSPで列自動生成できる）
	        int maxTestNo = 0;
	        for (TestListSubject t : list) {
	        	for (String key : t.getPoints().keySet()) {

	        	    int testNo = Integer.parseInt(key);

	        	    if (testNo > maxTestNo) {
	        	        maxTestNo = testNo;
	        	    }
	        	}
	        }
	        req.setAttribute("maxTestNo", maxTestNo);
	    }
	    // モード
	    req.setAttribute("mode", "subject");

	    req.setAttribute("subjectList", list);
	    req.setAttribute("errors", errors);

	    req.getRequestDispatcher("/scoremanager/main/test_list.jsp")
	       .forward(req, res);
	}
}