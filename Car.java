import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * The class "Car" represents a car which should use the parking garage.
 * Every car is one Thread.
 * It calls the Methods parkcar and outparkcar of the Class ParkingGarage
 */
public class Car extends Thread{

    /**The Objekt of the Parking Garage which is used*/
    private static ParkingGarage parkGarage;
    /**The id of the car*/
    private int carid;
    /**The logging class which handles the output*/
    private Logger log;

    /**
     * The Constructor of the Class.
     * It generates a random carid and initializes the Logger.
     */
    public Car(){
        Random rn = new Random();
        carid = rn.nextInt()+1;
        log = Logger.getInstance();
        }

    /**
     * Used to initialize the Objekt of the ParkingGarage which is used.
     * It`s static so all threads use the same garage.
      * @param parkingGarage the Object of the ParkingGarage
     */
    public static void setParkingGarage(ParkingGarage parkingGarage){
        parkGarage = parkingGarage;
    }

    /**
     * The run Method is used to start the Thread
     * It just starts the Method "controlcar"
     */
    public void run(){
        controlcar();
    }

    /**
     * The Method is used to start the Park-Method.
     * After a random-Time between mintime and maxtime the Method unpark is called
     */
    private void controlcar(){
        Random rn = new Random();
        int mintime = 1, maxtime = 5;
        park();
        try {
                TimeUnit.SECONDS.sleep(rn.nextInt((maxtime - mintime) + 1) + mintime);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
         }
         unpark();
    }

    /**
     * The Method calls the outparkcar-Method of the garage which removes the car from the Garage
     */
    public void unpark(){
            parkGarage.outparkcar();
    }

    /**
     * The Method calls the parkcar-Method of the garage to store the car in the garage
     */
    public synchronized void park(){
        parkGarage.parkcar(carid);
    }

}
