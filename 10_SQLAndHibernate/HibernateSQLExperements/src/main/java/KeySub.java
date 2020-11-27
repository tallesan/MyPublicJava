import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class KeySub implements Serializable {

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id",
      referencedColumnName = "id",
      nullable = false, insertable = false, updatable = false)
  private Student student;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id",
      referencedColumnName = "id",
      nullable = false, insertable = false, updatable = false)
  private Course course;

  public KeySub() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof KeySub)) {
      return false;
    }
    KeySub keySub = (KeySub) o;
    return course.equals(keySub.course) &&
        student.equals(keySub.student);
  }

  @Override
  public int hashCode() {
    return Objects.hash(course, student);
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }
}