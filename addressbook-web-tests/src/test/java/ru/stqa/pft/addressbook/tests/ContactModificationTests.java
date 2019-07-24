package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

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
    List<ContactData> before = app.contact().getContactList();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(before.get(index).getId()).withLastName("Lname1").withMname("Mname1").withNickname("nickname1")
            .withTitle("title1").withAddress("line1").withHomeNumber("999999").withFname("Fname1")
            .withEmail("modify@mmmmail.ru").withbDay("12").withbMonth("February").withbYear("2000");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}
