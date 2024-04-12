import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Scanner;

public class MulticastUDPChatGrafico {
    public volatile static boolean terminado = false;
    public static String nombre;

    public static class HiloChat implements Runnable {
        private int puerto;
        private InetAddress grupo;
        private MulticastSocket socket;
    
        public HiloChat(int puerto, InetAddress grupo, MulticastSocket socket){
            this.puerto = puerto;
            this.grupo = grupo;
            this.socket = socket;
        }
    
        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            String linea;
    
            while (!MulticastUDPChatConsola.terminado) {
                try {
                    DatagramPacket mensajeEntrada = new DatagramPacket(buffer, buffer.length, grupo, puerto);
                    socket.receive(mensajeEntrada);
    
                    linea = new String(buffer, 0, mensajeEntrada.getLength());
                    
                    if(!linea.startsWith(MulticastUDPChatConsola.nombre)){
                        System.out.println(linea);
                    }
                } catch (IOException e) {
                    System.out.println("Comunicación y socket cerrados.");
                }
            }
        }
        
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] arga){
        try {
            int puerto = 1212;
            InetAddress grupo = InetAddress.getByName("224.0.0.0");
            MulticastSocket socket = new MulticastSocket(puerto);

            socket.joinGroup(grupo);

            Scanner scan = new Scanner(System.in);
            System.out.println("Ingrese su nombre: ");
            nombre = scan.nextLine();

            byte[] buffer = new byte[1024];
            String linea;

            Thread hilo = new Thread(new HiloChat(puerto, grupo, socket));
            hilo.start();
            System.out.println("Envie un mensaje al grupo: ");

            while (true) {
                linea = scan.nextLine();
                if (linea.equalsIgnoreCase("Adios")) {
                    terminado = true;

                    linea = nombre + ": Ha terminado la conexión.";
                    buffer = linea.getBytes();
                    DatagramPacket mensajeSalida = new DatagramPacket(buffer, buffer.length, grupo, puerto);
                    socket.send(mensajeSalida);

                    socket.leaveGroup(grupo);
                    socket.close();
                    scan.close();
                    break;
                }

                linea = nombre + ": " + linea;
                buffer = linea.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, grupo, puerto);
                socket.send(datagramPacket);
            }
        } catch (SocketException ex) {
            System.out.println("Socket: " + ex.getMessage());
        } catch (IOException ex){
            System.out.println("IO: " + ex.getMessage());
        }
    }
}
