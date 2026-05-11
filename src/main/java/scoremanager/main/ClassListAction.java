package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ClassNum;
import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SchoolDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassListAction extends Action {

    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res)
            throws Exception {

        HttpSession session =
                req.getSession();

        Teacher teacher =
                (Teacher)session.getAttribute("user");

        // ログイン確認
        if (teacher == null) {

            res.sendRedirect("../login.jsp");
            return;
        }

        // DAO
        SchoolDao scDao =
                new SchoolDao();

        ClassNumDao cNumDao =
                new ClassNumDao();

        // エラー
        Map<String, String> errors =
                new HashMap<>();

        // 学校一覧
        List<School> schools =
                scDao.get();

        req.setAttribute(
                "schools",
                schools
        );

        // パラメータ取得
        String schoolCd =
                req.getParameter("f1");

        // クラス一覧
        List<ClassNum> classes =
                null;

        // 学校選択時
        if (schoolCd != null &&
            !schoolCd.equals("") &&
            !schoolCd.equals("0")) {

            // 選択学校のみ表示
            classes =
                cNumDao.filter(schoolCd);

        } else {

            // 全学校表示
            classes =
                cNumDao.all();
        }

        // 値保持
        req.setAttribute(
                "f1",
                schoolCd
        );

        req.setAttribute(
                "classList",
                classes
        );

        req.setAttribute(
                "errors",
                errors
        );

        // JSPへ
        req.getRequestDispatcher(
            "class_list.jsp"
        ).forward(req, res);
    }
}