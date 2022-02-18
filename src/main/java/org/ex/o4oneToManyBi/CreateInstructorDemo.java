package org.ex.o4oneToManyBi;

import org.ex.o4oneToManyBi.entity.Course;
import org.ex.o4oneToManyBi.entity.Instructor;
import org.ex.o4oneToManyBi.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.OneToManyBi.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //create the objects
            Instructor tempInst =
                    new Instructor("Tom",
                            "James",
                            "sashika25");
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
            session.close();
            factory.close();
        }
    }
}
