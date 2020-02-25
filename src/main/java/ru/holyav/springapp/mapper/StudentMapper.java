package ru.holyav.springapp.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.holyav.springapp.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {


    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setFirstName(resultSet.getString("firstName"));
        student.setLastName(resultSet.getString("lastName"));
        student.setAge(resultSet.getString("age"));
        student.setPassword(resultSet.getString("password"));
        return student;
    }

}
