package org.ex.o7manyToMany;

import org.ex.o7manyToMany.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.ManyToMany.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //start transaction
            session.beginTransaction();

            Course course = new Course("someCourse");
            System.out.println("\n Saving the course...");
            session.save(course);
            System.out.println("Saved the course: " + course);

            Student student1 = new Student("Ann", "Polsk", "polsk@");
            Student student2 = new Student("Bob", "Marley", "high@");
            course.addStudent(student1);
            course.addStudent(student2);

            System.out.println("\nSaving students...");
            session.save(student1);
            session.save(student2);
            System.out.println("saved 2 students");

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
