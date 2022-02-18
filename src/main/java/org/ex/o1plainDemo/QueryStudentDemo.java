package org.ex.o1plainDemo;

import org.ex.o1plainDemo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            List<Student> theStudents = session
                    .createQuery("from Student")
                    .getResultList();

            System.out.println("\n\nAll students:");
            printStudents(theStudents);

            List<Student> theStudents1 = session
                    .createQuery("from Student s where s.firstName='Sasha'")
                    .getResultList();

            System.out.println("\n\nStudents with name Sasha:");
            printStudents(theStudents1);

            List<Student> theStudents2 = session
                    .createQuery("from Student s where s.firstName='Sasha'" +
                            " OR s.lastName='Jane'")
                    .getResultList();

            System.out.println("\n\nStudents with name Sasha or surname Jane");
            printStudents(theStudents2);

            List<Student> theStudents3 = session
                    .createQuery("from Student s where s.email LIKE '%email'")
                    .getResultList();

            System.out.println("\n\nStudents with email 'email'");
            printStudents(theStudents3);

            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    private static void printStudents(List<Student> theStudents) {
        for (Student s : theStudents) {
            System.out.println(s);
        }
    }
}
