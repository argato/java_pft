package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.appmanager.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Mname1", "Lname1", "nickname1", "title", "line1\nline2", "999999", "1414141414", "Fname1", "qwerty@mmmmail.ru", "10", "February", "2000"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
