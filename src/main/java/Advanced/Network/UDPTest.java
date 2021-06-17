package Advanced.Network;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

/**
 * UDP 网络编程
 *
 * @author Feue
 * @create 2021-06-14 19:55
 */
public class UDPTest {
    @Test
    public void sender() throws IOException {
        DatagramSocket socket = new DatagramSocket();
        String str = "UDP方式发送的数据";
        byte[] data = str.getBytes();
        InetAddress inetAddress = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(data, 0, data.length, inetAddress, 9090);
        socket.send(packet);
        socket.close();
    }

    @Test
    public void receiver() throws IOException {
        DatagramSocket socket = new DatagramSocket(9090);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
        socket.receive(packet);
        System.out.println(new String(packet.getData(), 0, packet.getLength()));
        socket.close();
    }
}
