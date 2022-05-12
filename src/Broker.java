import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Broker extends Remote {

    /**
    * Ejecuta un servicio registrado en el broker
    *
    * @param nombre_servicio    Nombre del servicio
    * @param parametros         Parametros del servicio
    * @return                   Valor devuelto serializado como String
    */
    public String ejecutar_servicio(String nombre_servicio, Object[] parametros) throws RemoteException;

    /**
    * Registra un servidor en el broker
    *
    * @param nombre_servidor        Nombre del servidor
    * @param host_remoto_IP_puerto  IP del servidor 
    * @return                       void
    */
    public void registrar_servidor(String nombre_servidor, String host_remoto_IP_puerto) throws RemoteException;

    /**
    * Registra un servicio en el broker
    *
    * @param nombre_registrado  Nombre del servidor que ofrece este servicio
    * @param nombre_servicio       Nombre del servicio a registrar
    * @param tipo_retorno       Tipo que devuelve el servicio
    * @param tipoParametros     Tipo de los parametros del servicio
    * @return                   void
    */
    public void registrar_servicio(String nombre_registrado, String nombre_servicio, String tipo_retorno, String[] tipo_parametros) throws RemoteException;
    
    /**
    * Lista los servicios disponibles en el broker
    * 
    * @return       ArrayList de servicios
    */
    public ArrayList<Servicio> listar_servicios() throws RemoteException;

    /**
    * Da de baja un servicio en el broker
    *
    * @param nombre_servicio    Nombre del servicio a dar de baja
    * @return                   void
    */
    public void baja_servicio(String nombre_servicio) throws RemoteException;
}