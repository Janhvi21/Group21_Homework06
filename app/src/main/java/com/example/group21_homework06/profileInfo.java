package com.example.group21_homework06;

public class profileInfo {
    String first_name;
    String last_name;
    String student_ID;
    String Profile_Image;
    String Depart;
    int DepartID;

    @Override
    public String toString() {
        return "profileInfo{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", student_ID='" + student_ID + '\'' +
                ", Profile_Image='" + Profile_Image + '\'' +
                ", Depart='" + Depart + '\'' +
                ", DepartID=" + DepartID +
                '}';
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }

    public String getProfile_Image() {
        return Profile_Image;
    }

    public void setProfile_Image(String profile_Image) {
        Profile_Image = profile_Image;
    }

    public String getDepart() {
        return Depart;
    }

    public void setDepart(String depart) {
        Depart = depart;
    }

    public int getDepartID() {
        return DepartID;
    }

    public void setDepartID(int departID) {
        DepartID = departID;
    }

    public profileInfo(String first_name, String last_name, String student_ID, String profile_Image, String depart, int departID) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.student_ID = student_ID;
        Profile_Image = profile_Image;
        Depart = depart;
        DepartID = departID;
    }



    public profileInfo() {
    }
}
