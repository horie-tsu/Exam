package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

/**
 * 成績一覧（学生）用 DAO
 */
public class TestListStudentDao {

    private Connection con;

    public TestListStudentDao(Connection con) {
        this.con = con;
    }

    /**
     * 学生番号を指定して成績一覧を取得する
     *
     * @param studentNo 学生番号
     * @return 成績一覧（科目ごとの点数）
     */
    public List<TestListStudent> findByStudentNo(String studentNo)
            throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        String sql =
            "SELECT sub.NAME AS subject_name, " +
            "       t.SUBJECT_CD AS subject_cd, " +
            "       t.NO AS num, " +
            "       t.POINT AS point " +
            "FROM TEST t " +
            "JOIN SUBJECT sub " +
            "  ON t.SUBJECT_CD = sub.CD " +
            " AND t.SCHOOL_CD = sub.SCHOOL_CD " +
            "WHERE t.STUDENT_NO = ? " +
            "ORDER BY sub.CD, t.NO";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, studentNo);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            TestListStudent bean = new TestListStudent();

            bean.setSubjectName(rs.getString("subject_name"));
            bean.setSubjectCd(rs.getString("subject_cd"));
            bean.setNum(rs.getInt("num"));
            bean.setPoint(rs.getInt("point"));

            list.add(bean);
        }

        rs.close();
        ps.close();

        return list;
    }
}