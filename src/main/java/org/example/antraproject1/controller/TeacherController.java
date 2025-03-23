package org.example.antraproject1.controller;

import org.example.antraproject1.pojo.Teacher;
import org.example.antraproject1.repository.StudentRepository;
import org.example.antraproject1.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherRepository.save(teacher));
    }
    @GetMapping
    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }
}
