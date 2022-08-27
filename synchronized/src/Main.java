public class Main {
    public static void main (String[] args) throws InterruptedException {
        final CarSeller rolf= new CarSeller("Рольф");
        final Buyer buyer1 = new Buyer("Покупатель 1", rolf);
        final Buyer buyer2 = new Buyer("Покупатель 2", rolf);
        final Buyer buyer3 = new Buyer("Покупатель 3", rolf);
        final CarProducer toyota = new CarProducer("Toyota", rolf);

        ThreadGroup threadGroup = new ThreadGroup("mainGroup");
        Thread threadProducerToyota = new Thread(threadGroup, toyota::produceCar, "threadToyota", 0);
        Thread threadBuyer1 = new Thread(threadGroup, buyer1::buyCar, "threadBuyer1", 0);
        Thread threadBuyer2 = new Thread(threadGroup, buyer2::buyCar, "threadBuyer1", 0 );
        Thread threadBuyer3 = new Thread(threadGroup, buyer3::buyCar, "threadBuyer1", 0 );

        threadProducerToyota.start();
        threadBuyer1.start();
        threadBuyer2.start();
        threadBuyer3.start();

    }
}
