package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.ClassNum;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassCreateExecuteAction extends Action {

    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res)
            throws Exception {

        // 文字コード
        req.setCharacterEncoding("UTF-8");

        // パラメータ取得
        String schoolCd =
                req.getParameter("school_cd");

        String classNum =
                req.getParameter("class_num");

        // エラー
        Map<String, String> errors =
                new HashMap<>();

        // DAO
        ClassNumDao dao =
                new ClassNumDao();

        // 入力チェック
        if (classNum == null ||
            classNum.equals("")) {

            errors.put(
                "class_num",
                "クラス番号を入力してください"
            );
        }

        // 重複チェック
        ClassNum old =
                dao.get(
                    schoolCd,
                    classNum
                );

        if (old != null) {

            errors.put(
                "class_num",
                "すでに存在しています"
            );
        }

        // エラーあり
        if (!errors.isEmpty()) {

            req.setAttribute(
                "errors",
                errors
            );

            req.getRequestDispatcher(
                "class_create.jsp"
            ).forward(req, res);

            return;
        }

        // Beanにセット
        ClassNum c =
                new ClassNum();

        c.setSchoolCd(
            schoolCd
        );

        c.setClassNum(
            classNum
        );

        // 保存
        dao.save(c);

        // 完了画面
        req.getRequestDispatcher(
            "class_create_done.jsp"
        ).forward(req, res);
    }
}