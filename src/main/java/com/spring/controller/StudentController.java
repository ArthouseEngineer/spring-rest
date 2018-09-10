package com.spring.controller;

import com.spring.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    Map<Integer, Student> studentMap = new ConcurrentHashMap<>(); // TODO:: Добавить БД вместо мапы

    @RequestMapping(value = StudentURIConstants.DUMMY_STUDENT, method = RequestMethod.GET)
    public @ResponseBody
    Student getDummyStudent() {
        logger.info("Start get dummy student info");
        Student student = new Student();
        student.setId(1000);
        student.setName("Dummy");
        student.setCreatedDate(new Date());
        studentMap.put(1000, student);
        return student;
    }

    @RequestMapping(value = StudentURIConstants.GET_STUDENT, method = RequestMethod.GET)
    public @ResponseBody
    Student getStudentById(@PathVariable("id") int studentId) {
        logger.info("Start get student by id : " + studentId);
        return studentMap.get(studentId);
    }

    @RequestMapping(value = StudentURIConstants.GET_ALL_STUDENTS, method = RequestMethod.GET)
    public @ResponseBody
    List<Student> getAllStudents() {
        logger.info("Start get all students");
        final List<Student> students = new ArrayList<>();
        studentMap.forEach((k, v) -> students.add(studentMap.get(k)));
        return students;
    }

    @RequestMapping(value = StudentURIConstants.CREATE_STUDENT, method = RequestMethod.POST)
    public @ResponseBody
    Student createStudent(@RequestBody Student student) {
        logger.info("Start create Student.");
        student.setCreatedDate(new Date());
        studentMap.put(student.getId(), student);
        return student;
    }

    @RequestMapping(value = StudentURIConstants.DELETE_STUDENT, method = RequestMethod.POST)
    public @ResponseBody
    Student deleteStudent(@PathVariable("id") int studentId) {
        logger.info("Start delete student.");
        Student student = studentMap.get(studentId);
        studentMap.remove(studentId);
        return student;
    }
}
