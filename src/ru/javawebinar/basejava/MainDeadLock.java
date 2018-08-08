package ru.javawebinar.basejava;

public class MainDeadLock {

    static class BankAccount {
        private String name;
        private int amount;

        public BankAccount(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }

        public void deposit(int amount) {
            this.amount += amount;
            System.out.println(String.format("%d was deposit on account %s", amount, name));
            System.out.println(String.format("%d on account %S", this.amount, name));
        }

        public void withdraw(int amount) {
            if (this.amount < amount) {
                System.out.printf("Not enough money on account %s for this operation", name);
            } else {
                this.amount -= amount;
                System.out.println(String.format("%d was withdrawn on account %s", amount, this.name));
                System.out.println(String.format("%d on account %S", this.amount, this.name));
            }
        }

        public static void transaction(BankAccount a, BankAccount b, int amount) {
            synchronized (a) {
                a.withdraw(amount);
                synchronized (b) {
                    b.deposit(amount);
                }
            }
        }
    }

    public static void main(String[] args) {
        BankAccount a = new BankAccount("A", 1000);
        BankAccount b = new BankAccount("B", 1500);

        Thread thread1 = new Thread(() -> BankAccount.transaction(a, b, 500));
        Thread thread2 = new Thread(() -> BankAccount.transaction(b, a, 200));

        thread1.start();
        thread2.start();
    }

}
