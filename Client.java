import java.io.*;
import java.net.*;
import java.util.Random;

/**
 * The Main Class for the Client.
 * It starts a Car for parking
 */
public class Client {

    private static Logger log = Logger.getInstance();

    private static enum OPERATIONS {
      PARK, OUTPARK
    }

    public static void main(String[] args) {
        Random rand = new Random();
        try {
          Thread.sleep(rand.nextInt(500 + 1));
          int port = new ServerSocket(0).getLocalPort();
          DatagramSocket clientSocket = new DatagramSocket(port);
          boolean parked = false;
          byte[] sendData = OPERATIONS.PARK.name().getBytes();
          byte[] receiveData = new byte[1000];
          DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 8000);

          do {
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            parked = receivePacket.getData()[0]!=0;
            Thread.sleep(rand.nextInt(2000 + 1));
          } while (!parked);

          Thread.sleep(rand.nextInt(2000 + 1));
          byte[] sendData2 = OPERATIONS.OUTPARK.name().getBytes();
          DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length,InetAddress.getByName("localhost"), 8000);
          clientSocket.send(sendPacket2);

          clientSocket.close();
        } catch(SocketException e) {
          log.error("Socket: " + e.getMessage());
        } catch(IOException e) {
          log.error("IO: " + e.getMessage() );
        } catch(InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
    }
}
