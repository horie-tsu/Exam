package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	        
	        String entYearStr = req.getParameter("f1");
	        String num = req.getParameter("f2");
	        String subCd = req.getParameter("f3");
	        String testNo = req.getParameter("f4");
	        String[] studentNo = req.getParameterValues("studentNo[]");
	        String[] subjectCd = req.getParameterValues("subjectCd[]");
	        String[] schoolCd = req.getParameterValues("schoolCd[]");
	        String[] no = req.getParameterValues("no[]");
	        String[] point = req.getParameterValues("point[]");

	        List<Test> list = new ArrayList<>();
	       
	        
	        // エラー
	        Map<String, String> errors = new HashMap<>();

	        for (int i = 0; i < point.length; i++) {
	            int p;
	            try {
	                p = Integer.parseInt(point[i]);
	            } catch (NumberFormatException e) {
	                errors.put("point" + i, (i + 1) + "行目：数値を入力してください。");
	                continue;
	            }

	            if (p < 0 || p > 100) {
	                errors.put("point" + i, (i + 1) + "行目：0〜100の値を入力してください。");
	            }
	        }

	        if (!errors.isEmpty()) {//もしエラーが遭ったら検索結果をそのままにするために一度レジストアクションに送る
	            req.setAttribute("errors", errors);
	            req.setAttribute("f1", entYearStr);
	            req.setAttribute("f2", num);
				req.setAttribute("f3", subCd);
	            req.setAttribute("f4", testNo);
	            req.getRequestDispatcher("TestRegist.action").forward(req, res);
	            return;
	        }
	        
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

	    


	        // 更新処理
	        
	        TestDao dao = new TestDao();
	        dao.updatePoints(list);  // ← 修正（update → save）
	        req.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp")
	           .forward(req, res);
	    }
}
