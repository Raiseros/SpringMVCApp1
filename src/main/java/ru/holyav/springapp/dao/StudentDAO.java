package ru.holyav.springapp.dao;

import ru.holyav.springapp.entity.Student;

import java.util.List;

public interface StudentDAO {

    public List<Student> getStudents();

    public void saveStudent(Student theStudent);

}
