package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    //1
    public void addStudent(Student student){
        repository.addStudent(student);
    }

    //2
    public void addTeacher(Teacher teacher){
        repository.addTeacher(teacher);
    }

    //3
    public void addStudentTeacherPair(String studentName,String teacherName){
        if(repository.checkIfStudentExists(studentName) && repository.checkIfTeacherExists(teacherName)){
            repository.addStudentTeacherPair(studentName,teacherName);
        }
    }

    //4
    public Student getStudentByName(String studentName){
        return repository.getStudentByName(studentName);
    }

    //5
    public Teacher getTeacherByName(String teacherName){
        return repository.getTeacherByName(teacherName);
    }

    //6
    public List<String> getStudentsByTeacherName(String teacherName){
        return repository.getStudentsByTeacherName(teacherName);
    }

    //7
    public List<String> getAllStudents(){
        return repository.getAllStudents();
    }

    //8
    public void deleteTeacherByName(String teacherName){
        repository.deleteTeacherByName(teacherName);
    }

    //9
    public void deleteAllTeachers(){
        repository.deleteAllTeachers();
    }
}