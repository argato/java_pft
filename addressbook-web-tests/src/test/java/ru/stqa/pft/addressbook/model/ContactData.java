package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

  private int id;
  private final String mname;
  private final String lastname;
  private final String nickname;
  private final String title;
  private final String address;
  private final String fname;
  private final String homeNumber;
  private final String mobileNumber;
  private final String email;
  private final String bDay;
  private final String bMonth;
  private final String bYear;
  private final String group;

  public ContactData(String middlename, String lastname, String nickname, String title, String address, String home, String mobile, String fname, String email, String bday, String bmonth, String byear, String group) {
    this.id = 0;
    this.mname = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.address = address;
    this.fname = fname;
    this.homeNumber = home;
    this.mobileNumber = mobile;
    this.email = email;
    this.bDay = bday;
    this.bMonth = bmonth;
    this.bYear = byear;
    this.group = group;
  }

  public ContactData(int id, String firstName, String lastName) {
    this.id = id;
    this.fname = firstName;
    this.lastname = lastName;
    this.email = null;
    this.address = null;
    this.mname = null;
    this.nickname = null;
    this.title = null;
    this.homeNumber = null;
    this.mobileNumber = null;
    this.bDay = null;
    this.bMonth = null;
    this.bYear = null;
    this.group = null;
  }

  public ContactData(int id, String middlename, String lastname, String nickname, String title, String address, String home, String mobile, String fname, String email, String bday, String bmonth, String byear, String group) {
    this.id = id;
    this.mname = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.address = address;
    this.fname = fname;
    this.homeNumber = home;
    this.mobileNumber = mobile;
    this.email = email;
    this.bDay = bday;
    this.bMonth = bmonth;
    this.bYear = byear;
    this.group = group;
  }

  public String getMname() {
    return mname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getAddress() {
    return address;
  }

  public String getFname() {
    return fname;
  }

  public String getHomeNumber() {
    return homeNumber;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public String getEmail() { return email; }

  public String getBirthdayYear() { return bYear; }

  public String getBirthdayMonth() { return bMonth; }

  public String getBirthdayDay() { return bDay; }

  public String getGroup() { return group; }

  public int getId() { return id; }

  public void setId(int id) { this.id = id; }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", lastname='" + lastname + '\'' +
            ", fname='" + fname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(fname, that.fname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastname, fname);
  }
}
