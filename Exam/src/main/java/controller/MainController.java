package controller;

import service.UserService;
import utill.util;

public class MainController {
    UserService userService = new UserService();
    String menu = """
            0 - Exit
            1 - sign in
            2 - sign up
            """;
    public void mainController() throws Exception {
        while (true){
            System.out.println(menu);
            switch (util.intScanner.nextInt()){
                case 0 ->{return;}
                case 1->{
                    userService.signIn();
                }
                case 2 ->{
                    userService.signUp();
                }
                default -> System.out.println("Incorrect input");
            }
        }
    }
}
