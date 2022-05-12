import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class BrokerImpl extends UnicastRemoteObject implements Broker {
    private ArrayList<Servidor> servidores = new ArrayList<Servidor>();
    private static String ip = "155.210.154.209";
    private static String hostName;

    
    public BrokerImpl(String hostName) throws RemoteException{
        BrokerImpl.hostName = hostName;
    }

    
    public void registrar_servidor(String nombre_servidor, String host_remoto_IP_puerto) throws RemoteException {
        Servidor s = new Servidor(nombre_servidor,host_remoto_IP_puerto);
        try {
            int i=0;
            boolean encontrado = false;
            while (!encontrado && i < servidores.size()){
                if(servidores.get(i).getNombre().equals(nombre_servidor)) {
                    encontrado = true;
                    servidores.remove(i);
                }
                i++;
            }
            servidores.add(s);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
    * Metodo auxiliar que devuelve si el servicio esta registrado
    *
    * @param nombre_servicio    Nombre del servicio
    * @return                   boolean
    */
    public boolean servicioNoRegistrado(String nombre_servicio) {
        for(Servidor server: servidores) {
            for(Servicio s : server.listaServicios) {
                if (s.getNombre().equals(nombre_servicio)) {
                    return false;
                }
            }
        }
        return true;
    }

    
    public void registrar_servicio(String nombre_regitrado, String nom_servicio, String tipo_retorno, String[] tipoParametros) throws RemoteException {
        for (Servidor server : servidores) {
            if (server.getNombre().equals(nombre_regitrado)) {
                server.addServicio(new Servicio(nom_servicio, tipo_retorno, tipoParametros));
            }
        }
    }

    
    public void baja_servicio(String nombre_servicio) throws RemoteException {
        for (Servidor server : servidores) {
            for(Servicio s : server.listaServicios) {
                if (s.getNombre().equals(nombre_servicio)) {
                    server.listaServicios.remove(s);
                }
            }
        }
    }

    
    public String ejecutar_servicio(String nombre_servicio, Object[] parametros) throws RemoteException{
        try {
            for(Servidor server: servidores) {
                for(Servicio s : server.listaServicios) {
                    if (s.getNombre().equals(nombre_servicio)) {
                        Server ser = (Server) Naming.lookup("//" + server.getIpPort() + "/" + server.getNombre());
                        return ser.ejecutar_metodo(nombre_servicio,s.getListaParam(),s.getTipoRetorno(),parametros);
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
