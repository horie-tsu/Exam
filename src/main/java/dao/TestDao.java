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
	/*public Test get(int no) throws Exception{
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
		
	}*/
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
	
	public List<Test> filter(School school, String entYear, String classNum, String subjectCd, String no) throws Exception {
				//リストを初期化
				List<Test>list=new ArrayList<>();
				//コネクションを確立
				Connection connection=getConnection();
				//プリペアードステートメント
				PreparedStatement statement=null;
				//リザルトセット
				ResultSet rSet=null;
				
				//joinを使うのでbasesqlを使わず作成
				String sql = ("select t.STUDENT_NO,t.SUBJECT_CD,t.SCHOOL_CD,t.NO,t.POINT,t.CLASS_NUM,s.ent_year,s.name,sub.name as subject_name "
						+ "from test t join student s on t.student_no = s.no "
						+ " join subject sub on t.subject_cd = sub.cd "
						+ "where t.school_cd=? ");
				try {
					
					if (entYear != null && !entYear.equals("0"))
				        sql += " AND s.ent_year = ? ";

				    if (classNum != null && !classNum.equals("0"))
				        sql += " AND t.class_num = ? ";

				    if (subjectCd != null && !subjectCd.isEmpty())
				        sql += " AND t.subject_cd = ? ";

				    if (no != null && !no.equals("0"))
				        sql += " AND t.no = ? ";

				    sql += " ORDER BY t.no ASC";
				    
				    //System.out.println(sql);
				  //プリペアードステートメントにSQL文をセット
				    statement = connection.prepareStatement(sql);

				    int idx = 1;
				    statement.setString(idx++, school.getCd());

				    if (entYear != null && !entYear.equals("0"))
				    	statement.setInt(idx++, Integer.parseInt(entYear));

				    if (classNum != null && !classNum.equals("0"))
				    	statement.setString(idx++, classNum);

				    if (subjectCd != null && !subjectCd.isEmpty())
				    	statement.setString(idx++, subjectCd);

				    if (no != null && !no.equals("0"))
				    	statement.setInt(idx++, Integer.parseInt(no));

				    rSet = statement.executeQuery();

				    //System.out.println("テスト"+no);
				    while (rSet.next()) {
				        Test t = new Test();
				        t.setNo(rSet.getInt("no"));
				        t.setClassNum(rSet.getString("class_num"));
				        t.setPoint(rSet.getInt("point"));
				        
				        Subject sub = new Subject();
				        sub.setCd(rSet.getString("subject_cd"));
				        sub.setName(rSet.getString("subject_name"));
				        t.setSubject(sub);

				        Student st = new Student();
				        st.setNo(rSet.getString("student_no"));
				        st.setName(rSet.getString("name"));
				        st.setEntYear(rSet.getInt("ent_year"));
				        t.setStudent(st);
				        
				        School sc = new School();
				        sc.setCd(rSet.getString("school_cd"));
				        t.setSchool(sc);
				        
				        list.add(t);
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
		return list;
	}
	
	public Test get(
	        String studentNo,
	        String subjectCd,
	        String schoolCd,
	        int no
	) throws Exception {

	    // テストインスタンス
	    Test test = null;

	    // DB接続
	    Connection connection = getConnection();

	    PreparedStatement statement = null;

	    try {

	        // SQL
	        statement = connection.prepareStatement(
	            "select * from test " +
	            "where student_no=? " +
	            "and subject_cd=? " +
	            "and school_cd=? " +
	            "and no=?"
	        );

	        // バインド
	        statement.setString(1, studentNo);
	        statement.setString(2, subjectCd);
	        statement.setString(3, schoolCd);
	        statement.setInt(4, no);

	        // 実行
	        ResultSet rSet = statement.executeQuery();

	        // DAO
	        StudentDao studentDao = new StudentDao();
	        SubjectDao subjectDao = new SubjectDao();
	        SchoolDao schoolDao = new SchoolDao();

	        if (rSet.next()) {

	            test = new Test();

	            test.setNo(rSet.getInt("no"));
	            test.setClassNum(rSet.getString("class_num"));
	            test.setPoint(rSet.getInt("point"));

	            // 外部キー
	            test.setStudent(
	                studentDao.get(
	                    rSet.getString("student_no")
	                )
	            );

	            test.setSubject(
	                subjectDao.get(
	                    rSet.getString("subject_cd")
	                )
	            );

	            test.setSchool(
	                schoolDao.get(
	                    rSet.getString("school_cd")
	                )
	            );
	        }

	    } finally {

	        if (statement != null) {
	            statement.close();
	        }

	        if (connection != null) {
	            connection.close();
	        }
	    }

	    return test;
	}

	// 保存（INSERT or UPDATE）
	// 登録・更新
	public boolean save(List<Test> list) throws Exception {

	    Connection connection = getConnection();
	    PreparedStatement statement = null;

	    int count = 0;

	    try {

	        for (Test test : list) {

	            // 既存確認
	            Test old = get(
	                test.getStudent().getNo(),
	                test.getSubject().getCd(),
	                test.getSchool().getCd(),
	                test.getNo()
	            );

	            if (old == null) {

	                // INSERT
	                statement = connection.prepareStatement(
	                    "insert into test(" +
	                    "student_no, subject_cd, school_cd, no, class_num, point" +
	                    ") values(?,?,?,?,?,?)"
	                );

	                statement.setString(1, test.getStudent().getNo());
	                statement.setString(2, test.getSubject().getCd());
	                statement.setString(3, test.getSchool().getCd());
	                statement.setInt(4, test.getNo());
	                statement.setString(5, test.getClassNum());
	                statement.setInt(6, test.getPoint());

	            } else {

	                // UPDATE
	                statement = connection.prepareStatement(
	                    "update test set " +
	                    "class_num=?, point=? " +
	                    "where student_no=? " +
	                    "and subject_cd=? " +
	                    "and school_cd=? " +
	                    "and no=?"
	                );

	                statement.setString(1, test.getClassNum());
	                statement.setInt(2, test.getPoint());

	                statement.setString(3, test.getStudent().getNo());
	                statement.setString(4, test.getSubject().getCd());
	                statement.setString(5, test.getSchool().getCd());
	                statement.setInt(6, test.getNo());
	            }

	            count += statement.executeUpdate();

	            statement.close();
	        }

	    } finally {

	        if (statement != null) {
	            statement.close();
	        }

	        if (connection != null) {
	            connection.close();
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
	
	// 学生番号で成績一覧取得（成績一覧（学生）画面用）
	public List<Test> findByStudentNo(String studentNo) throws Exception {

	    List<Test> list = new ArrayList<>();
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet rSet = null;

	    try {
	        statement = connection.prepareStatement(
	            "SELECT t.NO, t.CLASS_NUM, t.POINT, " +
	            "sub.CD AS SUBJECT_CD, sub.NAME AS SUBJECT_NAME " +
	            "FROM TEST t " +
	            "JOIN SUBJECT sub ON t.SUBJECT_CD = sub.CD " +
	            "WHERE t.STUDENT_NO = ? " +
	            "ORDER BY sub.CD, t.NO"
	        );

	        statement.setString(1, studentNo);
	        rSet = statement.executeQuery();

	        while (rSet.next()) {
	            Test test = new Test();

	            test.setNo(rSet.getInt("NO"));
	            test.setClassNum(rSet.getString("CLASS_NUM"));
	            test.setPoint(rSet.getInt("POINT"));

	            Subject subject = new Subject();
	            subject.setCd(rSet.getString("SUBJECT_CD"));
	            subject.setName(rSet.getString("SUBJECT_NAME"));
	            test.setSubject(subject);

	            list.add(test);
	        }

	    } finally {
	        if (rSet != null) rSet.close();
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
	    }

	    return list;
	}

	//testRegist用保存メソッド
	public void updatePoints(List<Test> list) throws Exception {

	    String sql = "UPDATE test SET point=? " +
	                 "WHERE school_cd=? AND student_no=? AND subject_cd=? AND no=?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        for (Test t : list) {
	            ps.setInt(1, t.getPoint());
	            ps.setString(2, t.getSchool().getCd());
	            ps.setString(3, t.getStudent().getNo());
	            ps.setString(4, t.getSubject().getCd());
	            ps.setInt(5, t.getNo());

	            ps.executeUpdate();
	        }
	    }
	}
}