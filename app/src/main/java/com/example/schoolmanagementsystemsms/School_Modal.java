package com.example.schoolmanagementsystemsms;

public class School_Modal {

    String Uid, Email, Password, First_Name, Last_Name, Address, Zip_Postal_Code, Phone;

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
}
