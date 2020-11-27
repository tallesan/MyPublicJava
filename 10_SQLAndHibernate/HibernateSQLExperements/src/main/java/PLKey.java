import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PLKey implements Serializable {

  public PLKey() {
  }
  private static final long serialVersionUID = 2472129826645489974L;

  public PLKey(String name, String course) {
    this.name = name;
    this.course = course;
  }

  @Column(name = "student_name")
  private String name;

  @Column(name = "course_name")
  private String course;


  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PLKey plKey = (PLKey) o;
    return name.equals(plKey.name) &&
        course.equals(plKey.course);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, course);
  }
}