package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    private Map<String,Student> studentMap= new HashMap<>();
    private Map<String,Teacher> teacherMap= new HashMap<>();
    private Map<String, List<String>> teacherToStudent= new HashMap<>();

    public boolean checkIfStudentExists(String student){
        if(studentMap.containsKey(student)) return true;
        return false;
    }

    public boolean checkIfTeacherExists(String teacher){
        if(teacherMap.containsKey(teacher)) return true;
        return false;
    }
    //1
    public void addStudent(Student student){
        String name = student.getName();
        studentMap.put(name,student);
    }
    //2
    public void addTeacher(Teacher teacher){
        String name = teacher.getName();
        teacherMap.put(name,teacher);
    }

    //3
    public void addStudentTeacherPair(String sname,String tname){
        if(teacherToStudent.containsKey(tname)){
            teacherToStudent.get(tname).add(sname);
        }
        else{
            teacherToStudent.put(tname,new ArrayList<>());
            teacherToStudent.get(tname).add(sname);
        }
    }

    //4
    public Student getStudentByName(String studentName){
        if(studentMap.containsKey(studentName)){
            return studentMap.get(studentName);
        }
        return null;
    }

    //5
    public Teacher getTeacherByName(String teacherName){
        if(teacherMap.containsKey(teacherName)){
            return teacherMap.get(teacherName);
        }
        return null;
    }

    //6
    public List<String> getStudentsByTeacherName(String teacherName){
        if(teacherToStudent.containsKey(teacherName)){
            return teacherToStudent.get(teacherName);
        }
        return new ArrayList<>();
    }
    //7
    public List<String> getAllStudents(){
        List<String> studentNames= new ArrayList<>();

        for(String student:studentMap.keySet()){
            studentNames.add(student);
        }
        return studentNames;
    }
    public void deleteStudentByName(String studentName){
        if(studentMap.containsKey(studentName)){
            studentMap.remove(studentName);
        }
    }
    public void deleteTeacherFromStudentTeacherPair(String teacherName){
        if(teacherToStudent.containsKey(teacherName)){
            teacherToStudent.remove(teacherName);
        }
    }
    //8
    public void deleteTeacherByName(String teacherName){
        //remove teacher from teachermap
        if(teacherMap.containsKey(teacherName)) {
            teacherMap.remove(teacherName);

            List<String> students = getStudentsByTeacherName(teacherName);
            //removes all students of that teacher from student map
            for (String student : students) {
                deleteStudentByName(student);
            }
            //delete the teacher from student teacher pair map
            deleteTeacherFromStudentTeacherPair(teacherName);
        }
    }

    //9
    public void deleteAllTeachers(){
        List<String> studentsLinkedToTeachers = new ArrayList<>();

        for(String teacherName : teacherMap.keySet()){
            studentsLinkedToTeachers.addAll(getStudentsByTeacherName(teacherName));
        }

        teacherToStudent.clear();
        teacherMap.clear();

        for(String student:studentsLinkedToTeachers){
            deleteStudentByName(student);
        }
    }

}