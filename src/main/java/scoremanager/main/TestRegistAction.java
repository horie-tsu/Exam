package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session=req.getSession();//セッション
		Teacher teacher =(Teacher)session.getAttribute("user");
		
		ClassNumDao cNumDao=new ClassNumDao();//クラス番号Daoを初期化
		SubjectDao subDao=new SubjectDao();
		//ClassNumDao
		List<String> cls = cNumDao.filter(teacher.getSchool());
		//
		List<Subject> sub = subDao.filter(teacher.getSchool());
		
		//JSPへフォワード
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
		
	}

}
