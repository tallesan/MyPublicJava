import static java.lang.Integer.parseInt;

import java.util.LinkedList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

  public static void main(String[] args) {
    try {
      StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
          .configure("hibernate.cfg.xml").build();

      Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
      SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

      Session session = sessionFactory.openSession();

//      Выводим purchaselists
      List<LinkedPurchaseList> linkedPurchaseLists = new LinkedList<>();
      Criteria criteriaPL = session.createCriteria(Purchaselist.class);
      List<Purchaselist> purchase = new LinkedList<Purchaselist>(criteriaPL.list());


      for (Purchaselist cs : purchase) {
        String studentHQL =" FROM "  + Student.class.getSimpleName() +" WHERE name ='" + cs.getId().getName() + "'";
        String courseHQL =" FROM "  + Course.class.getSimpleName() +" WHERE name ='" + cs.getId().getCourse() + "'";
        Student student = (Student) session.createQuery(studentHQL).getSingleResult();
        Course course = (Course) session.createQuery(courseHQL).getSingleResult();
        LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
        linkedPurchaseList.setID(new LinkedPurchaseList.Key(student.getId(), course.getId()));
        linkedPurchaseLists.add(linkedPurchaseList);
//Сверяем правильность заполнения
        System.out.println(student.getId()+ " - " + student.getName());
        System.out.println(course.getId() + " - " + course.getName());
        System.out.println(cs.getId().getName() +" -\t " +cs.getId().getCourse() + " -\t " +cs.getPrice() + " - \t" + cs.getDateRegistr());

        session.getTransaction().begin();
        session.update(linkedPurchaseList);
        session.getTransaction().commit();
      }
      linkedPurchaseLists.forEach(System.out::println);

      sessionFactory.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }
}
