package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePredication() {
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().withLastName("Mod").withMname("Mod").withNickname("nickname")
              .withTitle("title").withAddress("line1").withHomeNumber("999999").withFname("Mod")
              .withEmail("Mod@mmmmail.ru").withbDay("11").withbMonth("February").withbYear("2000"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactModification() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData modificationContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modificationContact.getId()).withLastName("Lname" + modificationContact.getId())
            .withMname("Mname1").withNickname("nickname1")
            .withTitle("title1").withAddress("line1").withHomeNumber("999999").withFname("Fname1")
            .withEmail("modify@mmmmail.ru").withbDay("12").withbMonth("February").withbYear("2000");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modificationContact);
    before.add(contact);
    Assert.assertEquals(after, before);
  }
}
