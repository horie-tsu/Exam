package bean;

import java.io.Serializable;
import java.time.LocalDate;

public class Attendance implements Serializable {
	private Student stu;
	private LocalDate day;
	private boolean attend;
	public Student getStu() {
		return stu;
	}
	public void setStu(Student stu) {
		this.stu = stu;
	}
	public LocalDate getDay() {
		return day;
	}
	public void setDay(LocalDate day) {
		this.day = day;
	}
	public boolean isAttend() {
		return attend;
	}
	public void setAttend(boolean attend) {
		this.attend = attend;
	}

}
