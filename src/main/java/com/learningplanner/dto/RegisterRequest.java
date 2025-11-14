
package com.learningplanner.dto;
public class RegisterRequest { private String name; private String phoneNumber; private String password;
 public String getName(){return name;} public void setName(String n){this.name=n;}
 public String getPhoneNumber(){return phoneNumber;} public void setPhoneNumber(String p){this.phoneNumber=p;}
 public String getPassword(){return password;} public void setPassword(String pw){this.password=pw;}
}
