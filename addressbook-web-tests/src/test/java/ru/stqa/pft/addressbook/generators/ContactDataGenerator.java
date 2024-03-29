package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public  String file;

  @Parameter(names = "-d", description = "Data format")
  public  String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander (generator);
    try{
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();

  }

  private void run() throws IOException {
    List<ContactData> contacts = generatorContacts(count);
    if (format.equals("csv")) {
      save(contacts, new File(file));
    }
    else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));

    }
    else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    }
    else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting()
            .registerTypeAdapter(File.class, new FileSerializer())
            .excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try(Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try(Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    try(Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFname(), contact.getLastname(), contact.getNickname(),
                contact.getMname(), contact.getTitle(), contact.getAddress(), contact.getHomeNumber(),
                contact.getEmail(), contact.getGroups(), contact.getBirthdayDay(), contact.getBirthdayMonth(),
                contact.getBirthdayYear(), contact.getWorkNumber(), contact.getMobileNumber(),
                contact.getPhoto().getPath()));
      }
    }
  }

  private List<ContactData> generatorContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for(int i = 0; i < count; i++){
      contacts.add(new ContactData().withFname(String.format("First name %s", i)).withLastName(String.format("Last name %s", i))
                      .withNickname(String.format("Nickname %s", i)).withMname("Mname").withNickname("nickname")
                      .withTitle(String.format("title %s", i)).withAddress(String.format("address %s", i))
                      .withHomeNumber(String.format("+4888 88 %s", i)).withWorkNumber(String.format("+333 %s", i))
                      .withEmail("qwerty@mmmmail.ru").inGroup(new GroupData().withName("test1")).withMobileNumber(String.format("+4444 %s", i))
                      .withbDay("10").withbMonth("February").withbYear("2000").withPhoto(new File ("src/test/resources/ptah.jpg"))
              );
    }
    return contacts;
  }



}
