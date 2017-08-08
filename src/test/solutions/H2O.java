package test.solutions;

//import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class H2O {
    private int countH, countO;
    private Lock lock;
    private Condition condH, condO;
    private int hToRelease, oToRelease;

    public H2O() {
        countH = 0;
        countO = 0;
        lock = new ReentrantLock();
        condH = lock.newCondition();
        condO = lock.newCondition();
    }

    public void h() throws InterruptedException {
        lock.lock();
        try {
            countH++;
            if (countH>=2 && countO>=1) {
                System.out.println("2 H and 1 O consumed in H()");
                countH-=2;
                countO--;
                hToRelease++;
                oToRelease++;
                condH.signal();
                condO.signal();
                while (hToRelease<=0) {
                    condH.await();
                }
                hToRelease--;
            }
        } finally {
            lock.unlock();
        }
    }

    public void o() throws InterruptedException {
        lock.lock();
        try {
            countO++;
            if (countH>=2 && countO>=1) {
                System.out.println("2 H and 1 O consumed in O()");
                countH-=2;
                countO--;
                hToRelease+=2;
                condH.signal();
                condH.signal();
            } else {
                while (oToRelease<=0) {
                    condO.await();
                }
                oToRelease--;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        int n = 300;
        final H2O h2o = new H2O();
        Thread[] threads = new Thread[n];
        for (int i=0; i<n; i++) {
            final int id = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j=0; j<10; j++) {
                        if (id %3 == 0) {
                            try {
                                h2o.o();
                                System.out.println(String.format("Producing an O in thread %d", id));
                            } catch (InterruptedException e) {
                                System.out.println(String.format("Thread %d is interrupted for O().", id));
                            }
                        } else {
                            try {
                                h2o.h();
                                System.out.println(String.format("Producing an H in thread %d", id));
                            } catch (InterruptedException e) {
                                System.out.println(String.format("Thread %d is interrupted for H().", id));
                            }
                        }
                    }
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
