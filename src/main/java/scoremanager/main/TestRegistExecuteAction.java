package scoremanager.main;

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

	        String entYearStr = req.getParameter("year");
	    	String num = req.getParameter("num");
	    	String sub = req.getParameter("sub");
	    	String no = req.getParameter("no");
	        
	        //System.out.println("check:"+cd+name);

	        TestDao dao = new TestDao();

	        // ② 更新前に存在チェック
	        Test before = dao.get(1);  // ← 修正
	        if (before == null) {
	            req.setAttribute("errorMsg", "科目が存在していません。");
	            req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
	               .forward(req, res);
	            return;
	        }

	        // ① 科目名未入力チェック
	        if (entYearStr == null || entYearStr.trim().isEmpty()) {
	            req.setAttribute("errorMsg", "年度を入力してください。");
	            req.setAttribute("year",entYearStr);
	        	req.setAttribute("num",num);
	        	req.setAttribute("sub",sub);
	        	req.setAttribute("no",no);

	            req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
	               .forward(req, res);
	            return;
	        }
	        
	        if (num == null || num.trim().isEmpty()) {
	            req.setAttribute("errorMsg", "クラスを入力してください。");
	            req.setAttribute("year",entYearStr);
	        	req.setAttribute("num",num);
	        	req.setAttribute("sub",sub);
	        	req.setAttribute("no",no);

	            req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
	               .forward(req, res);
	            return;
	        }
	        
	        if (sub == null || sub.trim().isEmpty()) {
	            req.setAttribute("errorMsg", "科目を入力してください。");
	            req.setAttribute("year",entYearStr);
	        	req.setAttribute("num",num);
	        	req.setAttribute("sub",sub);
	        	req.setAttribute("no",no);

	            req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
	               .forward(req, res);
	            return;
	        }
	        
	        if (no == null || no.trim().isEmpty()) {
	            req.setAttribute("errorMsg", "試験回数を入力してください。");
	            req.setAttribute("year",entYearStr);
	        	req.setAttribute("num",num);
	        	req.setAttribute("sub",sub);
	        	req.setAttribute("no",no);

	            req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
	               .forward(req, res);
	            return;
	        }


	        // 更新処理
	        Test test = new Test();
	        

	        dao.save(test);  // ← 修正（update → save）

	        req.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp")
	           .forward(req, res);
	    }
}
