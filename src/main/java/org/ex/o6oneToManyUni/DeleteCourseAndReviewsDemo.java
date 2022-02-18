package org.ex.o6oneToManyUni;

import org.ex.o6oneToManyUni.entity.Instructor;
import org.ex.o6oneToManyUni.entity.InstructorDetail;
import org.ex.o6oneToManyUni.entity.Review;
import org.ex.o6oneToManyUni.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviewsDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.OneToManyUni.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //start transaction
            session.beginTransaction();
            int id = 10;
            Course course = session.get(Course.class, id);

            System.out.println("Deleting the course...");
            session.delete(course);
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
