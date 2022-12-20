package utill;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Scanner;

public interface util {
    Gson gson=new GsonBuilder().setPrettyPrinting().create();
    Scanner strScanner=new Scanner(System.in);
    Scanner intScanner=new Scanner(System.in);
    String userJsonUrl="src/main/resources/users.json";
    String historyJsonUrl = "src/main/resources/history.json";
}
