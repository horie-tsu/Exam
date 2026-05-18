package bean;

import java.io.Serializable;

public class AttendScore implements Serializable {
	    private Student student;
	    private double rate;

	    public Student getStudent() {
	        return student;
	    }
	    public void setStudent(Student student) {
	        this.student = student;
	    }
	    public double getRate() {
	        return rate;
	    }
	    public void setRate(double rate) {
	        this.rate = rate;
	    }
}
