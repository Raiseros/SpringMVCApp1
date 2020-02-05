package ru.holyav.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.holyav.springapp.dao.StudentDAO;
import ru.holyav.springapp.entity.Student;


import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    private StudentDAO studentDAO;

    @Override
    public List<Student> getStudents() {

        return studentDAO.getStudents();
    }

    @Override
    public void saveStudent(Student theStudent) {
        studentDAO.saveStudent(theStudent);
    }

    @Override
    public void updateStudent(Student theStudent) {
        studentDAO.updateStudent(theStudent);
    }

    @Override
    public Student getStudent(int theId) {
     return studentDAO.getStudent(theId);
    }

    @Override
    public void deleteStudent(int theId) {
        studentDAO.deleteStudent(theId);
    }
}
