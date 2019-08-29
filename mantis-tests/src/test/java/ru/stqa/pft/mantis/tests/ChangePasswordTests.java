package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() throws MessagingException {
    Users users = app.db().users();
    if (users.size() <= 1) {
      long now = System.currentTimeMillis();
      String email = String.format("user%s@localhost", now);
      String password = "password";
      String user = String.format("user%s", now);
      app.james().createUser(user, password);
      app.registration().start(user, email);
      List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
      String confirmationLink = findConfirmationLink(mailMessages, email);
      app.registration().finish(confirmationLink, password);
      logger.info(String.format("User %s password %s created.", user, password));
    }
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    String newPassword = "newPassword";
    app.session().login(app.getProperty("mantis.adminlogin"), app.getProperty("mantis.adminpassword"));
    app.goTo().managePage();
    app.goTo().manageUsersPage();
    Users users = app.db().users();
    users.removeIf(userData -> userData.getId()==1);
    UserData modifiedUser = users.iterator().next();
    app.james().deleteAllMail(modifiedUser.getUsername(), "password");
    app.goTo().userPageByUsername(modifiedUser.getUsername());
    app.goTo().resetPassword();
    app.goTo().logout();
    List<MailMessage> mailMessages = app.james().waitForMail(modifiedUser.getUsername(), "password", 60000);
    String confirmationLink = findConfirmationLink(mailMessages, modifiedUser.getEmail());
    app.registration().setNewPassword(confirmationLink, newPassword);
    logger.info(String.format("User %s password %s changed.", modifiedUser.getUsername(), newPassword));
    HttpSession session = app.newSession();
    assertTrue(session.login(modifiedUser.getUsername(), newPassword));
    assertTrue(session.isLoggedInAs(modifiedUser.getUsername()));
  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage =  mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex(). find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}
