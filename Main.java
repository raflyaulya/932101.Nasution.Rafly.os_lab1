public class Main {
    public static void main(String[] args) {

        Monitor monitor=new Monitor();

        Thread producerThread=new Thread(monitor::producer);
        Thread consumerThread=new Thread(monitor::consumer);

        producerThread.start();
        consumerThread.start();

    }
}