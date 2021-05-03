package com.am.example.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
  @Id
  private String id;

  private String fname;
  private String lname;
  private String email;
  private String userid;
  private String status;
  private String baseurl;

  public User() {

  }

  public User(String fname, String lname, String email, String userid, String status, String baseurl) {
    this.fname = fname;
    this.lname = lname;
    this.email = email;
    this.status = status;
    this.userid = userid;
    this.baseurl = baseurl;
  }

  public String getId() {
    return id;
  }

  
  public String getFname() {
	return fname;
  }

  public void setFname(String fname) {
	this.fname = fname;
  }

  public String getLname() {
	return lname;
  }

  public void setLname(String lname) {
	this.lname = lname;
  }

  public String getEmail() {
	return email;
  }

  public void setEmail(String email) {
	this.email = email;
  }

  public String getUserid() {
	return userid;
  }

  public void setUserid(String userid) {
	this.userid = userid;
  }

  public String getStatus() {
	return status;
  }

  public void setStatus(String status) {
	this.status = status;
  }

  public String getBaseurl() {
	return baseurl;
  }

  public void setBaseurl(String baseurl) {
	this.baseurl = baseurl;
  }

  @Override
  public String toString() {
    return "Users [id=" + id + ", fname=" + fname + ", lname=" + lname + ", status=" + status + ", userid=" + userid+ ", email=" +email+", baseurl=" + baseurl+"]";
  }
}
