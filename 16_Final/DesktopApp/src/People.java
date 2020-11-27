public class People {
  private String firstName;
  private String lastName;
  private String phaserName;

  public People() {
  }

  public People(String firstName, String lastName, String phaserName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phaserName = phaserName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhaserName() {
    return phaserName;
  }

  public void setPhaserName(String phaserName) {
    this.phaserName = phaserName;
  }

  @Override
  public String toString() {
    return "" + firstName + " " + lastName + " " + phaserName;
  }
}
