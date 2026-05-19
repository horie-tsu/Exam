package scoremanager.main;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import tool.Action;

@MultipartConfig
public class SubjectImageCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        // セッション取得
        HttpSession session = req.getSession();

        // ログインユーザー取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // DAO
        SubjectDao subDao = new SubjectDao();

        // エラーメッセージ用
        Map<String, String> errors = new HashMap<>();

        // パラメータ取得
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        // ログインチェック
        if (teacher == null) {
            errors.put("teacher", "ログイン情報がありません");
        }

        // 科目名チェック
        if (name == null || name.isEmpty()) {
            errors.put("name", "科目名を入力してください");
        }

        // ▼ 画像取得（エラー時も保持したいので先に取得）
        Part imagePart = req.getPart("image");
        String fileName = null;

        if (imagePart != null && imagePart.getSize() > 0) {
            fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
        }

        // エラーがある場合
        if (!errors.isEmpty()) {

            req.setAttribute("errors", errors);

            // 入力値を保持
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);

            req.getRequestDispatcher("subject_image_create.jsp")
               .forward(req, res);

            return;
        }

        // ▼ 画像保存処理（エラーがない場合のみ）
        if (fileName != null) {
            String uploadPath = req.getServletContext().getRealPath("/uploads/subject");
            File dir = new File(uploadPath);
            if (!dir.exists()) dir.mkdirs();

            imagePart.write(uploadPath + File.separator + fileName);
        }

        // 科目登録
        Subject sub = new Subject();
        sub.setCd(cd);
        sub.setName(name);
        sub.setSchool(teacher.getSchool());
        sub.setImagePath(fileName); // ★画像パスをセット

        // 保存
        subDao.save(sub);

        // 完了画面へ
        res.sendRedirect("subject_image_create_done.jsp");
    }
}
