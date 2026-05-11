package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassCreateAction extends Action {

    @Override
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res)
            throws Exception {

        // エラー用
        Map<String, String> errors =
                new HashMap<>();

        req.setAttribute(
                "errors",
                errors
        );

        // JSPへ
        req.getRequestDispatcher(
            "class_create.jsp"
        ).forward(req, res);
    }
}