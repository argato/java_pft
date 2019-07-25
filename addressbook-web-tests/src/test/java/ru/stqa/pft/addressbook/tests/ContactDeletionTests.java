package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

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
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);

    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
