package com.example.schoolmanagementsystemsms;

public class School_Modal {

    String Uid;
    String Email;
    String Password;
    String First_Name;
    String Last_Name;
    String Address;
    String Zip_Postal_Code;
    String Phone;
    String NAME;
    String ID;
    String DEPARTMENT;
    String CLASS;
    String Course_id;

    public School_Modal(String course_id, String course_Name) {
        Course_id = course_id;
        Course_Name = course_Name;
    }

    public String getCourse_id() {
        return Course_id;
    }

    public void setCourse_id(String course_id) {
        Course_id = course_id;
    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }

    String Course_Name;

    public School_Modal() { //Default Constructor For Firebase.

    }

    public School_Modal(String uid, String email, String password, String first_Name, String last_Name, String address, String zip_Postal_Code, String phone) {
        Uid = uid;
        Email = email;
        Password = password;
        First_Name = first_Name;
        Last_Name = last_Name;
        Address = address;
        Zip_Postal_Code = zip_Postal_Code;
        Phone = phone;
    }

    public School_Modal(String NAME, String ID, String DEPARTMENT, String CLASS) {
        this.NAME = NAME;
        this.ID = ID;
        this.DEPARTMENT = DEPARTMENT;
        this.CLASS = CLASS;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getZip_Postal_Code() {
        return Zip_Postal_Code;
    }

    public void setZip_Postal_Code(String zip_Postal_Code) {
        Zip_Postal_Code = zip_Postal_Code;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDEPARTMENT() {
        return DEPARTMENT;
    }

    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT = DEPARTMENT;
    }

    public String getCLASS() {
        return CLASS;
    }

    public void setCLASS(String CLASS) {
        this.CLASS = CLASS;
    }

}
