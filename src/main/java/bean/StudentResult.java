package bean;

public class StudentResult {

    private String studentNo;     // 学生番号
    private String studentName;   // 氏名

    private String subjectName;   // 科目名
    private String subjectCd;     // 科目コード
    private int No;           // 回数
    private int point;            // 点数

    // getter / setter
    public String getStudentNo() {
        return studentNo;
    }
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCd() {
        return subjectCd;
    }
    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public int getNo() {
        return No;
    }
    public void setTestNo(int No) {
        this.No = No;
    }

    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
}
