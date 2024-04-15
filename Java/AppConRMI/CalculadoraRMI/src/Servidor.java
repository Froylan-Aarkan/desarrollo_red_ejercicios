import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            int puerto = 2393;
            String servidor;
            //servidor = "192.168.56.102";
            servidor = "localhost";

            System.out.println("Iniciando servidor en: ");
            System.out.println("hostname: " + servidor);
            System.out.println("puerto: " + puerto );

            Registry registro = LocateRegistry.createRegistry(puerto);
            System.setProperty("java.rmi.server.hostname", servidor);
            registro.rebind("Calculadora", new CalcRMI());

            System.out.println("Servidor en ejecucion en espera de clientes...");

        } catch (RemoteException e) {
            System.err.println(e);
        }
    }
}