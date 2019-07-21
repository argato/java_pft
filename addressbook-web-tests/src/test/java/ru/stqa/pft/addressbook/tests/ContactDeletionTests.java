package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Del", "Del", "Del", "title", "line1\nline2", "999999",
              "1414141414", "Fname", "qwerty@mmmmail.ru", "10", "February", "2000", null));
    }
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().acceptDeletingContacts();
    app.getContactHelper().waitDeletingFinished();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);


  }
}
