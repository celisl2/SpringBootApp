package com.example.springbootapp.controller;

import com.example.springbootapp.exceptions.ResourceNotFoundException;
import com.example.springbootapp.model.Student;
import com.example.springbootapp.model.StudentRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentRepository repository;
    private static final String ALL_STUDENT_PATH = "/students";
    private static final String STUDENT_PATH = "/student";


    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping(ALL_STUDENT_PATH)
    public List<Student> getAllStudents(){
        return repository.findAll();
    }

    @PostMapping(STUDENT_PATH)
    public Student setNewStudent(@RequestBody Student newStudent){
        if(newStudent != null){
            if(StringUtils.isNotBlank(newStudent.getName()) && ObjectUtils.isNotEmpty(newStudent.getAge())){
                return repository.save(newStudent);
            }
        }
        return null;
    }

   @GetMapping(STUDENT_PATH + "/{id}")
    public Student getStudentById(@PathVariable Long id){
        if(ObjectUtils.isEmpty(id)){
            throw new ResourceNotFoundException(id);
        } else {
            return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        }
   }

   @PutMapping(STUDENT_PATH + "/{id}")
    public Student editStudent(@RequestBody Student newStudent, @PathVariable Long id){
        return repository.findById(id)
                .map(student -> {
                    student.setName(newStudent.getName());
                    student.setAge(newStudent.getAge());
                    return repository.save(student);
                }).orElseGet(() -> {
                    newStudent.setId(id);
                    return repository.save(newStudent);
                });
   }

   @DeleteMapping(STUDENT_PATH + "{id}")
    public void deleteStudent(@PathVariable Long id){
        repository.deleteById(id);
   }
}
