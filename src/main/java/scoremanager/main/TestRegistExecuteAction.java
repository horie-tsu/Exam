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

	        String cd = req.getParameter("cd");
	        String name = req.getParameter("name");
	        int no = 0;
	        
	        //System.out.println("check:"+cd+name);

	        TestDao dao = new TestDao();

	        // ② 更新前に存在チェック
	        Test before = dao.get(no);  // ← 修正
	        if (before == null) {
	            req.setAttribute("errorMsg", "科目が存在していません。");
	            req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
	               .forward(req, res);
	            return;
	        }

	        // ① 科目名未入力チェック
	        if (name == null || name.trim().isEmpty()) {
	            req.setAttribute("errorMsg", "科目名を入力してください。");
	            req.setAttribute("cd", cd);
	            req.setAttribute("name", name);

	            req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
	               .forward(req, res);
	            return;
	        }

	        // 文字数チェック
	        if (name.length() > 20) {
	            req.setAttribute("errorMsg", "科目名は20文字以内で入力してください。");
	            req.setAttribute("cd", cd);
	            req.setAttribute("name", name);

	            req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
	               .forward(req, res);
	            return;
	        }

	        // 更新処理
	        Test test = new Test();
	        //subject.setCd(cd);
	        //subject.setName(name);

	        dao.save(test);  // ← 修正（update → save）

	        req.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp")
	           .forward(req, res);
	    }
}
