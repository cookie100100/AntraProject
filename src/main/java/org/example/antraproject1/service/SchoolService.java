package org.example.antraproject1.service;

import org.example.antraproject1.pojo.Student;
import org.example.antraproject1.pojo.Teacher;
import org.example.antraproject1.repository.StudentRepository;
import org.example.antraproject1.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SchoolService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Transactional
    public void createStudentWithTeachers(){
        //create a teacher
        Teacher teacher1 = new Teacher();
        teacher1.setName("Teacher1");
        teacherRepository.save(teacher1);

        Teacher teacher2 = new Teacher();
        teacher2.setName("Teacher2");
        teacherRepository.save(teacher2);

        //create a student
        Student student = new Student();
        student.setName("Student1");

        student.getTeachers().add(teacher1);
        student.getTeachers().add(teacher2);

        //save the student
        studentRepository.save(student);

        System.out.println("Student created with teachers");

    }
}
