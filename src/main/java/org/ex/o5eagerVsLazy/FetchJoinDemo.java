package org.ex.o5eagerVsLazy;

import org.ex.o5eagerVsLazy.entity.Course;
import org.ex.o5eagerVsLazy.entity.Instructor;
import org.ex.o5eagerVsLazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.OneToManyBi.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //start transaction
            session.beginTransaction();

            //get the instructor from db
            int id = 1;

            //option2: Hibernate query with HQL

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                                    + "JOIN FETCH i.courses "
                                    + "where i.id=:theInstructorId",
                            Instructor.class);

            //set parameter on query
            query.setParameter("theInstructorId", id);

            //execute query and get instructor
            Instructor instructor = query.getSingleResult();

            System.out.println("Instructor: " + instructor);

            session.getTransaction().commit();
            session.close();
            System.out.println("Session is closed!");

            for (Course c : instructor.getCourses()) {
                System.out.println("Course: " + c);
            }
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
