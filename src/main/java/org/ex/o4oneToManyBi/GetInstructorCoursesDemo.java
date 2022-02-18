package org.ex.o4oneToManyBi;

import org.ex.o4oneToManyBi.entity.Course;
import org.ex.o4oneToManyBi.entity.Instructor;
import org.ex.o4oneToManyBi.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCoursesDemo {
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
            for (Course c : instructor.getCourses()) {
                System.out.println("Course: " + c);
            }
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
