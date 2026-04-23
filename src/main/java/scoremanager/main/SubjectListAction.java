package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session=req.getSession();//セッション
		Teacher teacher =(Teacher)session.getAttribute("user");
		List<Subject>subjects=null;
		SubjectDao sDao=new SubjectDao();//科目DAO 
		Map<String,String> errors=new HashMap<>();//エラーメッセージ
		
		
		//ログインユーザの学校コードをもとにクラス番号の一覧を取得
		List<Subject>list=sDao.filter(teacher.getSchool());
			//指定なしの場合
			//全学生情報を取得
			subjects=sDao.filter(teacher.getSchool());
			req.setAttribute("errors", errors);
			//全学生情報を取得
			subjects=sDao.filter(teacher.getSchool());
			//リクエストに学生リストをセット
			req.setAttribute("subjects", subjects);
			//リクエストにデータをセット
			req.setAttribute("subject_set", list);
			
			
		//JSPへフォワード
		req.getRequestDispatcher("subject_list.jsp").forward(req, res);
		
	}
}
