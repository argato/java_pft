package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToHomePage();
    ContactData newContact = new ContactData("Mname", "LnameNew", "nickname", "title", "line1\nline2", "999999",
            "1414141414", "Fname", "qwerty@mmmmail.ru", "10", "February", "2000", "test1");
    if (newContact.getGroup() != null) {
      app.getNavigationHelper().gotoGroupPage();
      if (!app.getGroupHelper().isGroupExist(newContact.getGroup())) {
        app.getGroupHelper().createGroup(new GroupData(newContact.getGroup(), null, null));
      }
    }
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(newContact);
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    newContact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(newContact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}
