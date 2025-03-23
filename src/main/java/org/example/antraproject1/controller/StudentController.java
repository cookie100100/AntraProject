package org.example.antraproject1.controller;

import org.example.antraproject1.pojo.Teacher;
import org.example.antraproject1.repository.StudentRepository;
import org.example.antraproject1.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.antraproject1.pojo.Student;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentRepository.save(student));
    }
    @PostMapping("/{studentId}/assign-teacher/{teacherId}")
    public ResponseEntity<Student> assignTeacher(@PathVariable Long studentId, @PathVariable Long teacherId){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("student not found"));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()-> new RuntimeException("teacher not found"));
        student.getTeachers().add(teacher);
        return ResponseEntity.ok(studentRepository.save(student));
    }
    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }


}
