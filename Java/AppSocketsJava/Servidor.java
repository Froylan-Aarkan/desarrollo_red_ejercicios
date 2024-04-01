import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor{
    public static void main(String[] args){
        int puerto = 8080;
        try{
            ServerSocket ss = new ServerSocket(puerto);
            System.out.println("Servidor escuchando en el puerto: " + puerto + "...");
            
            Socket ch = ss.accept();

            PrintWriter out = new PrintWriter(ch.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(ch.getInputStream()));

            /*out.println("Hola ");
            out.println("Mundo ");
            out.println("Froylan de Jesus Alvarez Rodriguez");*/

            Scanner scanner = new Scanner(System.in);
            String mensajeSalida = "Iniciando conversación...";
            out.println(mensajeSalida);

            do{
                mensajeSalida = scanner.nextLine();
                out.println(mensajeSalida);
            }while(!mensajeSalida.equalsIgnoreCase("Adios"));
            
            System.out.println("Cliente: " + in.readLine());

            scanner.close();
            out.close();
            in.close();
            ch.close();
            ss.close();
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
}