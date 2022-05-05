import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Broker extends Remote {

    void ejecutar_servicio(String nom_servicio) throws RemoteException;

    void registrar_servidor(String host_remoto_IP_port, String nombre_registrado) throws RemoteException;

    //ArrayList<String> listar_servicios() throws RemoteException;

}