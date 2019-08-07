package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactData {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  @Expose
  private String mname;
  @Expose
  private String lastname;
  @Expose
  private String nickname;
  @Expose
  private String title;
  @Expose
  private String address;
  @Expose
  private String fname;
  @Expose
  private String homeNumber;
  @Expose
  private String mobileNumber;
  @Expose
  private String workNumber;
  @Expose
  private String email;
  @Expose
  private String email1;
  @Expose
  private String email2;
  @Expose
  private String bDay;
  @Expose
  private String bMonth;
  @Expose
  private String bYear;
  @Expose
  private String group;
  @Expose
  private String secondaryAddress;
  @Expose
  private String secondaryHome;
  @Expose
  private String secondaryNotes;
  @Expose
  private String allPhones;
  @Expose
  private String allEmails;
  @Expose
  private File photo;

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

  public String getEmail() {
    return email;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getBirthdayYear() {
    return bYear;
  }

  public String getBirthdayMonth() {
    return bMonth;
  }

  public String getBirthdayDay() {
    return bDay;
  }

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

  public String getWorkNumber() {
    return workNumber;
  }

  public String getSecondaryAddress() {
    return secondaryAddress;
  }

  public String getSecondaryHome() {
    return secondaryHome;
  }

  public String getSecondaryNotes() {
    return secondaryNotes;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public File getPhoto() {
    return photo;
  }

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

  public ContactData withEmail1(String email) {
    this.email1 = email;
    return this;
  }

  public ContactData withEmail2(String email) {
    this.email2 = email;
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

  public ContactData withWorkNumber(String workNumber) {
    this.workNumber = workNumber;
    return this;
  }

  public ContactData withSecondaryAddress(String secondaryAddress) {
    this.secondaryAddress = secondaryAddress;
    return this;
  }

  public ContactData withSecondaryHome(String secondaryHome) {
    this.secondaryHome = secondaryHome;
    return this;
  }

  public ContactData withSecondaryNotes(String secondaryNotes) {
    this.secondaryNotes = secondaryNotes;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
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
    return id == that.id &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(fname, that.fname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastname, fname);
  }
}
