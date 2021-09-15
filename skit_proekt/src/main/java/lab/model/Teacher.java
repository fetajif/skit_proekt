package lab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Teacher {

    @Id
    private Long id;

    private String name;

    private String surname;

    public Teacher(String name, String surname) {
        this.id=(long)(Math.random()*1000);
        this.name = name;
        this.surname = surname;
    }

    public Teacher(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Teacher() {

    }

}
