import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class Subscriptions {

  @EmbeddedId
  private KeySub id;

  @Column(name = "subscription_date")
  private Date subscriptionsDate;

  public Date getSubscriptionsDate() {
    return subscriptionsDate;
  }

  public void setSubscriptionsDate(Date subscriptionsDate) {
    this.subscriptionsDate = subscriptionsDate;
  }

  public KeySub getId() {
    return id;
  }

  public void setId(KeySub id) {
    this.id = id;
  }
}