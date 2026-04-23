package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
	public List<TestListSubject> filtersub(int entYear, String classNum, String subCd) throws Exception {

	    List<TestListSubject> list = new ArrayList<>();
	    Connection connection = getConnection();
	    PreparedStatement statement = null;

	    try {
	        statement = connection.prepareStatement(//SQL文
	            "SELECT s.ENT_YEAR, s.NO, s.NAME, s.CLASS_NUM, " +
	            "t.TEST_NO, t.POINT " +
	            "FROM STUDENT s " +
	            "INNER JOIN TEST t " +
	            "ON s.NO = t.STUDENT_NO " +
	            "WHERE s.ENT_YEAR=? " +
	            "AND t.CLASS_NUM=? " +
	            "AND t.SUBJECT_CD=? " +
	            "ORDER BY s.NO, t.TEST_NO"
	        );

	        statement.setInt(1, entYear);
	        statement.setString(2, classNum);
	        statement.setString(3, subCd);

	        ResultSet rSet = statement.executeQuery();

	        TestListSubject current = null;
	        String currentStudentNo = null;

	        while (rSet.next()) {

	            String studentNo = rSet.getString("NO");

	            // 学生変わったら新しく作る
	            if (current == null || !studentNo.equals(currentStudentNo)) {

	                current = new TestListSubject();
	                current.setEntYear(rSet.getInt("ENT_YEAR"));
	                current.setStudentNo(studentNo);
	                current.setStudentName(rSet.getString("NAME"));
	                current.setClassNum(rSet.getString("CLASS_NUM"));

	                current.setPoints(new java.util.HashMap<>());

	                list.add(current);
	                currentStudentNo = studentNo;
	            }

	            // Mapに追加（テスト回数 → 点数）
	            int testNo = rSet.getInt("TEST_NO");
	            int point = rSet.getInt("POINT");

	            current.getPoints().put(testNo, point);
	        }

	    } finally {
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
	    }

	    return list;
	}
	
}
