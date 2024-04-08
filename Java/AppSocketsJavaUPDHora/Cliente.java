import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cliente {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        String mensaje = new String("Dame la hora local.");
        String servidor = new String("localhost");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formattercliente = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String strFecha = now.format(formattercliente);
        System.out.println("La hora actual es: " + strFecha);

        int puerto = 1616;
        int espera = 5000;

        DatagramSocket socketUDP = new DatagramSocket();
        InetAddress hostServidor = InetAddress.getByName(servidor);

        DatagramPacket peticion = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, hostServidor, puerto);
        socketUDP.setSoTimeout(espera);
        socketUDP.send(peticion);

        try {
            byte[] buffer = new byte[1024];
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(respuesta);

            String strText = new String(respuesta.getData(), 0, respuesta.getLength());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime horaServidor = LocalDateTime.parse(strText, formatter);
            System.out.println("La hora del servidor es: " + horaServidor.format(formatter));
            socketUDP.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
