package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDao {

    private Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(
            "jdbc:h2:tcp://localhost/~/exam", "sa", ""
        );
    }

    // =========================
    // 学生番号検索
    // =========================
    public List<TestListStudent> filterstu(String studentNo) throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        String sql =
            "SELECT sub.NAME AS subject_name, " +
            "       t.SUBJECT_CD, " +
            "       t.NO, " +
            "       t.POINT " +
            "FROM TEST t " +
            "JOIN SUBJECT sub " +
            "  ON t.SUBJECT_CD = sub.CD " +
            " AND t.SCHOOL_CD = sub.SCHOOL_CD " +
            "WHERE t.STUDENT_NO = ? " +
            "ORDER BY t.SUBJECT_CD, t.NO";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, studentNo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TestListStudent bean = new TestListStudent();
                    bean.setSubjectName(rs.getString("subject_name"));
                    bean.setSubjectCd(rs.getString("SUBJECT_CD"));
                    bean.setNo(rs.getInt("NO"));
                    bean.setPoint(rs.getInt("POINT"));
                    list.add(bean);
                }
            }
        }

        return list;
    }

    // =========================
    // 条件検索（修正版）
    // =========================
    public List<TestListStudent> filterByCondition(String entYear, String classNum, String subjectCd) throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
            "SELECT sub.NAME AS subject_name, " +
            "       t.SUBJECT_CD, " +
            "       t.NO, " +
            "       t.POINT " +
            "FROM TEST t " +
            "JOIN SUBJECT sub " +
            "  ON t.SUBJECT_CD = sub.CD " +
            " AND t.SCHOOL_CD = sub.SCHOOL_CD " +
            "JOIN STUDENT s " + // ← 追加（超重要）
            "  ON t.STUDENT_NO = s.NO " +
            "WHERE 1=1 "
        );

        List<String> params = new ArrayList<>();

        // 入学年度
        if (entYear != null && !entYear.isEmpty()) {
            sql.append("AND s.ENT_YEAR = ? ");
            params.add(entYear);
        }

        // クラス
        if (classNum != null && !classNum.isEmpty()) {
            sql.append("AND t.CLASS_NUM = ? ");
            params.add(classNum);
        }

        // 科目
        if (subjectCd != null && !subjectCd.isEmpty()) {
            sql.append("AND t.SUBJECT_CD = ? ");
            params.add(subjectCd);
        }

        sql.append("ORDER BY t.NO");

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setString(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TestListStudent bean = new TestListStudent();
                    bean.setSubjectName(rs.getString("subject_name"));
                    bean.setSubjectCd(rs.getString("SUBJECT_CD"));
                    bean.setNo(rs.getInt("NO"));
                    bean.setPoint(rs.getInt("POINT"));
                    list.add(bean);
                }
            }
        }

        return list;
    }
}