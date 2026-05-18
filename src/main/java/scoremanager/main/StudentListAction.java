package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ClassNum;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// セッション
		HttpSession session = req.getSession();

		// ログインユーザー取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		// パラメータ
		String entYearStr = "";
		String classNum = "";
		String isAttendStr = "";

		// 変数
		int entYear = 0;
		boolean isAttend = false;

		// 学生リスト
		List<Student> students = null;

		// 現在年取得
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();

		// DAO
		StudentDao sDao = new StudentDao();
		ClassNumDao cNumDao = new ClassNumDao();

		// エラー
		Map<String, String> errors = new HashMap<>();

		// リクエストパラメータ取得
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		isAttendStr = req.getParameter("f3");

		// 入学年度変換
		if (entYearStr != null && !entYearStr.equals("0")) {
			entYear = Integer.parseInt(entYearStr);
		}

		// 在学フラグ
		if (isAttendStr != null) {
			isAttend = true;
		}

		// 入学年度リスト
		List<Integer> entYearSet = new ArrayList<>();

		// 10年前〜現在年
		for (int i = year - 10; i <= year; i++) {
			entYearSet.add(i);
		}

		// クラス一覧取得
		List<ClassNum> list =
				cNumDao.filter(
						teacher.getSchool().getCd()
				);

		// 学生検索
		if (entYear != 0 &&
				classNum != null &&
				!classNum.equals("0")) {

			// 入学年度 + クラス
			students = sDao.filter(
					teacher.getSchool(),
					entYear,
					classNum,
					isAttend
			);

		} else if (entYear != 0 &&
				(classNum == null || classNum.equals("0"))) {

			// 入学年度のみ
			students = sDao.filter(
					teacher.getSchool(),
					entYear,
					isAttend
			);

		} else if (entYear == 0 &&
		        (classNum == null || classNum.equals("0"))) {

		    // 条件なし
		    students = sDao.filter(
		            teacher.getSchool(),
		            isAttend
		    );

		} else {

		    // クラスだけ選択
		    errors.put(
		            "f1",
		            "クラスを指定する場合は入学年度も指定してください"
		    );

		    req.setAttribute("errors", errors);

		    // 全件表示
		    students = sDao.filter(
		            teacher.getSchool(),
		            false
		    );
		}

		// リクエストセット
		req.setAttribute("f1", entYear);
		req.setAttribute("f2", classNum);
		req.setAttribute("f3", isAttend);

		req.setAttribute("students", students);

		req.setAttribute("class_num_set", list);

		req.setAttribute("ent_year_set", entYearSet);

		// JSPへフォワード
		req.getRequestDispatcher("student_list.jsp")
				.forward(req, res);
	}
}