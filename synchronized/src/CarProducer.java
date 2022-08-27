public class CarProducer {
    private final int TimeToProduceNewCar = 3000;
    protected int counter = 0;
    protected String name;
    protected CarSeller carSeller;

    public CarProducer(String name, CarSeller carSeller) {
        this.name = name;
        this.carSeller = carSeller;
    }

    public void produceCar() {
        while (!Thread.currentThread().isInterrupted() && Buyer.COUNTER < 10) {
            try {
                Thread.currentThread().sleep(TimeToProduceNewCar);
                System.out.println("Производитель" + name + " выпустил 1 автомобиль");
                carSeller.getCar(new Cars(name));
            } catch (InterruptedException ine) {

            }
        }

        Thread.currentThread().interrupt();
    }

}