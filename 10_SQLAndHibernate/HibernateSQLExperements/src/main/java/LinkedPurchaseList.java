import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "linkedpurchaselist")
public class LinkedPurchaseList {
  @EmbeddedId
  private Key ID;

  public Key getID() {
    return ID;
  }

  public void setID(Key ID) {
    this.ID = ID;
  }

  public LinkedPurchaseList() {
  }

  @Override
  public String toString() {
    return "LinkedPurchaseList{" +
        "ID=" + ID +
        '}';
  }

  @Embeddable
  public static class Key implements Serializable {

    private static final long serialVersionUID = 89L;

    @Column(name = "student_id",nullable = false, insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id",nullable = false, insertable = false, updatable = false)
    private int courseId;

    public Key() {
    }

    public Key(int studentId, int courseId) {
      this.studentId = studentId;
      this.courseId = courseId;
    }

    public int getStudentId() {
      return studentId;
    }

    public void setStudentId(int studentId) {
      this.studentId = studentId;
    }

    public int getCourseId() {
      return courseId;
    }

    public void setCourseId(int courseId) {
      this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Key key = (Key) o;
      return studentId == key.studentId &&
          courseId == key.courseId;
    }

    @Override
    public int hashCode() {
      return Objects.hash(studentId, courseId);
    }

    @Override
    public String toString() {
      return "Key{" +
          "studentId=" + studentId +
          ", courseId=" + courseId +
          '}';
    }
  }
}