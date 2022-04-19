package opu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class User {
   private String login;
   private String password;
   private LocalDateTime registrationDate;
   private SystemRole role;

   public User(final String login, final String password, final SystemRole role) {
      this.setLogin(login);
      this.setPassword(password);
      this.role = role;
      this.registrationDate = LocalDateTime.now().minusSeconds(((long) (Math.random() * 10000))).minusDays(((long) (Math.random() * 11)));
   }

   public String getLogin() {
      return login;
   }

   public void setLogin(final String login) {
      this.login = login.strip();
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(final String password) {
/*
      if (PasswordMaker.isCorrectPassword(password)) {
         this.password = password;
      }
      else
      {
         this.password = PasswordMaker.generatePassword();
      }
*/

      this.password = utilityPasswordMaker.isCorrectPassword(password) ? password : utilityPasswordMaker.generatePassword();
   }

   public LocalDateTime getRegistrationDate() {
      return registrationDate;
   }

   public SystemRole getRole() {
      return role;
   }

   public void setRole(final SystemRole role) {
      this.role = role;
   }

   @Override
   public String toString() {
      return "User{" +
              "login='" + login + '\'' +
              ", password='" + this.getPassword() + '\'' +
              ", registrationDate=" + registrationDate +
              ", role=" + role +
              '}';
   }
}
