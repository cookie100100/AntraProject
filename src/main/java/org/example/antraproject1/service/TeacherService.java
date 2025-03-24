package org.example.antraproject1.service;

import org.example.antraproject1.pojo.Teacher;
import org.example.antraproject1.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    public Teacher createTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }
    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }
}
