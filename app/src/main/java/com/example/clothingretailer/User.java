package com.example.clothingretailer;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

// Thông tin người dùng
public class User {
    private String username; // PRIMARY KEY
    private String firstname;
    private String lastname;
    private int gender;
    private String email;
    private String phone;
    private String birthday;
    private String address;
    private byte[] password;

    public User(String username, String firstname, String lastname, int gender,
                String email, String phone, String birthday, String address,
                byte[] password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.address = address;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public byte[] encodePassword(String pw){
        return Base64.encodeBase64(pw.getBytes(StandardCharsets.UTF_8));
    }

    public String decodePassword(byte[] epw){
        return new String(Base64.decodeBase64(epw), StandardCharsets.UTF_8);
    }

}
