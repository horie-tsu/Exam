package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
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
	        
	        //System.out.println("check:"+cd+name);

	        SubjectDao dao = new SubjectDao();

	        // ② 更新前に存在チェック
	        Subject before = dao.get(cd);  // ← 修正
	        if (before == null) {
	            req.setAttribute("errorMsg", "科目が存在していません。");
	            req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
	               .forward(req, res);
	            return;
	        }

	        // ① 科目名未入力チェック
	        if (name == null || name.trim().isEmpty()) {
	            req.setAttribute("errorMsg", "科目名を入力してください。");
	            req.setAttribute("cd", cd);
	            req.setAttribute("name", name);

	            req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
	               .forward(req, res);
	            return;
	        }

	        // 文字数チェック
	        if (name.length() > 20) {
	            req.setAttribute("errorMsg", "科目名は20文字以内で入力してください。");
	            req.setAttribute("cd", cd);
	            req.setAttribute("name", name);

	            req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
	               .forward(req, res);
	            return;
	        }

	        // 更新処理
	        Subject subject = new Subject();
	        subject.setCd(cd);
	        subject.setName(name);

	        dao.save(subject);  // ← 修正（update → save）

	        req.getRequestDispatcher("/scoremanager/main/subject_update_done.jsp")
	           .forward(req, res);
	    }
}
