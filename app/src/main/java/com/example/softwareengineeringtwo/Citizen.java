package com.example.softwareengineeringtwo;

import android.text.BoringLayout;

import java.util.Date;

public class Citizen {

    String Id;
    String firstName;
    String lastName;
    String address;
    Integer phoneNum;
    Character gender;
    Integer age;
    Date birthday;
    String barangay;

    public Citizen(String id, String firstName, String lastName, String address, Integer phoneNum, Character gender, Integer age, Date birthday, String barangay) {

        this.address = address;
        this.phoneNum = phoneNum;
        this.gender = gender;
        this.age = age;
        this.birthday = birthday;
        this.barangay = barangay;
    }

    public Citizen(String id, String firstName, String lastName, Integer phoneNumber) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNumber;
    }


    public String getId() {
        return Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public Character getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBarangay() {
        return barangay;
    }
}
