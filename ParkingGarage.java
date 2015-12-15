import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * The Class "ParkingGarage" implements the logic of a Parking Garage. The threads of the class "Car" are calling the
 * Methods parkcar and outparkcar. If the parking garage is full the IDs of the cars are stored in a Queue.
 * Cars which are at the Top of the Queue are allowed to park in first
 */
public class ParkingGarage {

    /**The maximal Number of cars the parking garage can store*/
    private int maxcars;
    /** The number of cars which are in the parking garage */
    private int cars;
    /** The queue for cars which are waiting to get parked*/
    private Queue<Integer> waitingQueue;
    /**The logging class which handles the output*/
    private Logger log;

    /**
     * Default Constructor of the Class
     * Initializes the Value maxcars, the Loggerclass, the waitingQueue
     * @param maxcars Value of the maximal cars which can be stored in the garage
     */
    public ParkingGarage(int maxcars) {
        this.maxcars = maxcars;
        waitingQueue = new LinkedBlockingQueue<>();
        log = Logger.getInstance();
    }

    /**
     * Method is called by a car-thread to store the car in the garage.
     * If the garage is full the car gets stored in a Queue if it`s not already in it and puts the car-thread on WAIT
     * If the garage has new space the fist element of the Queue gets stored and removed from the List.
     * @param carid id of the car that should be parked
     */
    public synchronized boolean parkcar(int carid) {

      boolean parked = false;
      /**If there is a Waiting Queue we test if the car is in it, if it`s at the head of the queue we remove it and mark it as parked.
       * If it's in the queue but not at the head the car stays in the queue.
       * If the car is not in the queue we add it.
       */
      if (waitingQueue.size() > 0) {
        if (waitingQueue.peek().equals(carid) && cars < maxcars) {
          waitingQueue.remove();
          cars++;
          log.info("Removed from Queue: " + carid + " Cars in Queue:" + waitingQueue.toString());
          log.info("Cars in the garage: " + cars);
          parked = true;
        } else {
          if (waitingQueue.contains(carid)) {
            log.info("Car already in Queue: " + carid + " Cars in Queue:" + waitingQueue.toString());
          } else {
            waitingQueue.add(carid);
            log.info("Car added to Queue: " + carid + " Cars in Queue:" + waitingQueue.toString());
          }
        }

        /**
         * If there is no Queue and there is free space in the garage, the car gets marked as parked and
         * the value of the parked cars gets incremented
         * If there is no free space in the garage the car gets added to a queue
         */
      } else if (cars < maxcars) {
        cars++;
        log.info("Car parked in Garage: " + carid + " Cars in the Garage: " + cars);
        parked = true;
      } else {
        waitingQueue.add(carid);
        log.info("Car added to waiting Queue: " + carid + " Waiting Queue:" + waitingQueue.toString());
      }

      return parked;
    }


    /**
     * The Method is called by the car-threads to outpark a car.
     */
    public synchronized void outparkcar(){
       cars--;
       log.info("Car got removed, there are: "+cars+" in the garage" + " Cars in Queue:" + waitingQueue.toString());
    }
}
