package ServidorMultihilo;

import java.io.IOException;
import java.net.ServerSocket;

public class ServidorMultihilo{
    public static void main(String[] args){
        int puerto = 1612;

        try(ServerSocket ss = new ServerSocket(puerto)){
            System.out.println("Servidor escuchando en el puerto: " + puerto + "...");
            int contador = 0;
            
            while (true) {
                System.out.println("El servidor tiene " + contador + " clientes conectados.");
                contador++;
                HiloHandler cliente = new HiloHandler(ss.accept(), contador);
                Thread h1 = new Thread(cliente);
                h1.start();
            }
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
}