package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		TestListSubjectDao TBDao =new TestListSubjectDao();//専用Dao
		SubjectDao subDao=new SubjectDao();
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		Map<String, String> errors = new HashMap<>();
	
		
		 // パラメータ取得
        String entYearStr = req.getParameter("entYear");
        String subCd = req.getParameter("subCd");
        String classNum = req.getParameter("ClassNum");
		
        int entYear = 0;

     // 入学年度チェック
     if (entYearStr == null || entYearStr.equals("0")) {
         errors.put("entYear", "入学年度を選択してください");
     } else {
         entYear = Integer.parseInt(entYearStr);
     }

     // 科目コードチェック
     if (subCd == null || subCd.isEmpty()) {
         errors.put("subCd", "科目コードを入力してください");
     }

     // クラス番号チェック
     if (classNum == null || classNum.isEmpty()) {
         errors.put("classNum", "クラス番号を入力してください");
     }

     // ログインチェック
     if (teacher == null) {
         errors.put("teacher", "ログイン情報がありません");
     }

     // エラー処理
     if (!errors.isEmpty()) {
         req.setAttribute("errors", errors);
         req.getRequestDispatcher("TestListSubject.action").forward(req, res);
         return;
     }
        
     Subject sub = new Subject();
     sub.setCd(subCd);
     sub.setSchool(teacher.getSchool());
		
     TBDao.filtersub(sub, entYear, classNum);

	}

}
