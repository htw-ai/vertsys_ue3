// import sun.util.resources.ParallelListResourceBundle;
import java.io.*;
import java.net.*;

/**
 * The Main Class.
 * It Initialises the Parking Garage and the Threads of cars
 */
public class Server {

  private static Logger log = Logger.getInstance();

  private enum OPERATIONS {
    PARK, OUTPARK
  }

    public static void main(String[] args) {
        ParkingGarage garage = new ParkingGarage(5);

        try {
          DatagramSocket socket = new DatagramSocket(8000);
          while(true) {
            byte[] buffer = new byte[1000];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);
            int carid = request.getPort();
            String s  = new String(request.getData()).trim();

            OPERATIONS operation = OPERATIONS.valueOf(s);
            if(operation == OPERATIONS.PARK) {
              byte[] parked = new byte[] { (byte) (garage.parkcar(carid)?1:0) };

              DatagramPacket reply = new DatagramPacket(parked,
                                                        parked.length,
                                                        request.getAddress(),
                                                        request.getPort());
              socket.send(reply);
            } else if (operation == OPERATIONS.OUTPARK) {
              garage.outparkcar();
            }
          }
        } catch(SocketException e) {
          log.error("Socket: " + e.getMessage());
        } catch(IOException e) {
          log.error("IO: " + e.getMessage() );
        }
    }
}
