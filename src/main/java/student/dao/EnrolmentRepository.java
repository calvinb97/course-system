package student.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import student.model.Enrolment;

public class EnrolmentRepository {

    private final SessionFactory sessionFactory;

    public EnrolmentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Enrolment save(Enrolment enrolment){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Enrolment merged = (Enrolment) session.merge(enrolment);
        tx.commit();
        session.close();
        return merged;
    }
}
