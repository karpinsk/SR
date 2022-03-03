import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class JavaUdpClient {

    public static void main(String args[]) throws Exception
    {
        System.out.println("JAVA UDP CLIENT");
        DatagramSocket socket = null;
        int portNumber = 9008;
        char terminator = '#';  // terminator is used to cut message from buffer

        byte[] receiveBuffer = new byte[1024];

        try {
            socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            String message = "Hello server!";   // message for server
            message += terminator;
            byte[] sendBuffer = message.getBytes();    // convert string to bytes

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, portNumber);
            socket.send(sendPacket);

            // RECEIVE MESSAGE FROM SERVER
            Arrays.fill(receiveBuffer, (byte)0);
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            String answer = new String(receivePacket.getData());
            String result = "";

            // CUT MESSAGE
            for (char ch: answer.toCharArray()) {
                if (ch == terminator) {
                    break;
                }
                result += ch;
            }
            System.out.println("Message from server: " + result);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
