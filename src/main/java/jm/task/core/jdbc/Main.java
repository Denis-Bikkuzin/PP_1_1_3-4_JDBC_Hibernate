package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        List<User> userList = new ArrayList<>();
        userList.add(new User("Denis", "Bikkuzin", (byte) 26));
        userList.add(new User("Vadim", "Yapiev", (byte) 21));
        userList.add(new User("Oleg", "Barbashin", (byte) 50));
        userList.add(new User("Slava", "Starikov", (byte) 30));

        userService.createUsersTable();
        for (User user : userList) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с именем " + user.getName() + " добавлен в базу данных");
        }
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
