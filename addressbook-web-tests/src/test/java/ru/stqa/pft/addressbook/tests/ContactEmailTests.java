package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

  @BeforeMethod
  public void ensurePredication() {
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().withLastName("For phone").withMname("ph").withNickname("nickname")
              .withTitle("title").withAddress("line1").withEmail("h1@mmmmail.ru").withEmail1("h2@mmmmail.ru")
              .withEmail2("h3@mmmmail.ru").withFname("fnamePh").withbDay("1").withbMonth("December").withbYear("1990"));
    }
  }
  @Test
  public void testContactEmail(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String  mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail1(), contact.getEmail2())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactEmailTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String address){
    return address.replaceAll("\\s", "");
  }
}
