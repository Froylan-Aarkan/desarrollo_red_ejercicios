import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculadora extends Remote{
    public float div(float a, float b) throws RemoteException;
    public float mul(float a, float b) throws RemoteException;
    public float sum(float a, float b) throws RemoteException;
    public float res(float a, float b) throws RemoteException;
} 