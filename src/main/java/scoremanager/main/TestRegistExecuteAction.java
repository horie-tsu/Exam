package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistExecuteAction extends Action {

	    @Override
	    public void execute(HttpServletRequest req, HttpServletResponse res)
	            throws Exception {

	        req.setCharacterEncoding("UTF-8");

	        String[] studentNo = req.getParameterValues("studentNo[]");
	        String[] subjectCd = req.getParameterValues("subjectCd[]");
	        String[] schoolCd = req.getParameterValues("schoolCd[]");
	        String[] no = req.getParameterValues("no[]");
	        String[] point = req.getParameterValues("point[]");

	        List<Test> list = new ArrayList<>();
	        
	        for (int i = 0; i < point.length; i++) {

	            Test t = new Test();

	            // 複合キー
	            Student st = new Student();
	            st.setNo(studentNo[i]);
	            t.setStudent(st);

	            Subject sub = new Subject();
	            sub.setCd(subjectCd[i]);
	            t.setSubject(sub);

	            School sc = new School();
	            sc.setCd(schoolCd[i]);
	            t.setSchool(sc);

	            t.setNo(Integer.parseInt(no[i]));

	            // 点数
	            t.setPoint(Integer.parseInt(point[i]));

	            list.add(t);
	        }

//	        // ① 科目名未入力チェック
//	        if (entYearStr == null || entYearStr.trim().isEmpty()) {
//	            req.setAttribute("errorMsg", "年度を入力してください。");
//	            req.setAttribute("year",entYearStr);
//	        	req.setAttribute("num",num);
//	        	req.setAttribute("sub",sub);
//	        	req.setAttribute("no",no);
//
//	            req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
//	               .forward(req, res);
//	            return;
//	        }
//	        
//	        if (num == null || num.trim().isEmpty()) {
//	            req.setAttribute("errorMsg", "クラスを入力してください。");
//	            req.setAttribute("year",entYearStr);
//	        	req.setAttribute("num",num);
//	        	req.setAttribute("sub",sub);
//	        	req.setAttribute("no",no);
//
//	            req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
//	               .forward(req, res);
//	            return;
//	        }
//	        
//	        if (sub == null || sub.trim().isEmpty()) {
//	            req.setAttribute("errorMsg", "科目を入力してください。");
//	            req.setAttribute("year",entYearStr);
//	        	req.setAttribute("num",num);
//	        	req.setAttribute("sub",sub);
//	        	req.setAttribute("no",no);
//
//	            req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
//	               .forward(req, res);
//	            return;
//	        }
//	        
//	        if (no == null || no.trim().isEmpty()) {
//	            req.setAttribute("errorMsg", "試験回数を入力してください。");
//	            req.setAttribute("year",entYearStr);
//	        	req.setAttribute("num",num);
//	        	req.setAttribute("sub",sub);
//	        	req.setAttribute("no",no);
//
//	            req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
//	               .forward(req, res);
//	            return;
//	        }


	        // 更新処理
	        
	        TestDao dao = new TestDao();
	        dao.updatePoints(list);  // ← 修正（update → save）
	        req.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp")
	           .forward(req, res);
	    }
}
