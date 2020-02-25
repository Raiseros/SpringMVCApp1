package ru.holyav.springapp.dao;


import ru.holyav.springapp.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {

    public List<Student> getStudents();

    public void saveStudent(Student theStudent) throws SQLException;

    public void updateStudent(Student theStudent);

    public Student getStudent(int theId);

    public void deleteStudent(int theId);

    public Student findByUserName(String firstName);

    public String findByUserRole(String firstName);

}
