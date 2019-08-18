package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "middlename")
  private String mname;
  @Expose
  @Column(name = "lastname")
  private String lastname;
  @Expose
  @Column(name = "nickname")
  private String nickname;
  @Expose
  @Column(name = "title")
  private String title;
  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;
  @Expose
  @Column(name = "firstname")
  private String fname;
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homeNumber;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobileNumber;
  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workNumber;
  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;
  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email1;
  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email2;
  @Expose
  @Transient
  private String bDay;
  @Expose
  @Transient
  private String bMonth;
  @Expose
  @Transient
  private String bYear;
  @Expose
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
        joinColumns = @JoinColumn(name="id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();
  @Expose
  @Column(name = "address2")
  @Type(type = "text")
  private String secondaryAddress;
  @Expose
  @Column(name = "phone2")
  @Type(type = "text")
  private String secondaryHome;
  @Expose
  @Column(name = "notes")
  @Type(type = "text")
  private String secondaryNotes;
  @Expose
  @Transient
  private String allPhones;
  @Expose
  @Transient
  private String allEmails;
  @Expose
  @Column(name="photo")
  @Type(type = "text")
  private String photo;

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
    return new File(photo);
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData inGroup(GroupData group){
    groups.add(group);
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(mname, that.mname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(nickname, that.nickname) &&
            Objects.equals(title, that.title) &&
            Objects.equals(address, that.address) &&
            Objects.equals(fname, that.fname) &&
            Objects.equals(homeNumber, that.homeNumber) &&
            Objects.equals(mobileNumber, that.mobileNumber) &&
            Objects.equals(workNumber, that.workNumber) &&
            Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, mname, lastname, nickname, title, address, fname, homeNumber, mobileNumber, workNumber, email);
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
    this.photo = photo.getPath();
    return this;
  }

  public ContactData addGroup(GroupData group){
    this.groups.add(group);
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", mname='" + mname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", nickname='" + nickname + '\'' +
            ", title='" + title + '\'' +
            ", address='" + address + '\'' +
            ", fname='" + fname + '\'' +
            ", homeNumber='" + homeNumber + '\'' +
            ", mobileNumber='" + mobileNumber + '\'' +
            ", workNumber='" + workNumber + '\'' +
            ", email='" + email + '\'' +
            ", bDay='" + bDay + '\'' +
            ", bMonth='" + bMonth + '\'' +
            ", bYear='" + bYear + '\'' +
            ", groups=" + groups +
            '}';
  }

  public ContactData removeGroup(GroupData group) {
    this.groups.remove(group);
    return this;
  }
}
