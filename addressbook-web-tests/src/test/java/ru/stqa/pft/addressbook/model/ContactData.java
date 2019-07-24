package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

  private int id = Integer.MAX_VALUE;
  private String mname;
  private String lastname;
  private String nickname;
  private String title;
  private String address;
  private String fname;
  private String homeNumber;
  private String mobileNumber;
  private String email;
  private String bDay;
  private String bMonth;
  private String bYear;
  private String group;

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

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withMname(String mname) {
    this.mname = mname;
    return this;
  }

  public ContactData withLastName(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withFname(String fname) {
    this.fname = fname;
    return this;
  }

  public ContactData withHomeNumber(String homeNumber) {
    this.homeNumber = homeNumber;
    return this;
  }

  public ContactData withMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withbDay(String bDay) {
    this.bDay = bDay;
    return this;
  }

  public ContactData withbMonth(String bMonth) {
    this.bMonth = bMonth;
    return this;
  }

  public ContactData withbYear(String bYear) {
    this.bYear = bYear;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

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
    return Objects.equals(lastname, that.lastname) &&
            Objects.equals(fname, that.fname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lastname, fname);
  }
}
