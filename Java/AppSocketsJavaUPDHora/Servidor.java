import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Servidor{
    public static void main(String[] args) throws SocketException, IOException{
        int puerto = 1616;
        int retardo = (int) (Math.random() * 6000) + 1;

        try(DatagramSocket socketUDP = new DatagramSocket(puerto)){
            byte[] buffer = new byte[1024];
            System.out.println("Servidor UDP escuchando en el puerto: " + puerto + "...");
            while (true) {
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);

                System.out.println("Datagrama recibido del host: " + peticion.getAddress());
                System.out.println("desde el puerto remoto: " + peticion.getPort());
                System.out.println("Datos recibidos: " + new String(peticion.getData()));

                try {
                    System.out.println("Simular un retardo de " + retardo + " milisegundos.");
                    Thread.sleep(retardo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String strFecha = now.format(formatter);
                System.out.println("La hora del servidor es: " + strFecha);

                DatagramPacket respuesta = new DatagramPacket(strFecha.getBytes(), strFecha.getBytes().length, peticion.getAddress(), peticion.getPort());
                socketUDP.send(respuesta);
                System.out.println("Datos enviados al cliente.");
            }
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
}