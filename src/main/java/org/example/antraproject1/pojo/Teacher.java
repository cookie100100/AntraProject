package org.example.antraproject1.pojo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;
    private String tname;
    @ManyToMany(mappedBy = "teachers")
    @JsonBackReference
    private Set<Student> students;


    public Long getId() {
        return tid;
    }

    public String getName() {
        return tname;
    }

    public void setName(String tname) {
        this.tname = tname;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
