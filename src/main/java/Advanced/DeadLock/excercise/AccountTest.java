package Advanced.DeadLock.excercise;

/**
 * 银行有一个账户。有两个储户分别向同一个账户存 3000 元，每次存 1000 ，存 3 次。每次存完打印账户余额。
 *
 * 分析：
 * 多线程：两个储户线程
 * 有共享数据，有线程安全问题
 * 需要同步机制
 *
 * @author Feue
 * @create 2021-05-29 15:12
 */
class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double amt) {
        if (amt > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance += amt;
            System.out.println(Thread.currentThread().getName()+"存钱成功。余额为："+balance);
        }
    }
}

class Customer extends Thread {
    private Account acct;

    public Customer(Account acct) {
        this.acct = acct;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acct.deposit(1000);
        }
    }
}

public class AccountTest {
    public static void main(String[] args) {
        Account acct = new Account(0);

        Customer c1 = new Customer(acct);
        Customer c2 = new Customer(acct);

        c1.setName("甲");
        c2.setName("乙");

        c1.start();
        c2.start();
    }
}
