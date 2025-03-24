package org.example.antraproject1.service;

import org.example.antraproject1.pojo.Teacher;
import org.example.antraproject1.repository.StudentRepository;
import org.example.antraproject1.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.example.antraproject1.pojo.Student;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    public StudentService(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }
    public Student createStudent(Student student){
        return studentRepository.save(student);
    }
    public Student assignTeacher(Long studentId, Long teacherId){
        Student student = studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student not found"));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()->new RuntimeException("Teacher not found"));
        student.getTeachers().add(teacher);
        return studentRepository.save(student);
    }
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public Student removeTeacher(Long studentId, Long teacherId){
        Student student = studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student not found"));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()->new RuntimeException("Teacher not found"));
        if(student.getTeachers().contains(teacher)){
            student.getTeachers().remove(teacher);
            return studentRepository.save(student);
        }
        else{
            throw new RuntimeException("Teacher not assigned to student");
        }
    }
}
