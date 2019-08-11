package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.generators.FileDeserializer;
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
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<>();
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
      String line = reader.readLine();
      while (line != null) {
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
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
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
  public void testContactCreation(ContactData newContact) throws Exception {
    if (newContact.getGroup() != null) {
      System.out.println("newContact.getGroup() " + newContact.getGroup());

      List<String> groupNameList = new ArrayList<>();
      for(GroupData groupData : app.db().groups() ){
        groupNameList.add(groupData.getName());
      }
      System.out.println("groupNameList=" + groupNameList);
      if (!groupNameList.contains(newContact.getGroup())) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(newContact.getGroup()));
      }
    }
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    app.contact().create(newContact);
    app.contact().returnToHomePage();
    assertEquals(app.contact().count(), before.size() + 1);
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(
            before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
