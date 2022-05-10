import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServerA extends UnicastRemoteObject implements Server {

    private static String ip;
    private static final String ipBroker = "localhost";
    private static final String brokerName = "MyBroker";
    private static String hostName = "serverA";

    public ServerA(String ip) throws RemoteException {
        super();
        ServerA.ip = ip;
    }

    public String dar_hora() {
        DateFormat dateF = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return dateF.format(date);
    }

    public String dar_fecha() {
        DateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateF.format(date);
    }

    public String ejecutar_metodo(String metodo) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Method method = this.getClass().getMethod(metodo, new Class[]{String.class}); //El segundo argumento es lo que devuelve
        return (String)method.invoke(this);
    }

    public static void main(String args[]) {
        System.setProperty("java.security.policy", "./java.policy");
        System.setSecurityManager(new SecurityManager());
        
        try {
            Broker broker = (Broker) Naming.lookup("//" + ipBroker + "/" + brokerName);
            ServerA o = new ServerA(args[0]);
            System.out.println("Creado!");

            // Registrar el objeto ServerA remoto
            Naming.rebind("//" + ip + "/"+ hostName, o);

            // Registrar el servidor en el broker
            broker.registrar_servidor(hostName,brokerName);
            System.out.println("Estoy registrado en el Broker!");
            
            broker.registrar_servicio(hostName, "dar_hora", "string");
            broker.registrar_servicio(hostName, "dar_fecha", "string");
            System.out.println("Servicios registrados");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}