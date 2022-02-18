package org.ex.o1plainDemo;

import org.ex.o1plainDemo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Student student1 = new Student("John", "Doe", "emailJohn");
            Student student2 = new Student("Mary", "Jane", "email");
            Student student3 = new Student("Sasha", "Perep", "email");
            session.beginTransaction();
            session.save(student1);
            session.save(student2);
            session.save(student3);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
