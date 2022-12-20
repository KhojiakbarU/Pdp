package repository;

import com.google.gson.reflect.TypeToken;
import entity.User;
import utill.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepository {
    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            users = util.gson.fromJson(new FileReader(util.userJsonUrl), new TypeToken<ArrayList<User>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return users == null ? new ArrayList<>() : users;
    }
    public User getUser(String number) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (Objects.equals(user.getPhoneNumber(), number)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        List<User> users = getAllUsers();
        users.add(user);
        writeUsers(users);
    }

    public void writeUsers(List<User> users) {
        new Thread(() -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(util.userJsonUrl));
                writer.write(util.gson.toJson(users));
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }



}
