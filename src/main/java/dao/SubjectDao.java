package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {
	private String baseSql = "select * from student where school_cd=?";
	public Subject get(String cd) throws Exception{
		//
		Subject subject = new Subject();
		//データベースへのコネクションを確立
		Connection connection=getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		try {
			//プリペアードステートメントにSQL文をセット
			statement=connection.prepareStatement("select * from subject where cd=?");
			//プリペアードステートメントに学生番号をバインド
			statement.setString(1, cd);
			//プリペアードステートメントを実行
			ResultSet rSet=statement.executeQuery();
			
			if(rSet.next()) {
				//リザルトセットが存在している場合
				//学生インスタンスに検索結果をセット
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				
			}else {
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				subject=null;
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
		return subject;
		
	}
	public List<Subject> filter(School school)throws Exception{
		//リストを初期化
		List<Subject>list=new ArrayList<>();
		ResultSet rSet = null;
		try {
			//リザルトセットを全件走査
			while(rSet.next()) {
				//科目インスタンスの初期化
				Subject subject=new Subject();
				//インスタンスに検索結果をセット
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				
				//リストに追加
				list.add(subject);
				
			}
		}catch(SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}
		
	public boolean save(Subject subject)throws Exception{
		//コネクションを確立
		Connection connection=getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		//実行件数
		int count=0;
		try {
			//データベースから科目を取得
			Subject old=get(subject.getCd());
			if(old==null) {
				//科目が存在しなかった場合
				//プリペアードステートメントにINSERT文をセット
				statement=connection.prepareStatement("insert into subject(cd,name) values(?,?)");
				//プリペアードステートメントに値をバインド
				statement.setString(1, subject.getCd());
				statement.setString(2, subject.getName());
			}else {
				//科目が存在した場合
				//プリペアードステートメントにupdate文をセット
				statement=connection.prepareStatement("update subject set name=? where cd=?");
				//プリペアードステートメントに値をバインド
				statement.setString(1, subject.getCd());
				statement.setString(2, subject.getName());			}
			//プリペアードステートメントを実行
			count=statement.executeUpdate();
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
		
		if(count>0) {
			//実行回数が1件以上ある場合
			return true;
		}else {
			//実行回数が0件の場合
			return false;
		}
		
	}
}
