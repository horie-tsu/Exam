package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        StudentDao sDao = new StudentDao();
        Map<String, String> errors = new HashMap<>();
        
        
        // パラメータ取得
        String entYearStr = req.getParameter("entYear");
        String stuId = req.getParameter("stuId");
        String stuName = req.getParameter("stuName");
        String stuClass = req.getParameter("f2");
        boolean isAttend = Boolean.parseBoolean(req.getParameter("attend"));

        int entYear = 0;

        // 入学年度チェック
        if (entYearStr == null || entYearStr.equals("0")) {
            errors.put("entYear", "入学年度を選択してください");
        } else {
            entYear = Integer.parseInt(entYearStr);
        }

        // 氏名チェック
        if (stuName == null || stuName.trim().isEmpty()) {
            errors.put("stuName", "氏名を入力してください");
        }

        // クラスチェック
        if (stuClass == null || stuClass.equals("0")) {
            errors.put("f2", "クラスを選択してください");
        }

        // エラーがある場合noと一緒にJSPに戻す
        if (!errors.isEmpty()) {
        	req.setAttribute("errors", errors);

        	req.getRequestDispatcher("StudentUpdate.action?no=" + stuId).forward(req, res);
            return;
        }
		
		
		
		
		// 登録処理
        Student student = new Student();
        student.setEntYear(entYear);
        student.setNo(stuId);
        student.setName(stuName);
        student.setClassNum(stuClass);
        student.setSchool(teacher.getSchool());
        student.setAttend(isAttend);

        sDao.save(student);

        // 完了後
        res.sendRedirect("student_update_done.jsp");

	}

}
