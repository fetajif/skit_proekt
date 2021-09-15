package lab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Grade {
    @Id
    private Long id;

    private Character grade;

    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    public Grade(Character grade, Student student, Course course) {
        this.id = (long) (Math.random()*1000);
        this.grade = grade;
        this.student = student;
        this.course = course;
    }

    public Grade() {

    }

    public String getGradeExtended() {
        String result="";
        switch (grade) {
            case 'A':
                result+="Excellent -> 10/A";
                break;
            case 'B':
                result+="Very Good -> 9/B"; break;
            case 'C':
                result+="Good -> 8/C"; break;
            case 'D':
                result+="Okay -> 7/D"; break;
            case 'E':
                result+="Passed -> 6/E"; break;
            case 'F':
                result+="Failed -> 5/F"; break;
        }
        return result;
    }
}
