package com.eujoh.uoeapp.Common.LoginSignUp;

public class User {
    public String fullName, admNumber, email, phoneNo;

    public User(){

    }

    public User(String fullName, String admNumber, String email, String phoneNo){
        this.fullName = fullName;
        this.admNumber = admNumber;
        this.email = email;
        this.phoneNo = phoneNo;
    }
}
