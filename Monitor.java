import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
    private Lock lock = new ReentrantLock(); // Инициализация блокировки с использованием ReentrantLock
    private Condition condition = lock.newCondition(); // Инициализация условия с использованием блокировки

    private int counter = 0; // Переменная counter как ресурс, создаваемый и потребляемый

    public void producer() {
        for (int i = 0; i < 20; i++) { // Цикл для производителя, создающего ресурс 20 раз
            try {
                Thread.sleep(1000); // Производитель спит в течение 1 секунды (имитация создания ресурса)
                lock.lock(); // Захват блокировки для доступа к общему ресурсу (counter)
                counter++; // Увеличение значения counter (создание ресурса)
                System.out.println("Send event" + ":" + counter); // Вывод информации о производстве
                condition.signal(); // Сообщить потребителю, что ресурс доступен
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); // Освобождение блокировки после завершения использования общего ресурса
            }
        }
    }


    public void consumer() {
        for (int i = 0; i < 20; i++) { // Цикл для потребителя, потребляющего ресурс 20 раз
            try {
                lock.lock(); // Захват блокировки для доступа к общему ресурсу (counter)
                condition.await(); // Ожидание сигнала о доступности ресурса
                System.out.println("Handling events" + ":" + counter); // Вывод информации о потреблении
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); // Освобождение блокировки после завершения использования общего ресурса
            }
        }
    }
}
