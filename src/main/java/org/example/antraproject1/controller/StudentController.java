package org.example.antraproject1.controller;

import org.example.antraproject1.pojo.Teacher;
import org.example.antraproject1.repository.StudentRepository;
import org.example.antraproject1.repository.TeacherRepository;
import org.example.antraproject1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.antraproject1.pojo.Student;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Endpoint to create student and put student information in the request body
     * @param student student info
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    /**
     * Endpoint to assign student to teacher through id
     * @param studentId student id
     * @param teacherId teacher id
     * @return ResponseEntity
     */
    @PostMapping("/{studentId}/assign-teacher/{teacherId}")
    public ResponseEntity<Student> assignTeacher(@PathVariable Long studentId, @PathVariable Long teacherId){
        return ResponseEntity.ok(studentService.assignTeacher(studentId, teacherId));
    }

    /**
     * Endpoint to get all student information as well as student-teacher assignment
     * @return list of student
     */
    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    /**
     * Endpoint to remove teacher-student assignment
     * @param studentId student id
     * @param teacherId teacher id
     * @return ResponseEntityg
     */
    @DeleteMapping("/{studentId}/remove-teacher/{teacherId}")
    public ResponseEntity<?> removeTeacher(@PathVariable Long studentId, @PathVariable Long teacherId){
        return ResponseEntity.ok(studentService.removeTeacher(studentId, teacherId));
    }


}
