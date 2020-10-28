package com.eujoh.uoeapp.Common.LoginSignUp;

public class User {
    public String fullName, admNumber, email;

    public User(){

    }

    public User(String fullName, String admNumber, String email){
        this.fullName = fullName;
        this.admNumber = admNumber;
        this.email = email;
    }
}
