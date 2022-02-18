package org.ex.o1plainDemo;

import org.ex.o1plainDemo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Student student = new Student("Daffy", "Dog", "daffy@email");
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();

            //New code
            //find out the student's id:primary key
            System.out.println("Saved student generated id:" + student.getId());

            //get a new session, start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();
            //retrieve student
            Student myStudent = session.get(Student.class, student.getId());
            System.out.println(myStudent);
            //commit transaction
            session.getTransaction().commit();
            //commit
        } finally {
            factory.close();
        }
    }
}
