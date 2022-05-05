import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerA extends Remote
{
    public String dar_hora() throws RemoteException;
    public String dar_fecha() throws RemoteException;
}