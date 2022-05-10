import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Broker extends Remote {

    String ejecutar_servicio(String metodo, Object[] parametros) throws RemoteException;

    void registrar_servidor(String host_remoto_IP_port, String nombre_registrado) throws RemoteException;

    public void registrar_servicio(String nombre_regitrado, String nom_servicio,String tipo_retorno) throws RemoteException;

    ArrayList<Servicio> listar_servicios() throws RemoteException;

}