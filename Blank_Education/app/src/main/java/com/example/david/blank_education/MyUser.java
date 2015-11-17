package com.example.david.blank_education;

import cn.bmob.v3.BmobUser;

/**
 * Created by 123 on 2015/11/17.
 */
public class MyUser extends BmobUser {
    private String headImage;
    private int studentNumber;
    private String schoolnumber;

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getSchoolnumber(String schoolnumber)
    {
        return schoolnumber;
    }

    public void setSchoolnumber(String schoolnumber)
    {
        this.schoolnumber = schoolnumber;
    }

}
