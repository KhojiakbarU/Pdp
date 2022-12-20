package service;

import controller.UserController;
import entity.History;
import entity.User;
import repository.HistoryRepository;
import repository.UserRepository;
import utill.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService {
    UserRepository userRepository = new UserRepository();
    HistoryRepository historyRepository = new HistoryRepository();

    public void signIn() throws Exception {
        System.out.println("Phone number: ");
        String number = util.strScanner.nextLine();
        User user = userRepository.getUser(number);
        if (user == null) {
            System.out.println("Doesn't exist");
            return;
        }
        new UserController().userController(user);
    }

    public void signUp() {
        User user = new User();
        System.out.println("Enter your phone number(only digits)");
        user.setPhoneNumber(util.strScanner.nextLine());
        if (userRepository.getUser(user.getPhoneNumber()) != null) {
            System.out.println("Uje bor");
            return;
        }
        System.out.println("Account created successfully");
        userRepository.addUser(user);
    }

    public void writeToAnotherUser(User user) throws Exception {
        List<User> allUsers = userRepository.getAllUsers();
        List<History> histories = historyRepository.getAllHistories();
        showContacts(user);
        System.out.println("Enter phone number: ");
        String phoneNumber = util.strScanner.nextLine();
        if (Objects.equals(phoneNumber, user.getPhoneNumber())) {
            System.out.println("You can't send message to yourself");
            return;
        }
        User secondUser = userRepository.getUser(phoneNumber);
        if (secondUser == null) {
            return;
        }
        user.setContacts(secondUser.getPhoneNumber());
        History history = new History();
        history.setFirstUser(user);
        history.setSecondUser(secondUser);
        History history1 = historyRepository.getHistory(user.getPhoneNumber(), secondUser.getPhoneNumber());
        if (history1 != null)
        if (Objects.equals(history.getFirstUser().getPhoneNumber(), history1.getFirstUser().getPhoneNumber()) &&
                Objects.equals(history.getSecondUser().getPhoneNumber(), history1.getSecondUser().getPhoneNumber())) {
            history = history1;
        }
        List<String> messages;
        if (history.getSms() == null) {
            messages = new ArrayList<>();
        } else {
            messages = history.getSms();
        }
        while (true) {
            System.out.println("Enter message");
            String message = util.strScanner.nextLine();
            if (Objects.equals(message, "0")) {
                break;
            }
            messages.add(message);
        }
        history.setSms(messages);
        history.setFirstUser(user);
        history.setSecondUser(secondUser);
        for (History perHistory : histories) {
            if (Objects.equals(perHistory, history)){
                historyRepository.writeHistory(histories);
                return;
            }
        }
        historyRepository.addHistory(history);
        userRepository.writeUsers(allUsers);
    }

    public void showMessages(User user) throws Exception {
        showContacts(user);
        System.out.println("Enter phone number");
        String phoneNumber = util.strScanner.nextLine();
        if (Objects.equals(phoneNumber, user.getPhoneNumber())) {
            System.out.println("Incorrect input!");
            return;
        }
        User secondUser = userRepository.getUser(phoneNumber);
        if (secondUser == null) {
            return;
        }
        History history = historyRepository.getHistory(user.getPhoneNumber(), secondUser.getPhoneNumber());
        if (history == null) {
            System.out.println("History is empty");
            return;
        }
        for (String message : history.getSms()) {
            System.out.println(message);
        }
    }

    public void showContacts(User user) {
        if (user.getContacts().size() != 0) {
            System.out.println("Your contacts");
        }
        for (String s : user.getContacts()) {
            System.out.println(s);
        }
    }
}
