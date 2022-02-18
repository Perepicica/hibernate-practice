package org.ex.o2oneToOneUni;

import org.ex.o2oneToOneUni.entity.Instructor;
import org.ex.o2oneToOneUni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //create the objects
            Instructor tempInst =
                    new Instructor("Sasha",
                            "Perep",
                            "sashika");
            InstructorDetail tempInstDet =
                    new InstructorDetail(
                            "some/youtube",
                            "dance");

            //associate the objects
            tempInst.setInstructorDetail(tempInstDet);
            //start transaction
            session.beginTransaction();

            //save the instructor
            session.save(tempInst);


            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
