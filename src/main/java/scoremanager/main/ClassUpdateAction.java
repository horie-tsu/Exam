package scoremanager.main;

import bean.ClassNum;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassUpdateAction extends Action {

    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res)
            throws Exception {

        // パラメータ取得
        String schoolCd =
                req.getParameter("schoolCd");

        String classNum =
                req.getParameter("classNum");

        // DAO
        ClassNumDao dao =
                new ClassNumDao();

        // データ取得
        ClassNum c =
                dao.get(
                    schoolCd,
                    classNum
                );

        // requestへセット
        req.setAttribute(
                "classNumData",
                c
        );

        // JSPへ
        req.getRequestDispatcher(
            "class_update.jsp"
        ).forward(req, res);
    }
}