package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase{

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void managePage() {
    click(By.linkText("Manage"));
  }

  public void manageUsersPage() {
    click(By.linkText("Manage Users"));
  }

  public void userPageByUsername(String username) {
    click(By.linkText(username));
  }

  public void resetPassword() {click(By.xpath("//input[@value='Reset Password']"));}

  public void logout() {
    click(By.linkText("Logout"));
  }
}
