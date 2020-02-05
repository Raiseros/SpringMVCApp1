package ru.holyav.springapp.dao;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.holyav.springapp.entity.Student;
import ru.holyav.springapp.mapper.StudentMapper;


import java.util.List;

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

        String sql = "INSERT INTO student (firstName, lastName, age) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, theStudent.getFirstName(), theStudent.getLastName(), theStudent.getAge());

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

        String sql = "DELETE FROM student WHERE id=?";
        jdbcTemplate.update(sql, theId);

    }
}
