package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//*[@id='maintable']//tr[@name='entry']")).get(index).findElement(By.xpath(".//img[@title=\"Edit\"]")).click();
  }

  public void submitContactModification() {  click(By.xpath("(//input[@name='update'])[2]")); }

  public void deleteSelectedContacts() { click(By.xpath("//input[@value='Delete']"));}

  public void acceptDeletingContacts(){ wd.switchTo().alert().accept(); }

  public void waitDeletingFinished(){ wd.findElement(By.cssSelector("div.msgbox"));}

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData contactData) {
    initContactCreation();
    fillContactForm(contactData, true);
    submitContactCreation();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> groups = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//*[@id='maintable']//tr[@name='entry']"));
    for(WebElement element : elements){
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      ContactData group = new ContactData(id, firstname, lastname);
      System.out.println(group);
      groups.add(group);
    }
    return groups;
  }
}
