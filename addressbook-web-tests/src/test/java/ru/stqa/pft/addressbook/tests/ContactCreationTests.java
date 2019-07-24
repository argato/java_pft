package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

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
    List<ContactData> before = app.contact().getContactList();
    app.contact().create(newContact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    newContact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(newContact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}
