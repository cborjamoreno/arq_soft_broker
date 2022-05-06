import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ServerA {

    private static String ipHost;
    private static final String ipBroker = "localhost";

    public ServerA(String ipHost) throws RemoteException {
        super();
        this.ipHost = ipHost;
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

    public static void main(String args[]) {
        System.setProperty("java.security.policy", "./src/java.policy");
        System.setSecurityManager(new SecurityManager());
        String hostName = "serverA";
        String brokerName = "";

        Broker broker = (Broker) Naming.lookup("//" + brokerName + "");
        try {
            ServerA o = new ServerA(args[0]);
            System.out.println("Creado!");

            //Registrar el servidor en el broker
            broker.registrar_servidor(hostName,brokerName);

            System.out.println("Estoy registrado en el Broker!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}