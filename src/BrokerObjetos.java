import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class BrokerObjetos extends UnicastRemoteObject
{
    
    public void registrar_servidor(String nombre_servidor, String host_remoto_IP_puerto) {

    }

    public Object ejecutar_servicio(String nombre_servicio, ArrayList<String> parametros_servicio) {

    }

    public static void main(String args[])
    {
        //Fijar el directorio donde se encuentra el java.policy
        System.setProperty("java.security.policy", "./src/java.policy");

        //Crear administrador de seguridad
        System.setSecurityManager(new SecurityManager());

        //nombre o IP del host donde reside el objeto servidor
        String hostName = "localhost"; 
        
        try
        {
            // Crear objeto remoto
            CollectionImpl obj = new CollectionImpl();
            System.out.println("Creado!");
            //Registrar el objeto remoto
            Naming.rebind("//" + hostName + "/MyCollection", obj);
            System.out.println("Estoy registrado!");
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }