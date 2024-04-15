import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class Cliente {
    public static void main(String[] args) {
        int puerto = 2393;
        String servidor;
        servidor = "192.168.56.102";
        //servidor = "localhost";

        try {  
            Calculadora calculadora = (Calculadora) Naming.lookup("rmi://" + servidor + ":" + puerto + "/Calculadora");

            while (true) {
                String opt = JOptionPane.showInputDialog(
                    "Calcular\n" + 
                    "Suma................(1)\n" +
                    "Resta...............(2)\n" + 
                    "Multiplicación......(3)\n" + 
                    "División............(4)\n\n" + 
                    "Cancelar para salir");
                    
                    if (opt == null){
                        break;
                    }

                    float a = Float.parseFloat(JOptionPane.showInputDialog("Ingrese a"));
                    float b = Float.parseFloat(JOptionPane.showInputDialog("Ingrese b"));

                    switch (opt) {
                        case "1": {
                            JOptionPane.showMessageDialog(null, a + " + " + b + " = " + calculadora.sum(a, b));
                            break;
                        }

                        case "2": {
                            JOptionPane.showMessageDialog(null, a + " - " + b + " = " + calculadora.res(a, b));
                            break;
                        }

                        case "3": {
                            JOptionPane.showMessageDialog(null, a + " x " + b + " = " + calculadora.mul(a, b));
                            break;
                        }

                        case "4": {
                            JOptionPane.showMessageDialog(null, a + " / " + b + " = " + calculadora.div(a, b));
                        }
                    
                        default:
                            break;
                    }
            }
        } catch (RemoteException | NotBoundException e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor:\n" + e);
        }catch (MalformedURLException e){
            JOptionPane.showMessageDialog(null, "La URL está en formato incorrecto:\n" + e);
        }
    }
}