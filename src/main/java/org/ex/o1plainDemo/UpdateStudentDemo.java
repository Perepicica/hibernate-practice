package org.ex.o1plainDemo;

import org.ex.o1plainDemo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            int studentId = 1;

            //start transaction
            session.beginTransaction();

            //retrieve student
            System.out.println("/nGetting student with id: " + studentId);
            Student myStudent = session.get(Student.class, studentId);

            System.out.println("Updating student...");
            myStudent.setFirstName("Scooby");

            //commit transaction
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();

            //update email fo all students
            session.createQuery("update Student s set email='foo@' where s.firstName = 'Sasha'").executeUpdate();

            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
