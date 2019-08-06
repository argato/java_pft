package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

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
    save(contacts ,new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for(ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFname(), contact.getLastname(), contact.getNickname(),
              contact.getMname(),contact.getTitle(), contact.getAddress(), contact.getHomeNumber(),
              contact.getEmail(), contact.getGroup(), contact.getBirthdayDay(), contact.getBirthdayMonth(),
              contact.getBirthdayYear(), contact.getPhoto().getPath()));
    }
    writer.close();
  }

  private List<ContactData> generatorContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for(int i = 0; i < count; i++){
      contacts.add(new ContactData().withFname(String.format("First name %s", i)).withLastName(String.format("Last name %s", i))
                      .withNickname(String.format("Nickname %s", i)).withMname("Mname").withNickname("nickname")
                      .withTitle(String.format("title %s", i)).withAddress(String.format("address %s", i))
                      .withHomeNumber(String.format("+4888 88 %s", i))
                      .withEmail("qwerty@mmmmail.ru").withGroup("test1")
                      .withbDay("10").withbMonth("February").withbYear("2000").withPhoto(new File ("src/test/resources/ptah.jpg"))
              );
    }
    return contacts;
  }
}
