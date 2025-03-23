package org.example.antraproject1.controller;

import org.example.antraproject1.pojo.Teacher;
import org.example.antraproject1.repository.StudentRepository;
import org.example.antraproject1.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /**
     * Endpoint to create student and put student information in the request body
     * @param student student info
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentRepository.save(student));
    }

    /**
     * Endpoint to assign student to teacher through id
     * @param studentId student id
     * @param teacherId teacher id
     * @return ResponseEntity
     */
    @PostMapping("/{studentId}/assign-teacher/{teacherId}")
    public ResponseEntity<Student> assignTeacher(@PathVariable Long studentId, @PathVariable Long teacherId){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("student not found"));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()-> new RuntimeException("teacher not found"));
        student.getTeachers().add(teacher);
        return ResponseEntity.ok(studentRepository.save(student));
    }

    /**
     * Endpoint to get all student information as well as student-teacher assignment
     * @return list of student
     */
    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    /**
     * Endpoint to remove teacher-student assignment
     * @param studentId student id
     * @param teacherId teacher id
     * @return ResponseEntity
     */
    @DeleteMapping("/{studentId}/remove-teacher/{teacherId}")
    public ResponseEntity<?> removeTeacher(@PathVariable Long studentId, @PathVariable Long teacherId){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("student not found"));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()-> new RuntimeException("teacher not found"));
        if(student.getTeachers().contains(teacher)){
            student.getTeachers().remove(teacher);
            studentRepository.save(student);
            return ResponseEntity.ok("Teacher removed from student");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not assigned to student");
        }
    }


}
