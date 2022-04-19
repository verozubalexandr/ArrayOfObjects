package opu;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Users {
    private final long DAYS_TO_CHANGE = 3;
    private User[] users;
    private int currentUserNumber;

    public Users(final int usersNumber) {
        this.users = new User[usersNumber];
        currentUserNumber = -1;
    }

    public User findOldestUser() {
        User user1 = users[0];

        for (final User user : users) {
            if (user.getRegistrationDate().isBefore(user1.getRegistrationDate())) user1 = user;
        }

        return user1;
    }

    public void addUser(final User user) {
        if (++currentUserNumber < users.length) {
            users[currentUserNumber] = user;
        }
    }

    private void delUserByNumber(final int number) {
        final Users tempUsers = new Users(currentUserNumber);
        tempUsers.currentUserNumber = currentUserNumber;

        int newIndex = 0;
        for (int i = 0; i <= currentUserNumber; i++) {
            if (i == number) continue;
            tempUsers.users[newIndex++] = users[i];
        }
        users = tempUsers.users;
        currentUserNumber--;
    }

    public void removeUser(final int number) {
        if (number < 0 || number >= currentUserNumber) {
            return;
        } else {
            delUserByNumber(number);
        }
    }

    public void removeUser(final String login) {
        int number = 0;
        for (final User user : users) {
            if (user.getLogin().equalsIgnoreCase(login.strip())) {
                delUserByNumber(number);
                return;
            }
            number++;
        }
    }

    //task 1
    public void checkAutoChange() {
        for (User user : users) {
            if (user.getRegistrationDate().isBefore(LocalDateTime.now().minusDays(this.DAYS_TO_CHANGE))) {
                user.setPassword(utilityPasswordMaker.generatePassword());
            }
        }
    }

    @Override
    public String toString() {
        String result = "Users: " + System.lineSeparator();

        for (final User user : users) {
            result += user + System.lineSeparator();
        }

        return result;
    }
}
