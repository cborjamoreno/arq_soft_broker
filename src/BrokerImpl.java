import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class BrokerImpl extends UnicastRemoteObject implements Broker {
    private ArrayList<Servidor> servidores = new ArrayList<Servidor>();
    private static String ip;
    private static String hostName = "MyBroker";

    
    public BrokerImpl(String ip) throws RemoteException{
        BrokerImpl.ip = ip;
    }

    public void registrar_servidor(String nombre_servidor, String host_remoto_IP_puerto) throws RemoteException {
        Servidor s = new Servidor(nombre_servidor,host_remoto_IP_puerto);
        try {
            servidores.add(s);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void registrar_servicio(String nombre_servidor, String nombre_servicio, String tipo_retorno) throws RemoteException {
        for (Servidor server : servidores) {
            if (server.getNombre().equals(nombre_servidor)) {
                server.addServicio(new Servicio(nombre_servicio, tipo_retorno));
            }
        }
    }

    public String ejecutar_servicio(String nombre_servicio) throws RemoteException{
        try {
            for(Servidor server: servidores) {
                for(Servicio s : server.listaServicios) {
                    if (s.getNombre().equals(nombre_servicio)) {
                        ServerA ser = (ServerA) Naming.lookup("//" + server.getIpPort() + "/" + server.getNombre());
                        return ser.ejecutar_metodo(nombre_servicio);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return "";
    }

    public ArrayList<Servicio> listar_servicios() {
        ArrayList<Servicio> lista = new ArrayList<Servicio>();
        for(Servidor server : servidores) {
            lista.addAll(server.getServicios());
        }
        return lista;
    }

    public static void main(String args[])
    {
        //Fijar el directorio donde se encuentra el java.policy
        System.setProperty("java.security.policy", "./java.policy");

        //Crear administrador de seguridad
        System.setSecurityManager(new SecurityManager());
        
        try
        {
            BrokerImpl broker = new BrokerImpl(args[0]);
            System.out.println("Broker creado!");

            //Registrar el broker
            Naming.rebind("//" + ip + "/" + hostName, broker);
            System.out.println("Broker registrado!");
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}
