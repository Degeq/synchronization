import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;

    public class CarSeller {
        private final int TimeToNoticeBuyer = 3000;
        String name;
        public static volatile ArrayList<Cars> stock = new ArrayList<>();

        public CarSeller(String name) {
            this.name = name;
        }

        public synchronized void getCar(Cars car) {
            try {
                stock.add(car);
                Thread.currentThread().sleep(TimeToNoticeBuyer);
                notify();
            } catch (InterruptedException ine) {
                ine.printStackTrace();
            }
        }

        public synchronized void sellCar() throws InterruptedException {
            if (stock.size()==0 && Buyer.COUNTER < 10) {
                System.out.println("Машин нет");
                try {
                    wait();
                    if (Buyer.COUNTER < 10) {
                        stock.remove(0);
                    }
                } catch (InterruptedException ine) {
                    ine.printStackTrace();
                }
            } else {
                notifyAll();
                Thread.currentThread().sleep(100);
                Thread.currentThread().getThreadGroup().interrupt();
            }
        }

        public String getName() {
            return name;
        }
    }