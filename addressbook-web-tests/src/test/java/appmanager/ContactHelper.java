package appmanager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd){ super(wd);}

  public void submitContactCreation() {
    click(By.xpath("//form[@name='theform']//input[@name='submit']"));
  }

  public void fillContactForm(ContactData contactData) {
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
    setCheckBox(By.name("bday"), contactData.getBirthdayDay());
    setCheckBox(By.name("bmonth"), contactData.getBirthdayMonth());
    type(By.name("byear"), contactData.getBirthdayYear());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }
}
