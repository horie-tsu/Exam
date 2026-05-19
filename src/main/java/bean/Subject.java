package bean;

import java.io.Serializable;

public class Subject implements Serializable {

	/**
	 * 科目コード:String
	 */
	private String cd;
	
	/**
	 * 科目名:String
	 */
	private String name;
	
	/**
	 * 学校:School
	 */
	private School school;
	
	/**
	 * ゲッター・セッター
	 */
	
	private String imagePath;
	
	public String getCd() {
		return cd;
	}
	
	public void setCd(String cd) {
		this.cd = cd;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
