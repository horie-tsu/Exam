package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		String entYearStr = req.getParameter("f1");
		String num = req.getParameter("f2");
		String subCd = req.getParameter("f3");
		String testNo = req.getParameter("f4");

		String[] studentNo = req.getParameterValues("studentNo[]");
		String[] subjectCd = req.getParameterValues("subjectCd[]");
		String[] schoolCd = req.getParameterValues("schoolCd[]");
		String[] no = req.getParameterValues("no[]");
		String[] point = req.getParameterValues("point[]");

		List<Test> list = new ArrayList<>();

		// エラー
		Map<String, String> errors = new HashMap<>();

		for (int i = 0; i < point.length; i++) {
			
			System.out.println("no=" + no[i]);
			System.out.println("point=" + point[i]);
			

			int p;

			try {

				p = Integer.parseInt(point[i]);

			} catch (NumberFormatException e) {

				errors.put(
					"point" + i,
					(i + 1) + "行目：数値を入力してください。"
				);

				continue;
			}

			if (p < 0 || p > 100) {

				errors.put(
					"point" + i,
					(i + 1) + "行目：0〜100の値を入力してください。"
				);
			}
		}
		
	

		// エラー時
		if (!errors.isEmpty()) {

			TestDao tDao = new TestDao();

			List<Test> tests = tDao.filter(
				    teacher.getSchool(),
				    entYearStr,
				    num,
				    subCd,
				    testNo
				);

			// 検索結果を戻す
			req.setAttribute("tests", tests);
			
			

			// 検索条件を戻す
			req.setAttribute("f1", Integer.parseInt(entYearStr));
			req.setAttribute("f2", num);
			req.setAttribute("f3", subCd);
			req.setAttribute("f4", Integer.parseInt(testNo));

			// エラー
			req.setAttribute("errors", errors);

			// 検索済みフラグ
			req.setAttribute("searched", true);

			// プルダウン再表示用
			ClassNumDao cNumDao = new ClassNumDao();
			SubjectDao sDao = new SubjectDao();
			
			
			
			
			req.setAttribute(
				    "class_num_set",
				    cNumDao.filter(teacher.getSchool().getCd())
				);

			req.setAttribute(
				"subject_list",
				sDao.filter(teacher.getSchool())
			);

			// 回数
			List<Integer> noSet = new ArrayList<>();

			for (int i = 1; i <= 5; i++) {
				noSet.add(i);
			}

			req.setAttribute("no_set", noSet);

			req.getRequestDispatcher("test_regist.jsp")
				.forward(req, res);

			return;
		}

		// 保存用データ作成
		for (int i = 0; i < point.length; i++) {

			Test t = new Test();

			// 学生
			Student st = new Student();
			st.setNo(studentNo[i]);
			t.setStudent(st);

			// 科目
			Subject sub = new Subject();
			sub.setCd(subjectCd[i]);
			t.setSubject(sub);

			// 学校
			School sc = new School();
			sc.setCd(schoolCd[i]);
			t.setSchool(sc);

			// 回数
			t.setNo(Integer.parseInt(no[i]));

			// 点数
			t.setPoint(Integer.parseInt(point[i]));

			list.add(t);
		}

		// 更新処理
		TestDao dao = new TestDao();

		dao.updatePoints(list);

		req.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp")
			.forward(req, res);
	}
}