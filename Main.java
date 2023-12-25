public class Main {
    public static void main(String[] args) {

        Monitor monitor = new Monitor(); // Создание объекта Monitor

        Thread producerThread = new Thread(monitor::producer); // Создание потока для производителя, ссылаясь на метод producer в объекте monitor
        Thread consumerThread = new Thread(monitor::consumer); // Создание потока для потребителя, ссылаясь на метод consumer в объекте monitor

        producerThread.start(); // Запуск выполнения потока производителя
        consumerThread.start(); // Запуск выполнения потока потребителя

    }
}
