import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
    private Lock lock=new ReentrantLock();
    private Condition condition= lock.newCondition();

    private int counter=0;

    public void producer(){
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(1000);
                lock.lock();
                counter++;
                System.out.println("Send event"+":"+counter);
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    public  void consumer(){
        for (int i = 0; i < 20; i++) {
            try {
                lock.lock();
                condition.await();
                System.out.println("Handling events"+":"+counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        }
}