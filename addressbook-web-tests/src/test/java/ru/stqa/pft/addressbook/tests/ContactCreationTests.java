package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    ContactData newContact = new ContactData().withLastName("LnameNew").withMname("Mname").withNickname("nickname")
            .withTitle("title").withAddress("line1\nline2").withHomeNumber("999999").withFname("Fname")
            .withEmail("qwerty@mmmmail.ru").withGroup("test1").withbDay("10").withbMonth("February").withbYear("2000");
    if (newContact.getGroup() != null) {
      app.goTo().groupPage();
      if (!app.group().isGroupExist(newContact.getGroup())) {
        app.group().create(new GroupData().withName(newContact.getGroup()));
      }
    }
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().create(newContact);
    app.contact().returnToHomePage();
    assertEquals(app.contact().count(), before.size() + 1);
    Contacts after = app.contact().all();

    assertThat(after, equalTo(
            before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
