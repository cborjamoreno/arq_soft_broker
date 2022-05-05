import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ServerAImpl extends UnicastRemoteObject implements ServerA {

    public ServerAImpl() throws RemoteException {
        super();
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

        //BrokerObjetos broker = (BrokerObjetos) Naming.lookup("//" + brokerName + "");

        try {
            ServerAImpl o = new ServerAImpl();
            System.out.println("Creado!");

            //Registrar objeto en broker
            //registrar_servidor(hostName,brokerName);
            System.out.println("Estoy registrado en el Broker!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}