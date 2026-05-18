package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Attendance;
import bean.Student;

public class AttendDao extends Dao {
	public Attendance save(Attendance attendance) throws Exception {
	    Connection connection = getConnection();
	    PreparedStatement statement = null;

	    try {
	    	statement = connection.prepareStatement(
	    		    "MERGE INTO attendance (date, student_no, attend, school_cd, class_num) " +
	    		    "VALUES (?, ?, ?, ?, ?)"
	    		);
	        statement.setObject(1, attendance.getDay());
	        statement.setString(2, attendance.getStu().getNo());
	        statement.setBoolean(3, attendance.isAttend());
	        statement.setString(4, attendance.getStu().getSchool().getCd());
	        statement.setString(5, attendance.getStu().getClassNum());

	        statement.executeUpdate();

	    } catch (Exception e) {
	        throw e;
	    } finally {
	        if (statement != null) {
	            try { statement.close(); } catch (SQLException sqle) { throw sqle; }
	        }
	        if (connection != null) {
	            try { connection.close(); } catch (SQLException sqle) { throw sqle; }
	        }
	    }
	    return attendance;
	}
	public List<Attendance> get(Student stu) throws Exception {
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    List<Attendance> list = new ArrayList<>();

	    try {
	        statement = connection.prepareStatement(
	            "SELECT date, attend FROM attendance " +
	            "WHERE student_no = ? AND school_cd = ? AND class_num = ? " +
	            "ORDER BY date ASC"
	        );
	        statement.setString(1, stu.getNo());
	        statement.setString(2, stu.getSchool().getCd());
	        statement.setString(3, stu.getClassNum());

	        ResultSet rSet = statement.executeQuery();

	        while (rSet.next()) {
	            Attendance a = new Attendance();
	            a.setDay(rSet.getObject("date", LocalDate.class));
	            a.setAttend(rSet.getBoolean("attend"));
	            a.setStu(stu);
	            list.add(a);
	        }

	    } catch (Exception e) {
	        throw e;
	    } finally {
	        if (statement != null) {
	            try { statement.close(); } catch (SQLException sqle) { throw sqle; }
	        }
	        if (connection != null) {
	            try { connection.close(); } catch (SQLException sqle) { throw sqle; }
	        }
	    }
	    return list;
	}
	public List<Attendance> findByDate(bean.School school, LocalDate day) throws Exception {
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    List<Attendance> list = new ArrayList<>();

	    try {
	        statement = connection.prepareStatement(
	            "SELECT student_no, attend, class_num FROM attendance " +
	            "WHERE date = ? AND school_cd = ? " +
	            "ORDER BY student_no ASC"
	        );
	        statement.setObject(1, day);
	        statement.setString(2, school.getCd());

	        ResultSet rSet = statement.executeQuery();

	        while (rSet.next()) {
	            Attendance a = new Attendance();
	            a.setDay(day);
	            a.setAttend(rSet.getBoolean("attend"));

	            Student st = new Student();
	            st.setNo(rSet.getString("student_no"));
	            st.setClassNum(rSet.getString("class_num"));
	            st.setSchool(school);
	            a.setStu(st);

	            list.add(a);
	        }
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        if (statement != null) {
	            try { statement.close(); } catch (SQLException sqle) { throw sqle; }
	        }
	        if (connection != null) {
	            try { connection.close(); } catch (SQLException sqle) { throw sqle; }
	        }
	    }
	    return list;
	}
}
