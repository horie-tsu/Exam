package scoremanager.main;

import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// 削除対象の科目コードを取得
		String cd = request.getParameter("cd");
		
		SubjectDao dao = new SubjectDao();
		boolean result = dao.delete(cd);
		
		if (!result) {
			// 削除失敗した場合
			request.setAttribute("error", "削除に失敗しました");
			request.getRequestDispatcher("subject_delete.jsp").forward(request, response);
			return;
		}
		
		//削除成功
		response.sendRedirect("subject_delete_done.jsp");
	}
}
