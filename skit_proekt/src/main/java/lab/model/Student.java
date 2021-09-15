package lab.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Locale;
import java.util.Random;

@Data
@Entity
public class Student {
    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Student() {

    }

    public int RandomNumber() {
        return username.length() * 2 + password.length();
    }

    public String suggestNewUsername() {
        return String.format("%s%d%s", surname.substring(0, 1).toUpperCase(), RandomNumber(), name.toLowerCase());
    }
}
