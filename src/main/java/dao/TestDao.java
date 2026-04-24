package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
//コミットテスト
public class TestDao extends Dao {
	private String baseSql = "select * from test where school_cd=?";
	public Test get(int no) throws Exception{
		//テストインスタンスを初期化
		Test test=new Test();
		//データベースへのコネクションを確立
		Connection connection=getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		try {
			//プリペアードステートメントにSQL文をセット
			statement=connection.prepareStatement("select * from test where no=?");
			//プリペアードステートメントに学生番号をバインド
			statement.setInt(1, no);
			//プリペアードステートメントを実行
			ResultSet rSet=statement.executeQuery();
			
			//学生Dao、科目Dao、学校Daoを初期化
			StudentDao studentDao=new StudentDao();
			SubjectDao subjectDao=new SubjectDao();
			SchoolDao schoolDao=new SchoolDao();
			
			if(rSet.next()) {
				//リザルトセットが存在している場合
				//学生インスタンスに検索結果をセット
				test.setNo(rSet.getInt("no"));
				test.setClassNum(rSet.getString("class_num"));
				test.setPoint(rSet.getInt("point"));
				//外部キーから各Beanを取得
				test.setStudent(studentDao.get(rSet.getString("student_no")));
				test.setSubject(subjectDao.get(rSet.getString("subject_cd")));
				test.setSchool(schoolDao.get(rSet.getString("school_cd")));
				
			}else {
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				test=null;
			}
		}catch(Exception e) {
			throw e;
		}finally{
			//プリペアードステートメントを閉じる
			if(statement!=null) {
				try {
					statement.close();
				}catch(SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if(connection!=null) {
				try {
					connection.close();
				}catch(SQLException sqle) {
					throw sqle;
				}
			}
		}
		return test;
		
	}
	public List<Test> postFilter(ResultSet rSet, School school) throws Exception {
	    List<Test> list = new ArrayList<>();
	    StudentDao studentDao = new StudentDao();
	    SubjectDao subjectDao = new SubjectDao();
	    SchoolDao schoolDao = new SchoolDao();
	    
	    try {
	        while (rSet.next()) {
	            Test test = new Test();

	            test.setNo(rSet.getInt("no"));
	            test.setClassNum(rSet.getString("class_num"));
	            test.setPoint(rSet.getInt("point"));

	            // 外部キーから Bean を取得してセット
	            test.setStudent(studentDao.get(rSet.getString("student_no")));
	            test.setSubject(subjectDao.get(rSet.getString("subject_cd")));
	            test.setSchool(schoolDao.get(rSet.getString("school_cd")));

	            list.add(test);
	        }
	    } catch (SQLException | NullPointerException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	//条件検索（学校・クラス・科目等）
	public List<Test> filter(School school, String classNum, String subjectCd) throws Exception{
		//リストを初期化
		List<Test>list=new ArrayList<>();
		//コネクションを確立
		Connection connection=getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		//リザルトセット
		ResultSet rSet=null;
		
		//SQL文の条件
		String condition=" and class_num=? and subject_cd=?";
		//SQL文のソート
		String order=" order by no asc";
		
		try {
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql+condition+order);
			//プリペアードステートメントに学校コードをバインド
			statement.setString(1,school.getCd());
			//プリペアードステートメントにクラス番号をバインド
			statement.setString(2,classNum);
			//プリペアードステートメントに科目コードをバインド
			statement.setString(3,subjectCd);
			
			//プリペアードステートメントを実行
			rSet=statement.executeQuery();
			//リストへの格納処理を実行
			list=postFilter(rSet,school);
		}catch(Exception e) {
			throw e;
		}finally{
			//プリペアードステートメントを閉じる
			if(statement!=null) {
				try {
					statement.close();
				}catch(SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if(connection!=null) {
				try {
					connection.close();
				}catch(SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}
	
	// 保存（INSERT or UPDATE）
	public boolean save(Test test) throws Exception{
		
		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;
		try {
			Test old = get(test.getNo());
			if (old == null) {
				// INSERT
				statement = connection.prepareStatement(
					"insert into test(no, student_no, class_num, subject_cd, school_cd, point)"	
						+"values(?,?,?,?,?,?)"
				);
				statement.setInt(1, test.getNo());
				statement.setString(2, test.getStudent().getNo());
				statement.setString(3, test.getClassNum());
				statement.setString(4, test.getSubject().getCd());
				statement.setString(5, test.getSchool().getCd());
				statement.setInt(6, test.getPoint());
			} else {
				// UPDATE
				statement = connection.prepareStatement(
					"update test set student_no=?, class_num=?, subject_cd=?, school_cd=?, point=? where no = ?"
				);
				statement.setString(1, test.getStudent().getNo());
				statement.setString(2, test.getClassNum());
				statement.setString(3, test.getSubject().getCd());
				statement.setString(4, test.getSchool().getCd());
				statement.setInt(5, test.getPoint());
				statement.setInt(6, test.getNo());
			}
			
			count = statement.executeUpdate();
		}finally {
			if (statement != null) {
				try {statement.close();} catch (SQLException sqle) 
				{throw sqle;}
			}
			if (connection != null) {
				try { connection.close();} catch (SQLException sqle)
				{throw sqle;}
			}
		}
	return count > 0;
	}
	public List<Test> findAll(School school) throws Exception {//一覧表示

	    List<Test> list = new ArrayList<>();
	    Connection connection = getConnection();
	    PreparedStatement statement = null;

	    try {
	        statement = connection.prepareStatement(
	            "SELECT t.NO, t.CLASS_NUM, t.POINT, " +
	            "s.NO AS STUDENT_NO, s.NAME AS STUDENT_NAME, " +
	            "sub.CD AS SUBJECT_CD, sub.NAME AS SUBJECT_NAME, " +
	            "sc.CD AS SCHOOL_CD, sc.NAME AS SCHOOL_NAME " +
	            "FROM TEST t " +
	            "JOIN STUDENT s ON t.STUDENT_NO = s.NO " +
	            "JOIN SUBJECT sub ON t.SUBJECT_CD = sub.CD " +
	            "JOIN SCHOOL sc ON t.SCHOOL_CD = sc.CD " +
	            "WHERE t.SCHOOL_CD = ?" 
	        );

	        statement.setString(1, school.getCd());

	        ResultSet rSet = statement.executeQuery();

	        while (rSet.next()) {

	            Test test = new Test();

	            test.setNo(rSet.getInt("NO"));
	            test.setClassNum(rSet.getString("CLASS_NUM"));
	            test.setPoint(rSet.getInt("POINT"));

	            // Student
	            Student student = new Student();
	            student.setNo(rSet.getString("STUDENT_NO"));
	            student.setName(rSet.getString("STUDENT_NAME"));
	            test.setStudent(student);

	            // Subject
	            Subject subject = new Subject();
	            subject.setCd(rSet.getString("SUBJECT_CD"));
	            subject.setName(rSet.getString("SUBJECT_NAME"));
	            test.setSubject(subject);

	            // School
	            School sc = new School();
	            sc.setCd(rSet.getString("SCHOOL_CD"));
	            sc.setName(rSet.getString("SCHOOL_NAME"));
	            test.setSchool(sc);

	            list.add(test);
	        }

	    } finally {
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
	    }

	    return list;
	}
}