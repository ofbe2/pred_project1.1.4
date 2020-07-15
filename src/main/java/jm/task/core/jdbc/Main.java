package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
       UserService userService = new UserServiceImpl();
       userService.createUsersTable();
       userService.saveUser("Oleg","Olegov", (byte) 29);
       userService.saveUser("Alesha","Alekseev", (byte) 20);
       userService.saveUser("Valek","Orlov", (byte) 17);
       userService.saveUser("Katya","Krasavina", (byte) 19);
       userService.getAllUsers();
       userService.cleanUsersTable();
       userService.dropUsersTable();
    }
}
