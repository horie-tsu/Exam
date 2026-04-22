package scoremanager.csv;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.Dao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import tool.Action;

public class CsvAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Connection conn = null;
        PreparedStatement pstmt = null;
        BufferedReader br = null;

        try {
            Part csv = request.getPart("csv");

            // DB接続
            Dao dao = new Dao();
            conn = dao.getConnection();

            InputStream is = csv.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            // ヘッダー取得
            String headerLine = br.readLine();
            String[] headers = headerLine.split(",");

            // ヘッダー整形（空白除去）
            for (int i = 0; i < headers.length; i++) {
                headers[i] = headers[i].trim();
            }

            // カラム名生成
            String columns = String.join(",", headers);

            // プレースホルダ生成 
            String placeholders = String.join(",", java.util.Collections.nCopies(headers.length, "?"));

            String sql = "MERGE INTO STUDENT (" + columns + ") KEY(NO) VALUES (" + placeholders + ")";
            pstmt = conn.prepareStatement(sql);

            // データ処理
            String line;
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replace("\"", "").trim();

                    // 型ごとにセット
                    if (i == 2) { // ent_year
                        pstmt.setInt(i + 1, Integer.parseInt(data[i]));
                    } else if (i == 4) { // is_attend
                        pstmt.setBoolean(i + 1, Boolean.parseBoolean(data[i]));
                    } else {
                        pstmt.setString(i + 1, data[i]);
                    }
                }

                pstmt.executeUpdate();
            }

            // 成功
            request.getRequestDispatcher("csv-conp.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("csv-error.jsp").forward(request, response);

        } finally {
            if (br != null) br.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }
}