package ru.holyav.springapp.dao;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.holyav.springapp.entity.Student;


import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private SessionFactory sessionFactory;





    @Override
    public List<Student> getStudents() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Student> theQuery = currentSession.createQuery("from Student", Student.class);
        List<Student> students = theQuery.getResultList();

        return students;
    }

    @Override
    public void saveStudent(Student theStudent) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theStudent);
    }

    @Override
    public Student getStudent(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Student student = currentSession.get(Student.class, theId);
        return student;
    }

    @Override
    public void deleteStudent(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery = currentSession.createQuery("delete from Student where id=:studentId");
        theQuery.setParameter("studentId", theId);
        theQuery.executeUpdate();
    }
}
