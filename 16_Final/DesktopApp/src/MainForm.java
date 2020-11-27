import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainForm extends JPanel {

  private JPanel mainPanel;
  private JTextField firstName;
  private JLabel firstNameLabel;
  private JTextField lastName;
  private JLabel lastNameLabel;
  private JTextField phaserName;
  private JLabel phaserNameLabel;
  private JButton myButton;
  private JTextField fulName;
  private JButton expButton;

  private People people;

  public JPanel getMainPanel() {
    return mainPanel;
  }

  public MainForm() {

    myButton.addActionListener(new Action() {
      @Override
      public Object getValue(String key) {
        return null;
      }

      @Override
      public void putValue(String key, Object value) {

      }

      @Override
      public void setEnabled(boolean b) {

      }

      @Override
      public boolean isEnabled() {
        return false;
      }

      @Override
      public void addPropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void removePropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void actionPerformed(ActionEvent e) {
        if (firstName.getText().equals("") || lastName.getText().equals("") || phaserName.getText()
            .equals("")) {
          JOptionPane.showMessageDialog(null,
              "Не все поля заполены",
              "Ошибка", JOptionPane.WARNING_MESSAGE);
        } else {
          people = new People(firstName.getText(), lastName.getText(), phaserName.getText());
          visibleMain(false);
          fulName.setVisible(true);
          expButton.setVisible(true);
          fulName.setText(people.toString());
        }
      }
    });

    expButton.addActionListener(new Action() {
      @Override
      public Object getValue(String key) {
        return null;
      }

      @Override
      public void putValue(String key, Object value) {

      }

      @Override
      public void setEnabled(boolean b) {

      }

      @Override
      public boolean isEnabled() {
        return false;
      }

      @Override
      public void addPropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void removePropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void actionPerformed(ActionEvent e) {
        if (fulName.equals("")) {
          JOptionPane.showMessageDialog(null,
              "Поле не может быть пустым",
              "Ошибка", JOptionPane.WARNING_MESSAGE);
        } else {
          String[] data = fulName.getText().split(" ");
          if (data.length < 3) {
            JOptionPane.showMessageDialog(null,
                "Не правильный формат данных",
                "Ошибка", JOptionPane.WARNING_MESSAGE);
          } else {
            firstName.setText(data[0]);
            lastName.setText(data[1]);
            phaserName.setText(data[2]);
            visibleMain(true);
            fulName.setVisible(false);
            expButton.setVisible(false);
          }
        }
      }
    });
  }

  private void visibleMain(boolean visible) {
    firstName.setVisible(visible);
    lastName.setVisible(visible);
    phaserName.setVisible(visible);
    firstNameLabel.setVisible(visible);
    lastNameLabel.setVisible(visible);
    phaserNameLabel.setVisible(visible);
    myButton.setVisible(visible);

  }
}
