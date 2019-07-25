package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

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
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withLastName("Lname" + modifiedContact.getId())
            .withMname("Mname1").withNickname("nickname1")
            .withTitle("title1").withAddress("line1").withHomeNumber("999999").withFname("Fname1")
            .withEmail("modify@mmmmail.ru").withbDay("12").withbMonth("February").withbYear("2000");
    app.contact().modify(contact);
    assertEquals(app.contact().count(), before.size());
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before.withReplaced(modifiedContact, contact)));
  }
}
