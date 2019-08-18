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

public class AddingContactToGroupTest extends TestBase{

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
  public void testAddingToGroup(){
    Contacts contactsBefore = app.db().contacts();
    Contacts contacts = new Contacts(contactsBefore);
    Groups groups = app.db().groups();
    GroupData selectedGroup = groups.iterator().next();
    contacts.removeIf(contactData -> contactData.getGroups().contains(selectedGroup));
    if(contacts.size() == 0){

      app.goTo().homePage();
      app.contact().create(new ContactData().withLastName("Mod").withMname("Mod").withNickname("nickname")
              .withTitle("title").withAddress("line1").withHomeNumber("999999").withFname("Mod")
              .withEmail("Mod@mmmmail.ru").withbDay("11").withbMonth("February").withbYear("2000")
              .withPhoto(new File("src/test/resources/ptah.jpg")).withWorkNumber("+7 9999").withMobileNumber("78 55 55"));
      contacts = app.db().contacts();
      contacts.removeIf(contactData -> contactData.getGroups().contains(selectedGroup));
    }
    ContactData modifiedContact = contacts.iterator().next();
    app.goTo().homePage();
    app.contact().showContactsInGroup("[all]");
    assertFalse(selectedGroup.getContacts().contains(modifiedContact));
    assertFalse(modifiedContact.getGroups().contains(selectedGroup));
    app.contact().selectContactbyId(modifiedContact.getId());
    app.contact().selectGroup(selectedGroup.getName());
    app.contact().addToGroupSelectedContacts();
    logger.info(String.format("Contact %s added to group %s", modifiedContact, selectedGroup));

    Contacts updatedContacts = app.db().contacts();
    Groups updatedGroups = app.db().groups();

    assertThat(updatedContacts, equalTo(contactsBefore.withAddedGroup(modifiedContact, selectedGroup)));
    assertThat(updatedGroups, equalTo(groups.withAddedContact(selectedGroup, modifiedContact)));

    verifyContactListInUIByGroup(selectedGroup);
  }
}
