package com.example.softwareengineeringtwo;

import android.text.BoringLayout;

import com.google.firebase.database.PropertyName;

import java.util.Date;

public class Citizens {


    private  String Address;
    private  String birthday;
    private  String contact_Number;
    private  String first_Name;
    private  String gender;
    private  String last_Name;
    private  String user_Role;
    private  String vaccination_Status;

    public Citizens() {
    }

    @PropertyName("Address")
    public String getAddress() {
        return Address;
    }
    @PropertyName("Address")
    public void setAddress(String address) {
        Address = address;
    }
    @PropertyName("Birthday")
    public String getbirthday() {
        return birthday;
    }
    @PropertyName("Birthday")
    public void setbirthday(String Birthday) {
        birthday = Birthday;
    }
    @PropertyName("Contact_No")
    public String getcontact_Number() {
        return contact_Number;
    }
    @PropertyName("Contact_No")
    public void setcontact_Number(String Contact_Number) {
        contact_Number = Contact_Number;
    }
    @PropertyName("First_Name")
    public String getfirst_Name() {
        return first_Name;
    }
    @PropertyName("First_Name")
    public void setfirst_Name(String First_Name) {
        first_Name = First_Name;
    }
    @PropertyName("Gender")
    public String getgender() {
        return gender;
    }
    @PropertyName("Gender")
    public void setgender(String Gender) {
        gender = Gender;
    }
    @PropertyName("Last_Name")
    public String getlast_Name() {
        return last_Name;
    }
    @PropertyName("Last_Name")
    public void setlast_Name(String Last_Name) {
        last_Name = Last_Name;
    }
    @PropertyName("User_Role")
    public String getuser_Role() {
        return user_Role;
    }
    @PropertyName("User_Role")
    public void setuser_Role(String User_Role) {
        user_Role = User_Role;
    }
    @PropertyName("Vaccination_Status")
    public String getvaccination_Status() {
        return vaccination_Status;
    }
    @PropertyName("Vaccination_Status")
    public void setvaccination_Status(String Vaccination_Status) {
        vaccination_Status = Vaccination_Status;
    }
}
