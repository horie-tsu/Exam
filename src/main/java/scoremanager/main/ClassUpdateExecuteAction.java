package scoremanager.main;

import bean.ClassNum;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassUpdateExecuteAction extends Action {

    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res)
            throws Exception {

        // パラメータ取得
        String schoolCd =
                req.getParameter("schoolCd");

        String oldClassNum =
                req.getParameter("oldClassNum");

        String classNum =
                req.getParameter("classNum");

        // 入力チェック
        if (classNum == null ||
            classNum.isEmpty()) {

            req.setAttribute(
                "error",
                "クラス番号を入力してください"
            );

            req.getRequestDispatcher(
                "class_update.jsp"
            ).forward(req, res);

            return;
        }

        // Bean
        ClassNum c = new ClassNum();

        c.setSchoolCd(schoolCd);
        c.setClassNum(classNum);

        // DAO
        ClassNumDao dao =
                new ClassNumDao();

        // 更新SQL
        dao.update(
            schoolCd,
            oldClassNum,
            classNum
        );

        
        res.sendRedirect(
            "class_update_done.jsp"
        );
    }
}