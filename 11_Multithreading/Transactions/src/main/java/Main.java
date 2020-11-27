import java.util.HashMap;
import java.util.Hashtable;

public class Main {

  public static void main(String[] args)  {

    final Bank bank = new Bank();
    bank.setAccounts(new HashMap<String, Account>());
    bank.getAccounts().put("11111", new Account(10000000, "111", true));
    bank.getAccounts().put("22222", new Account(10000000, "222", true));
    bank.getAccounts().put("33333", new Account(10000000, "333", true));
    bank.getAccounts().put("44444", new Account(10000000, "444", true));

    for (int j = 0; j < 20; j++) {
      new Thread(new Runnable() {
        @Override
        public void run() {
          for (int i = 0; i < 50000 ; i++) {
            try {
              bank.transfer("11111", "22222", i);
              bank.transfer("22222", "33333", i);
              bank.transfer("33333", "44444", i);
              bank.transfer("44444", "11111", i);
              System.out.println(bank.getBalance("11111"));
              System.out.println(bank.getBalance("22222"));
              System.out.println(bank.getBalance("33333"));
              System.out.println(bank.getBalance("44444"));
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }).start();

    }

  }
}
