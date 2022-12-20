package entity;

import exceptions.WrongPhoneNumberException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String phoneNumber;
    private Set<String> contacts = new HashSet<>();
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("[0-9]{12}")) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new WrongPhoneNumberException();
        }
    }

    public void setContacts(String newPhoneNumber) {
        contacts.add(newPhoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Set<String> getContacts() {
        return contacts;
    }
}
