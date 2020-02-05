package ru.holyav.springapp.service;

import ru.holyav.springapp.entity.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getStudents();

    public void saveStudent(Student theStudent);

    public void updateStudent(Student theStudent);

    public Student getStudent(int theId);

    public void deleteStudent(int theId);
}
