package controller;

import entity.User;
import service.UserService;
import utill.util;

public class UserController {
    UserService userService = new UserService();
    String menu = """
            0 - Exit
            1 - write to another user
            2 - show messages
            """;
    public void userController(User user) throws Exception {
        while (true){
            System.out.println(menu);
            switch (util.intScanner.nextInt()){
                case 0->{
                    return;
                }
                case 1 ->{
                    userService.writeToAnotherUser(user);
                }
                case 2 ->{
                    userService.showMessages(user);
                }
                default -> System.out.println("Wrong option");
            }
        }
    }
}
