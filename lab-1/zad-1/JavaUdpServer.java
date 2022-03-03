import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class JavaUdpServer {

    public static void main(String args[])
    {
        System.out.println("JAVA UDP SERVER");
        DatagramSocket socket = null;
        int portNumber = 9008;
        char terminator = '#';      // terminator is used to cut message from buffer

        try {
            socket = new DatagramSocket(portNumber);
            byte[] receiveBuffer = new byte[1024];

            while(true) {
                Arrays.fill(receiveBuffer, (byte)0);
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                socket.receive(receivePacket);
                String msg = new String(receivePacket.getData());   // message received from client
                String rec_msg = "";

                for (char ch: msg.toCharArray()) {
                    if (ch == terminator) {
                        break;
                    }
                    rec_msg += ch;
                }
                System.out.println("Message from client: " + rec_msg);

                // GET ADDRESS OF CLIENT
                InetAddress address = receivePacket.getAddress();
                int port = receivePacket.getPort();
                System.out.println("Client's address: " + address);

                // SEND MESSAGE FROM SERVER
                String send_message = "Hello client!";
                send_message += terminator;
                byte[] sendBuffer = send_message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
                socket.send(sendPacket);
            }
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
