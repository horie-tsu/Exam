package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		
		HttpSession session = req.getSession(); // セッション
		
		// パラメータ取得
		String no = req.getParameter("no");
		Teacher teacher = (Teacher) session.getAttribute("user");

		// DAO生成
		StudentDao studentDao = new StudentDao();
		ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Daoを初期化

		// ログインユーザの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());
		
		// DBから取得
		Student student = studentDao.get(no);

		// JSPへ渡す
		req.setAttribute("student", student);
		req.setAttribute("class_num_set", list);

		// フォワード
		req.getRequestDispatcher("student_update.jsp").forward(req, res);
	}
}