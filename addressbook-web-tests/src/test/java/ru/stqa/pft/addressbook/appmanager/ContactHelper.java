package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd){ super(wd);}

  public void submitContactCreation() {
    click(By.xpath("//form[@name='theform']//input[@name='submit']"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFname());
    type(By.name("middlename"), contactData.getMname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomeNumber());
    type(By.name("mobile"), contactData.getMobileNumber());
    type(By.name("email"), contactData.getEmail());
    type(By.name("home"), contactData.getHomeNumber());
    setComboBox(By.name("bday"), contactData.getBirthdayDay());
    setComboBox(By.name("bmonth"), contactData.getBirthdayMonth());
    type(By.name("byear"), contactData.getBirthdayYear());
    if(creation){
      if(contactData.getGroup() != null){
        setComboBox(By.name("new_group"), contactData.getGroup());
      }
    } else  {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() { click(By.linkText("add new"));  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void initContactModification() {  click(By.cssSelector("img[alt=\"Edit\"]")); }

  public void submitContactModification() {  click(By.xpath("(//input[@name='update'])[2]")); }

  public void deleteSelectedContacts() { click(By.xpath("//input[@value='Delete']"));}

  public void acceptDeletingContacts(){ wd.switchTo().alert().accept(); }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData contactData) {
    initContactCreation();
    fillContactForm(contactData, true);
    submitContactCreation();
  }
}
