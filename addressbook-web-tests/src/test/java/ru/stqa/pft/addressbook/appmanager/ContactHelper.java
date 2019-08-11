package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  private Contacts contactCache = null;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//form[@name='theform']//input[@name='submit']"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFname());
    type(By.name("middlename"), contactData.getMname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("title"), contactData.getTitle());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobileNumber());
    type(By.name("work"), contactData.getWorkNumber());
    type(By.name("email"), contactData.getEmail());
    type(By.name("home"), contactData.getHomeNumber());
    setComboBox(By.name("bday"), contactData.getBirthdayDay());
    setComboBox(By.name("bmonth"), contactData.getBirthdayMonth());
    type(By.name("byear"), contactData.getBirthdayYear());
    if (creation) {
      if (contactData.getGroup() != null) {
        setComboBox(By.name("new_group"), contactData.getGroup());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactbyId(int id) {
    wd.findElement(By.id(Integer.toString(id))).click();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", id))).click();
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void acceptDeletingContacts() {
    wd.switchTo().alert().accept();
  }

  public void waitDeletingFinished() {
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(ContactData contactData) {
    initContactCreation();
    fillContactForm(contactData, true);
    contactCache = null;
    submitContactCreation();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactbyId(contact.getId());
    deleteSelectedContacts();
    acceptDeletingContacts();
    contactCache = null;
    waitDeletingFinished();
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    if(contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement>  cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt( cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String emails = cells.get(4).getText();
      String phones = cells.get(5).getText();
      String address = cells.get(3).getText();
      contactCache.add(new ContactData().withId(id).withFname(firstname).withLastName(lastname)
              .withAllPhones(phones).withAllEmails(emails).withAddress(address));
    }

    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact){
    initContactModificationById(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email1 = wd.findElement(By.name("email2")).getAttribute("value");
    String email2 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String secondaryAddress = wd.findElement(By.name("address2")).getAttribute("value");
    String secondaryHome = wd.findElement(By.name("phone2")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFname(firstName).withLastName(lastName)
            .withHomeNumber(home).withMobileNumber(mobile).withWorkNumber(work)
            .withEmail(email).withEmail1(email1).withEmail2(email2)
            .withAddress(address).withSecondaryAddress(secondaryAddress).withSecondaryHome(secondaryHome);
  }

}
