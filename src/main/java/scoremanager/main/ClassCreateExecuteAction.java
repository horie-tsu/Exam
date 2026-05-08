package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.ClassNum;
import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		ClassNumDao classDao = new ClassNumDao();
        SchoolDao schoolDao = new SchoolDao();
        Map<String, String> errors = new HashMap<>();
        
        
        
        // パラメータ取得
        String schoolName = req.getParameter("name");
        String classnum = req.getParameter("class_num");
        String schoolCd = req.getParameter("school_cd");

        
        //ログインチェック
        if (teacher == null) {
            errors.put("teacher", "ログイン情報がありません");
            req.setAttribute("errors", errors);
            return;
        }
        
        School school = schoolDao.getByName(schoolName);
     // 学校名チェック
        if (school != null ) {
            errors.put("school_name", "学校名が重複しています");
        }
     // エラーがある場合
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("ClassCreate.action").forward(req, res);
            return;
        }
     // 登録処理
        School newSchool = new School();
        newSchool.setCd(schoolCd);
        newSchool.setName(schoolName);
        schoolDao.save(newSchool);
        
        
        ClassNum class_num = new ClassNum();
        class_num.setClass_num(classnum);
        
        class_num.setSchool(newSchool);
        
        classDao.save(class_num);

        // 完了後
        res.sendRedirect("class_create_done.jsp");

	}

}