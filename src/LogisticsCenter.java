import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class LogisticsCenter {

    private static Object mutex = new Object();
    public static ArrayBlockingQueue<Truck> queue = new ArrayBlockingQueue<Truck>(10);
    public static List<Truck> allTruck = new LinkedList<>();


    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            allTruck.add(new Truck("Truck " + i));
        }

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                LogisticsCenter.queue.remove(i);
                System.out.println("Происходит  выгрузка Truck номер " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                LogisticsCenter.queue.add(LogisticsCenter.allTruck.get(i));
                System.out.println("Происходит загрузка Truck номер " + i);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

class Truck {
    private String name;
    public Truck(String name) {
        this.name = name;
    }
}

