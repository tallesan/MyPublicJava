import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchaselist")
public class Purchaselist {

  @EmbeddedId
  private PLKey id;

//  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//  @JoinColumn(name = "course_name",
//      referencedColumnName = "name",
//      nullable = false, insertable = false, updatable = false)
//  private Course course;
//
//  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//  @JoinColumn(name = "student_name",
//      referencedColumnName = "name",
//      nullable = false, insertable = false, updatable = false)
//  private Student student;

  private int price;

  @Column(name = "subscription_date")
  private Date dateRegistr;

  public PLKey getId() {
    return id;
  }

  public void setId(PLKey id) {
    this.id = id;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Date getDateRegistr() {
    return dateRegistr;
  }

  public void setDateRegistr(Date dateRegistr) {
    this.dateRegistr = dateRegistr;
  }

//  public Course getCourse() {
//    return course;
//  }
//
//  public void setCourse(Course course) {
//    this.course = course;
//  }
//
//  public Student getStudent() {
//    return student;
//  }
//
//  public void setStudent(Student student) {
//    this.student = student;
//  }
}
