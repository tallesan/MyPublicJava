import static java.lang.Integer.parseInt;

import java.util.LinkedList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class Main {

  public static void main(String[] args) {
    try {
      StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
          .configure("hibernate.cfg.xml").build();

      Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
      SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

      Session session = sessionFactory.openSession();
      //Способ 1
      SQLQuery query = session.createSQLQuery("SELECT count(*)id from courses");
      List idCourses = query.list();
      int id = parseInt(idCourses.get(0).toString());

      System.out.println("1-й Способ вывода =========================");
      for (int i = 1; i < id; i++) {
        Course course = session.get(Course.class, i);
        System.out.println(course.getName() + " - " + course.getStudentCount());
      }

      //способ 2
      //сразу добавляем в лист
      System.out.println("2-й Способ вывода =========================");
      Query query1 = session.createQuery("from Course");
      List<Course> courses = new LinkedList<Course>(query1.list());
      for (Course cs : courses) {
        System.out.println(cs.getName() + " - " + cs.getStudentCount());
      }
      //3-й способ через критерии
      System.out.println("3-й Способ вывода =========================");
      Criteria criteria = session.createCriteria(Course.class);
      List<Course> courses1 = new LinkedList<Course>(criteria.list());
      for (Course cs : courses1) {
        System.out.println(cs.getName() + " - " + cs.getStudentCount());
      }

      sessionFactory.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }
}
