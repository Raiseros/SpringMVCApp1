package ru.holyav.springapp.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.holyav.springapp.entity.Role;
import ru.holyav.springapp.entity.Student;
import ru.holyav.springapp.mapper.RoleMapper;
import ru.holyav.springapp.mapper.StudentMapper;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class StudentDAOImpl implements StudentDAO {


    public final JdbcTemplate jdbcTemplate;


    @Autowired
    public StudentDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Student> getStudents() {

        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new StudentMapper());

    }

    @Override
    public void saveStudent(Student theStudent) {
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        String str = theStudent.getFirstName();

        if (str.equalsIgnoreCase("ADMIN")) {
            role.setRoleName("ADMIN");
            roles.add(role);
            theStudent.setRoles(roles);
        } else {
            role.setRoleName("STUDENT");
            roles.add(role);
            theStudent.setRoles(roles);
        }


        String sqlCheckRole = "SELECT * FROM roles WHERE roleName=?";

        List<Role> CheckRoles = jdbcTemplate.query(sqlCheckRole, new RoleMapper(), new Object[]{role.getRoleName()});
        if (CheckRoles.size() == 0) {
            String sql = "INSERT INTO roles (roleName) VALUE (?)";
            jdbcTemplate.update(sql, role.getRoleName());
        }


        String sqlStudent = "INSERT INTO student (firstName, lastName, age, password) VALUES (?, ?, ?, ?)";

        String sqlStudentRoles = "INSERT INTO student_roles (student_id, role_id) VALUES (?, ?)";


        jdbcTemplate.update(sqlStudent, theStudent.getFirstName(), theStudent.getLastName(),
                theStudent.getAge(), theStudent.getPassword());


        String sqlIdStudent = "SELECT * FROM student WHERE firstName=? AND lastName=?";
        String sqlIdRoleName = "SELECT * FROM roles WHERE roleName=?";


        SqlRowSet idStudent = jdbcTemplate.queryForRowSet(sqlIdStudent, new Object[]{theStudent.getFirstName(),
                theStudent.getLastName()});

        Role idRole = jdbcTemplate.queryForObject(sqlIdRoleName, new RoleMapper(), new Object[]{role.getRoleName()});

        while (idStudent.next()) {
            Long fieldIdStudent = idStudent.getLong("id");
            jdbcTemplate.update(sqlStudentRoles, fieldIdStudent, idRole.getId());
        }


    }


    @Override
    public void updateStudent(Student theStudent) {

        String sql = "UPDATE student SET firstName=?, lastName=?, age=? WHERE id=?";
        jdbcTemplate.update(sql, theStudent.getFirstName(), theStudent.getLastName(), theStudent.getAge(),
                theStudent.getId());

    }


    @Override
    public Student getStudent(int theId) {

        String sql = "SELECT * FROM student WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new StudentMapper(), theId);
    }

    @Override
    public void deleteStudent(int theId) {

        String sql = "DELETE student, student_roles FROM student, student_roles" +
                " WHERE student.id=student_roles.student_id" +
                " AND student.id=?";
        jdbcTemplate.update(sql, theId);

    }

    @Override
    public Student findByUserName(String firstName) {
        String sql = "SELECT * FROM student WHERE firstName=?";
        return jdbcTemplate.queryForObject(sql, new StudentMapper(), firstName);
    }

    @Override
    public String findByUserRole(String firstName) {
        String sql = "SELECT student.id, student_roles.student_id, student_roles.role_id, roles.id, roles.roleName" +
                "  FROM student, student_roles, roles " +
                " WHERE student.id=student_roles.student_id AND student_roles.role_id=roles.id" +
                " AND student.firstName=?";
        SqlRowSet idTables = jdbcTemplate.queryForRowSet(sql, firstName);
        String userRole = null;
        while (idTables.next()) {
            userRole = idTables.getString("roleName");
        }
        return userRole;

    }
}
