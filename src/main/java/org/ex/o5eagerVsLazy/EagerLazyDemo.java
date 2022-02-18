package org.ex.o5eagerVsLazy;

import org.ex.o5eagerVsLazy.entity.Course;
import org.ex.o5eagerVsLazy.entity.Instructor;
import org.ex.o5eagerVsLazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {
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
            Instructor instructor = session.get(Instructor.class, id);

            System.out.println("Instructor: " + instructor);

            session.getTransaction().commit();
            session.close();

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
