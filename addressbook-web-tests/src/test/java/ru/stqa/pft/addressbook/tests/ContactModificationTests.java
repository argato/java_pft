package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.generators.FileDeserializer;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePredication() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withLastName("Mod").withMname("Mod").withNickname("nickname")
              .withTitle("title").withAddress("line1").withHomeNumber("999999").withFname("Mod")
              .withEmail("Mod@mmmmail.ru").withbDay("11").withbMonth("February").withbYear("2000")
              .withPhoto(new File("src/test/resources/ptah.jpg")).withWorkNumber("+7 9999").withMobileNumber("78 55 55"));
    }
    app.goTo().homePage();
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new GsonBuilder().registerTypeAdapter(File.class, new FileDeserializer()).create();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactModification(ContactData contact) throws Exception {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    System.out.println("modifiedContact=" + modifiedContact);
    ContactData newDataContact = new ContactData().withId(modifiedContact.getId()).withLastName(contact.getLastname())
            .withMname(contact.getMname()).withNickname(contact.getNickname())
            .withTitle(contact.getTitle()).withAddress(contact.getAddress()).withHomeNumber(contact.getHomeNumber())
            .withFname(contact.getFname()).withEmail(contact.getEmail()).withbDay(contact.getBirthdayDay())
            .withbMonth(contact.getBirthdayMonth()).withbYear(contact.getBirthdayYear())
            .withPhoto(contact.getPhoto()).withMobileNumber(contact.getMobileNumber()).withWorkNumber(contact.getWorkNumber());
    System.out.println("newDataContact=" + newDataContact);
    app.contact().modify(newDataContact);
    assertEquals(app.contact().count(), before.size());
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.withReplaced(modifiedContact, newDataContact)));
    verifyContactListInUI();
  }
}
