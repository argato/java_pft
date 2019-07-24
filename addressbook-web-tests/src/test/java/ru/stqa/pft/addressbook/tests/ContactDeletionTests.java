package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePredication(){
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData("Del", "Del", "Del", "title", "line1\nline2", "999999",
              "1414141414", "Fname", "qwerty@mmmmail.ru", "10", "February", "2000", null));
    }
  }

  @Test(enabled = false)
  public void testContactDeletion() throws Exception {
    app.goTo().homePage();
    List<ContactData> before = app.contact().getContactList();
    int index = before.size() - 2;
    app.goTo().homePage();
    app.contact().delete(index);
    List<ContactData> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }
}
