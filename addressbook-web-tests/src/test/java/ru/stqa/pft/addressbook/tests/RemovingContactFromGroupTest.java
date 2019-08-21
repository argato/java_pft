package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RemovingContactFromGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditionsForContact() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withLastName("Mod").withMname("Mod").withNickname("nickname")
              .withTitle("title").withAddress("line1").withHomeNumber("999999").withFname("Mod")
              .withEmail("Mod@mmmmail.ru").withbDay("11").withbMonth("February").withbYear("2000")
              .withPhoto(new File("src/test/resources/ptah.jpg")).withWorkNumber("+7 9999").withMobileNumber("78 55 55"));
    }
    app.goTo().homePage();
  }

  @BeforeMethod
  public void ensurePreconditionsForGroup() {
    if(app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test add"));
    }
  }

  @Test
  public void testDeletingFromGroup(){
    Contacts contacts = app.db().contacts();
    contacts.removeIf(contactData -> contactData.getGroups().size()==0);
    ContactData modifiedContact = contacts.iterator().next();
    ContactData contactBefore = app.db().contactById(modifiedContact.getId());
    GroupData selectedGroup = modifiedContact.getGroups().iterator().next();

    app.goTo().homePage();
    app.contact().showContactsInGroup(selectedGroup.getName());
    app.contact().selectContactbyId(modifiedContact.getId());
    app.contact().removeFromeGroup();
    logger.info(String.format("Contact %s removed from group %s", modifiedContact, selectedGroup));

    ContactData updatedContact = app.db().contactById(modifiedContact.getId());

    assertThat(updatedContact.getGroups(), equalTo(contactBefore.removeGroup(selectedGroup).getGroups()));

    verifyContactListInUIByGroup(selectedGroup);
  }

}
