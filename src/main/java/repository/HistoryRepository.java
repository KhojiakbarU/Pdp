package repository;

import com.google.gson.reflect.TypeToken;
import entity.History;
import utill.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoryRepository {
    UserRepository userRepository = new UserRepository();
    public void addHistory(History history1) throws Exception {
        List<History> histories = getAllHistories();
        histories.add(history1);
        writeHistory(histories);
    }

    public void writeHistory(List<History> histories) {
        new Thread(() -> {
            try {  BufferedWriter writer = new BufferedWriter(new FileWriter(util.historyJsonUrl));
                writer.write(util.gson.toJson(histories));
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }}).start();
    }

    public History getHistory(String firstPhoneNumber, String secondPhoneNumber) throws Exception {
        List<History> histories = getAllHistories();
        for (History history : histories) {
            if (history.getFirstUser() == null || history.getSecondUser() == null){
                continue;
            }
            if (Objects.equals(history.getFirstUser(), userRepository.getUser(firstPhoneNumber)) &&
            Objects.equals(history.getSecondUser(), userRepository.getUser(secondPhoneNumber))){
                return history;
            }
        }
        return null;
    }
    public List<History> getAllHistories() throws Exception {
        ArrayList<History> histories = new ArrayList<>();
        try {
            histories = util.gson.fromJson(new FileReader(util.historyJsonUrl), new TypeToken<ArrayList<History>>() {
            }.getType());
        } catch (Exception e) {
            throw new Exception(e);
        }
        return histories == null ? new ArrayList<>() : histories;
    }
}
