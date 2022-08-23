package course.dao;

import course.exception.CourseNotFoundException;
import course.model.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class CourseRepository {

    private SessionFactory sessionFactory;

    public CourseRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Course> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query<Course> query = session.createNamedQuery("Course_FindAll", Course.class);
        List<Course> result = query.getResultList();
        tx.commit();
        session.close();
        return result;
    }

    public Course findById(int id) throws CourseNotFoundException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query<Course> query = session.createNamedQuery("Course_FindById", Course.class);
        query.setParameter("id", id);
        try {
            Course result = query.getSingleResult();
            tx.commit();
            return result;
        } catch (NoResultException e) {
            throw new CourseNotFoundException();
        } finally {
            session.close();
        }
    }
}
