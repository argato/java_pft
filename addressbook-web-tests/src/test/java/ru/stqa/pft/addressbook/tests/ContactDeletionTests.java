package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePredication() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withLastName("Del").withMname("Del").withNickname("Del")
              .withTitle("title").withAddress("line1").withHomeNumber("999999").withFname("Del")
              .withEmail("Mod@mmmmail.ru").withbDay("11").withbMonth("February").withbYear("2000")
              .withPhoto(new File("src/test/resources/ptah.jpg")).withWorkNumber("+7 9999").withMobileNumber("78 55 55"));
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertEquals(app.contact().count(), before.size() - 1);
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
