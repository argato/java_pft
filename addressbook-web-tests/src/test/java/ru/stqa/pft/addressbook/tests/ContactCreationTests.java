package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.appmanager.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Mname", "Lname", "nickname", "title", "line1\nline2", "999999", "1414141414", "Fname", "qwerty@mmmmail.ru", "10", "February", "2000"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();

  }
}
