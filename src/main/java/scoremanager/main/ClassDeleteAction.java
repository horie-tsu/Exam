package scoremanager.main;

import bean.ClassNum;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassDeleteAction extends Action {

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

        // データが存在しない
        if (c == null) {

            req.setAttribute(
                "error",
                "対象データが存在しません"
            );

            req.getRequestDispatcher(
                "error.jsp"
            ).forward(req, res);

            return;
        }

        // 削除実行
        dao.delete(
            schoolCd,
            classNum
        );

        // 完了画面へ
        req.getRequestDispatcher(
            "class_delete_done.jsp"
        ).forward(req, res);
    }
}