package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SubjectDeleteAction {
	public void execute (
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {
		// パラメータ取得(科目コード)
		String cd = request.getParameter("cd");
		
		// DAOから科目情報を取得
		SubjectDao dao = new SubjectDao();
		Subject subject = dao.get(cd);
		
		// JSPに渡す
		request.setAttribute("subject", subject);
		
		//JSPへフォワード7
		request.getRequestDispatcher("subject_delete.jsp").forward(request, response);

	}
}
