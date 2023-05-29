package test1;

public class FundsService {
    private double balance = 1000;

    double recharge(String username, double price) {
        System.out.println(username  + "提现" + price);
        balance += price;
        return balance;
    }

    double cashOut(String username, double price) {
        if (balance < price) {
            throw new RuntimeException("余额不足！");
        }
        System.out.println(username + "提现" + price);
        balance -= price;
        return balance;
    }

    double getBalance(String username) {
        return balance;
    }
}
