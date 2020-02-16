package ru.holyav.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.holyav.springapp.dao.RoleDao;
import ru.holyav.springapp.dao.StudentDAO;
import ru.holyav.springapp.dao.StudentDao2;
import ru.holyav.springapp.entity.Role;
import ru.holyav.springapp.entity.Student;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    private StudentDAO studentDAO;

   /* @Autowired
    private RoleDao roleDao;*/

  /*  @Autowired
    private StudentDao2 studentDao2;*/

    @Override
    public List<Student> getStudents() {

        return studentDAO.getStudents();
    }

    @Override
    public void saveStudent(Student theStudent) throws SQLException {
      studentDAO.saveStudent(theStudent);
      /*Set<Role> roles = new HashSet<>();
      roles.add(roleDao.getOne(1L));
      theStudent.setRoles(roles);*/
        /*Student student = new Student();
        theStudent.setFirstName(registration.getFirstName());
        theStudent.setLastName(registration.getLastName());
        theStudent.setEmail(registration.getEmail());
        theStudent.setPassword(passwordEncoder.encode(registration.getPassword()));
        theStudent.setRoles(Arrays.asList(new Role("ROLE_USER")));*/


     /* studentDao2.save(theStudent);*/
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
