package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session = req.getSession();

        SubjectDao subDao = new SubjectDao();
        Map<String, String> errors = new HashMap<>();
        
        
        // パラメータ取得
        String subcd = req.getParameter("cd");
        String subname = req.getParameter("name");
        String stuName = req.getParameter("stuName");
        String stuClass = req.getParameter("f2");
        boolean isAttend = Boolean.parseBoolean(req.getParameter("attend"));

        int entYear = 0;

	}

}
