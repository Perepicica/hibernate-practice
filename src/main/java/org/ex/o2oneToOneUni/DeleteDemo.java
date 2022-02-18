package org.ex.o2oneToOneUni;

import org.ex.o2oneToOneUni.entity.Instructor;
import org.ex.o2oneToOneUni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
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

            //get the instructor by primary key
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            System.out.println("Found instructor: " + instructor);

            if (instructor != null) {
                System.out.println("Deleting...");
                session.delete(instructor);
            }

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
