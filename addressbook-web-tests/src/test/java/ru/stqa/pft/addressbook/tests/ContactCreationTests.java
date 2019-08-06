package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while(line != null){
      System.out.println(line);
      String[] split = line.split(";");
      list.add(new Object[]{new ContactData().withFname(split[0]).withLastName(split[1]).withNickname(split[2])
              .withMname(split[3]).withTitle(split[4]).withAddress(split[5]).withHomeNumber(split[6])
              .withEmail(split[7]).withGroup(split[8]).withbDay(split[9]).withbMonth(split[10].trim()).withbYear(split[11])
              .withPhoto(new File(split[12].trim()))});
      line = reader.readLine();
    }
    return list.iterator();
  }


  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData newContact) throws Exception {
    app.goTo().homePage();

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
