package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToHomePage();
    ContactData newContact = new ContactData("Mname", "Lname", "nickname", "title", "line1\nline2", "999999",
            "1414141414", "Fname", "qwerty@mmmmail.ru", "10", "February", "2000", "test1");
    if (newContact.getGroup() != null) {
      app.getNavigationHelper().gotoGroupPage();
      if (!app.getGroupHelper().isGroupExist(newContact.getGroup())) {
        app.getGroupHelper().createGroup(new ru.stqa.pft.appmanager.model.GroupData(newContact.getGroup(), null, null));
      }
    }
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().createContact(newContact);
  }
}
