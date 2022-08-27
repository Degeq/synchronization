import java.util.concurrent.locks.ReentrantLock;

public class Buyer {
//    private final ReentrantLock locker = new ReentrantLock(true);
    private final int TimeBeforeNewCar = 3000;
    public static volatile Integer COUNTER = 0;
    String name;
    CarSeller carSeller;

    public Buyer(String name, CarSeller carSeller) {
        this.name = name;
        this.carSeller = carSeller;
    }

    public synchronized void buyCar() {
//        locker.lock();
        try {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println(name + "зашел в " + carSeller.getName());
                    carSeller.sellCar();
                    if (COUNTER < 10) {
                        Buyer.COUNTER++;
                        System.out.println(name + " уехал на новенькой машине" + COUNTER);
                        Thread.currentThread().sleep(TimeBeforeNewCar);
                    } else {
                        System.out.println("К сожалению превышена квота продаж автомобилей" + name);
                        break;
                    }
                } catch (InterruptedException ine) {

                }
            }
            Thread.currentThread().interrupt();
        } finally {
//            locker.unlock();
        }
    }
}