package org.ex.o1plainDemo;

import org.ex.o1plainDemo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            int studentId = 1;

            // start transaction
            session.beginTransaction();

            //retrieve student
            System.out.println("/nGetting student with id: " + studentId);
            Student myStudent = session.get(Student.class, studentId);

            //System.out.println("deleting the student: "+myStudent);
            //session.delete(myStudent);

            //delete the student with id = 2
            session.createQuery("delete from Student where id=2").executeUpdate();

            //commit transaction
            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
