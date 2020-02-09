package com.example.thiftmarts.Model;

 public class User {

  private String name;
  private String address;
  private String email;
  private String gender;
  private String password;

  public User(String name, String address, String email, String gender, String password) {
   this.name = name;
   this.address = address;
   this.email = email;
   this.gender = gender;
   this.password = password;


  }

  public String getName() {
   return name;
  }

  public String getAddress() {
   return address;
  }

  public String getEmail() {
   return email;
  }

  public String getGender() {
   return gender;
  }

  public String getPassword() {
   return password;
  }
 }

