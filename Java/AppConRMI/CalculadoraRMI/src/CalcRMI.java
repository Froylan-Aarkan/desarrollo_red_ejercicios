import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcRMI extends UnicastRemoteObject implements Calculadora {

    protected CalcRMI() throws RemoteException{
        super();
    }

    @Override
    public float div(float a, float b) throws RemoteException {
        return a / b;
    }

    @Override
    public float mul(float a, float b) throws RemoteException {
        return a * b;
    }

    @Override
    public float res(float a, float b) throws RemoteException {
        return a - b;
    }

    @Override
    public float sum(float a, float b) throws RemoteException {
        return a + b;
    }
    
}