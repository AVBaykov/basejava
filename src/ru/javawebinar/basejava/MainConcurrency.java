package ru.javawebinar.basejava;

public class MainConcurrency {
    private static int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();
        
        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", "
        + Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    increment();
                }
            });
            thread.start();
        }
        Thread.sleep(100);
        Thread.currentThread().join();
        System.out.println(counter);
    }

    private static synchronized void increment() {
        synchronized (LOCK) {
            counter++;
        }
    }
}
