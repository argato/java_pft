package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePredication() {
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().withLastName("Del").withMname("Del").withNickname("nickDel")
              .withTitle("title").withAddress("line1").withHomeNumber("999999").withFname("Del")
              .withEmail("del@mmmmail.ru").withbDay("11").withbMonth("February").withbYear("2000"));
    }

  }

  @Test
  public void testContactDeletion() throws Exception {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData deletingContact = before.iterator().next();
    app.contact().delete(deletingContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletingContact);
    Assert.assertEquals(before, after);
  }
}
