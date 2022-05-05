import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class BrokerImpl implements Broker {
    private ArrayList<Servidor> servidores;
    private static String host;

    
    public BrokerImpl(String ip) {
        host = ip;
    }

    public void registrar_servidor(String nombre_servidor, String host_remoto_IP_puerto) throws RemoteException {
        Servidor s = new Servidor(nombre_servidor,host_remoto_IP_puerto);
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ejecutar_servicio(String nombre_servicio) throws RemoteException{
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // public ArrayList<String> listar_servicios() {
    //     ArrayList<String> lista;
    //     for(Servidor s : servidores) {
    //         lista.add(s.getServicios());
    //     }
    // }

    public static void main(String args[])
    {
        //Fijar el directorio donde se encuentra el java.policy
        System.setProperty("java.security.policy", "./src/java.policy");

        //Crear administrador de seguridad
        System.setSecurityManager(new SecurityManager());
        
        try
        {
            BrokerImpl broker = new BrokerImpl(args[0]);

            System.out.println("Broker creado!");
            //Registrar el objeto remoto
            //Naming.rebind("//" + hostName + "/MyCollection", obj);
            System.out.println("Broker registrado!");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}