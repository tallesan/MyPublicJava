import java.util.Objects;

public class Account
{
    private long money;
    private String accNumber;
    private boolean isBlocked;


    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }



    public Account(long money, String accNumber,boolean isBlocked) {
        this.money = money;
        this.accNumber = accNumber;
        this.isBlocked = isBlocked;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return money == account.money &&
            accNumber.equals(account.accNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, accNumber);
    }

    @Override
    public String toString() {
        return "Account{" +
            "money=" + money +
            ", accNumber='" + accNumber + '\'' +
            '}';
    }
}
