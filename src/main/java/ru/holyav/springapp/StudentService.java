package ru.holyav.springapp;

import java.util.List;

public interface StudentService {

    public List<Student> getStudents();

    public void saveStudent(Student theStudent);
}
