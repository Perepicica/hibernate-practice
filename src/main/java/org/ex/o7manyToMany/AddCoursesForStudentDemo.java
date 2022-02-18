package org.ex.o7manyToMany;

import org.ex.o7manyToMany.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForStudentDemo {
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

            int id = 1;
            Student student = session.get(Student.class, id);
            System.out.println(student.getCourses());
            Course course1 = new Course("course for Mary");
            Course course2 = new Course("sasha course");
            System.out.println("Courses of Mary: " + student.getCourses());
            student.addCourse(course1);
            student.addCourse(course2);
            session.save(course1);
            session.save(course2);

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
