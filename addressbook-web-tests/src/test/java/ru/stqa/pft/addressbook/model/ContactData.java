package ru.stqa.pft.appmanager.model;

public class ContactData {
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

  public ContactData(String middlename, String lastname, String nickname, String title, String address, String home, String mobile, String fname, String email, String bday, String bmonth, String byear) {
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
}
