package bean;

import java.io.Serializable;

public class ClassNum implements Serializable {

    // 学校コード
    private String schoolCd;

    // クラス番号
    private String classNum;

    // getter
    public String getSchoolCd() {
        return schoolCd;
    }

    // setter
    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }

    // getter
    public String getClassNum() {
        return classNum;
    }

    // setter
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
}