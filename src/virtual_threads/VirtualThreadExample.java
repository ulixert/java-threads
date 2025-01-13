package virtual_threads;

import java.util.Random;

public class VirtualThreadExample {
    public static void main(String[] args) {
        // Example 1: Create Runnable. Create and start virtual thread
        Runnable runnable = () -> {
            long threadId = Thread.currentThread().threadId();

            Random random = new Random();

            for (int i = 0; i < 10; i++) {
                System.out.println(threadId + " index: " + i);
                sleep(random.nextInt(1000));  // Simulate some work
            }
        };

        Thread vThread1 = Thread.ofVirtual().start(runnable);

        // Example 2: Create but do not start virtual thread
        Thread vThreadUnstarted = Thread.ofVirtual().unstarted(runnable);
        vThreadUnstarted.start();

        // Example 3: How to join a virtual thread
        try {
            vThread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(long mills) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
