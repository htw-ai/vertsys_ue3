import sun.util.resources.ParallelListResourceBundle;

/**
 * The Main Class.
 * It Initialises the Parking Garage and the Threads of cars
 */
public class Main {


    public static void main(String[] args) {

        ParkingGarage garage = new ParkingGarage(10);

        Car.setParkingGarage(garage);
        for(int i = 0; i < 20; i++){
            Car car = new Car();
            car.start();
        }
    }
}
