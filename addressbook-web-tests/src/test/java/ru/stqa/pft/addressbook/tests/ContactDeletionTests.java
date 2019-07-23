package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePredication(){
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Del", "Del", "Del", "title", "line1\nline2", "999999",
              "1414141414", "Fname", "qwerty@mmmmail.ru", "10", "February", "2000", null));
    }
  }

  @Test(enabled = false)
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 2);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().acceptDeletingContacts();
    app.getContactHelper().waitDeletingFinished();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 2);
    Assert.assertEquals(before, after);
  }
}
