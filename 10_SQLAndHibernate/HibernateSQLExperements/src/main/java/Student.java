import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "students")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private int age;

  @Column(name = "registration_date")
  private Date registrationDate;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "subscriptions",
      joinColumns = {@JoinColumn(name = "student_id")},
      inverseJoinColumns = {@JoinColumn(name = "course_id")}
  )
  private List<Course>coursesFor;

//  @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "student")
//  private List<Subscriptions> subscriptions;

  public List<Course> getCoursesFor() {
    return coursesFor;
  }

  public void setCoursesFor(List<Course> coursesFor) {
    this.coursesFor = coursesFor;
  }
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

}
