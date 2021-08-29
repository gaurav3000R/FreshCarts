package com.example.freshcarts;

public class storing_data {

    String fullname,username,emailid,password,phonenumber;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public storing_data(String fullname_s, String email_s, String password_s, String phonenumber_s) {
    }

    public storing_data(String fullname, String username, String emailid, String password, String phonenumber) {
        this.fullname = fullname;
        this.username = username;
        this.emailid = emailid;
        this.password = password;
        this.phonenumber = phonenumber;
    }
}
