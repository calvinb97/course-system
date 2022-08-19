package student;

import enrolment.Enrolment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class StudentRepository {

    private SessionFactory sessionFactory;

    public StudentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        if(this.sessionFactory == null){
            System.out.println("Problem");
        }
    }

    public List<Student> findAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query<Student> query = session.createNamedQuery("Student_FindAll", Student.class);
        List<Student> result = query.getResultList();
        tx.commit();
        session.close();
        return result;
    }

    public Student create(Student student) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(student);
        tx.commit();
        session.close();
        return student;
    }

    public Student findById(int id) throws StudentNotFoundException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query<Student> query = session.createNamedQuery("Student_FindById", Student.class);
        query.setParameter("id", id);
        try {
            Student result = query.getSingleResult();
            // TODO resolve workaround for lazy loading
            for (Enrolment enrolment : result.getEnrolments()){
                enrolment.getCourse();
            }
            tx.commit();
            return result;
        } catch (NoResultException e){
            throw new StudentNotFoundException();
        } finally {
            session.close();
        }

    }
}
