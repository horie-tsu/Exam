package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassListAction extends Action {//未完

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        SchoolDao scDao = new SchoolDao();
        ClassNumDao cNumDao = new ClassNumDao();
        Map<String, String> errors = new HashMap<>();

        // 学校一覧（プルダウン用）
        List<School> schools = scDao.get();
        req.setAttribute("schools", schools);

        // パラメータ取得
        String scName = req.getParameter("f1");

        List<String> classes = null;

        // 検索処理
        if (scName != null && !scName.equals("0")) {

            // 学校取得（名前 or コードに応じて）
            School school = scDao.get(scName);

            if (school != null) {
                classes = cNumDao.filter(school);
            } else {
                errors.put("f1", "学校が見つかりません");
            }

        }

        // 値保持
        req.setAttribute("f1", scName);
        req.setAttribute("classes", classes);
        req.setAttribute("errors", errors);

        // フォワード
        req.getRequestDispatcher("class_list.jsp").forward(req, res);
    }
}