package scoremanager.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import bean.ClassNum;
import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import tool.Action;

public class CsvAction extends Action {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {

        request.setCharacterEncoding("UTF-8");

        // GET → CSV画面
        if (request.getMethod().equals("GET")) {

            RequestDispatcher rd =
                    request.getRequestDispatcher(
                            "/scoremanager/csv/csv_input.jsp");

            rd.forward(request, response);

            return;
        }

        try {

            // CSV種類取得
            String type =
                    request.getParameter("type");

            // セッション取得
            HttpSession session =
                    request.getSession();

            Teacher teacher =
                    (Teacher) session.getAttribute("user");

            School school =
                    teacher.getSchool();

            // CSV取得
            Part csv =
                    request.getPart("csv");

            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(
                                    csv.getInputStream(),
                                    "UTF-8"));

            // ヘッダー読み飛ばし
            br.readLine();

            String line;

            // =========================
            // 学生CSV
            // =========================
            if (type.equals("student")) {

                StudentDao dao =
                        new StudentDao();

                while ((line = br.readLine()) != null) {

                    String[] data =
                            line.split(",");

                    Student s =
                            new Student();

                    s.setNo(data[0].trim());

                    s.setName(data[1].trim());

                    s.setEntYear(
                            Integer.parseInt(
                                    data[2].trim()));

                    s.setClassNum(
                            data[3].trim());

                    s.setAttend(
                            Boolean.parseBoolean(
                                    data[4].trim()));

                    s.setSchool(school);

                    dao.save(s);
                }

                br.close();

                new StudentListAction().execute(
                        request,
                        response);

                return;
            }

            // =========================
            // 科目CSV
            // =========================
            if (type.equals("subject")) {

                SubjectDao dao =
                        new SubjectDao();

                while ((line = br.readLine()) != null) {

                    String[] data =
                            line.split(",");

                    Subject s =
                            new Subject();

                    s.setCd(
                            data[0].trim());

                    s.setName(
                            data[1].trim());

                    s.setSchool(school);

                    dao.save(s);
                }

                br.close();

                new SubjectListAction().execute(
                        request,
                        response);

                return;
            }

         // =========================
         // クラスCSV
         // =========================
         if (type.equals("classnum")) {

             ClassNumDao dao =
                     new ClassNumDao();

             while ((line = br.readLine()) != null) {

                 String[] data =
                         line.split(",");

                 ClassNum c =
                         new ClassNum();

                 // 学校コード
                 c.setSchoolCd(
                         school.getCd()
                 );

                 // クラス番号
                 c.setClassNum(
                         data[0].trim()
                 );

                 dao.save(c);
             }

             br.close();

             new ClassListAction().execute(
                     request,
                     response
             );

             return;
         }
           

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "error",
                    e.getMessage());

            RequestDispatcher rd =
                    request.getRequestDispatcher(
                            "/scoremanager/csv/csv_error.jsp");

            rd.forward(request, response);
        }
    }
}