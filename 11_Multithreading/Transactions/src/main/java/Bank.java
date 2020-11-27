import java.util.HashMap;
import java.util.Random;

public class Bank extends Exception {

  private static final long AMOUNT_CHECK = 50000; //порог проверки
  private final Random random = new Random();
  private HashMap<String, Account> accounts;

  public HashMap<String, Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(HashMap<String, Account> accounts) {
    this.accounts = accounts;
  }

  public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
      throws InterruptedException {
    Thread.sleep(1000);
    return random.nextBoolean();
  }

  /**
   * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
   * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
   * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
   * усмотрение)
   */

  public void transfer(String fromAccountNum, String toAccountNum, long amount)
      throws InterruptedException {
    if (amount >= AMOUNT_CHECK) {
      if (!isFraud(fromAccountNum, toAccountNum, amount)) {
        accounts.get(fromAccountNum).setBlocked(false);
        accounts.get(toAccountNum).setBlocked(false);
      }
    }
    if (!accounts.get(fromAccountNum).isBlocked()) {
      System.out.println("Перевод не возможен -" + fromAccountNum + " заблокирован");
    } else if (!accounts.get(toAccountNum).isBlocked()) {
      System.out.println("Перевод не возможен -" + toAccountNum + " заблокирован");
    } else {
      toTransfer(fromAccountNum, toAccountNum, amount);
    }
  }

  private void toTransfer(String fromAccountNum, String toAccountNum, long amount) {
    if (fromAccountNum.compareTo(toAccountNum) > 0) {
      synchronized (fromAccountNum) {
        synchronized (toAccountNum) {
          long moneyFrom = accounts.get(fromAccountNum).getMoney();
          long moneyTo = accounts.get(toAccountNum).getMoney();
          accounts.get(fromAccountNum).setMoney(moneyFrom - amount);
          accounts.get(toAccountNum).setMoney(moneyTo + amount);
        }
      }
    } else {
      synchronized (toAccountNum) {
        synchronized (fromAccountNum) {
          long moneyFrom = accounts.get(fromAccountNum).getMoney();
          long moneyTo = accounts.get(toAccountNum).getMoney();
          accounts.get(fromAccountNum).setMoney(moneyFrom - amount);
          accounts.get(toAccountNum).setMoney(moneyTo + amount);
        }
      }
    }
  }

  /**
   * TODO: реализовать метод. Возвращает остаток на счёте.
   *
   * @return
   */

  public long getBalance(String accountNum) {
    return accounts.get(accountNum).getMoney();
  }

}
