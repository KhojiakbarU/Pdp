package entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class History {
    private List<String> sms = new ArrayList<>();
    private User firstUser;
    private User secondUser;
}
