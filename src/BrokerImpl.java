import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class BrokerImpl implements Broker {
    private ArrayList<Servidor> servidores;
    private static String ip;
    private static String hostName = "MyBroker";

    
    public BrokerImpl(String ip) {
        this.ip = ip;
    }

    public void registrar_servidor(String nombre_servidor, String host_remoto_IP_puerto) throws RemoteException {
        Servidor s = new Servidor(nombre_servidor,host_remoto_IP_puerto);
        try {
            servidores.add(s);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ejecutar_servicio(String nombre_servicio) throws RemoteException{
        try {
            if (nombre_servicio.equals("dar_hora")) {
                
            }
            else if (nombre_servicio.equals("dar_fecha")) {
                
            }
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
            //Registrar el broker
            Naming.rebind("//" + ip + "/" + hostName, broker);

            System.out.println("Broker creado!");
            System.out.println("Broker registrado!");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}