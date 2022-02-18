package org.ex.o3oneToOneBi;

import org.ex.o3oneToOneBi.entity.InstructorDetail;
import org.ex.o3oneToOneBi.entity.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //start transaction
            session.beginTransaction();

            //get the instructor detail obj
            int id = 2;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            //print detail & instructor itself
            System.out.println("Instructor Detail: " + instructorDetail);
            System.out.println("Instructor: " + instructorDetail.getInstructor());

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}