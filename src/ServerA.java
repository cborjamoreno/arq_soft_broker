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
    private static final String ipBroker = "155.210.154.209";
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

    public String eco(String msg) {
        return msg;
    }

    public String suma(String a, String b) {
        return a+b;
    }

    public String ejecutar_metodo(String metodo, String[] tipoParametros, String retorno, Object[] parametros) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Method method = this.getClass().getMethod(metodo, toClassArray(tipoParametros)); //El segundo argumento es los parametros
        return (String)method.invoke(this,parametros);
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
            broker.registrar_servidor(hostName,ip);
            System.out.println("Estoy registrado en el Broker!");
            
            broker.registrar_servicio(hostName, "dar_hora", "string", new String[]{});
            broker.registrar_servicio(hostName, "dar_fecha", "string", new String[]{});
            broker.registrar_servicio(hostName, "eco", "string", new String[]{"String"});
            broker.registrar_servicio(hostName, "suma", "string", new String[]{"String","String"});
            System.out.println("Servicios registrados");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private Class[] toClassArray(String[] param){
		Class[] output = new Class[param.length];
        int i = 0;
        for(String p: param) {
            switch (p) {
                case "String":
                    output[i++] = String.class;
                    break;
                
                case "Integer":
                    output[i++] = Integer.class;
                    break;
                
                case "Boolean":
                    output[i++] = Boolean.class;
                    break;
                
                case "Character":
                    output[i++] = Character.class;
                    break;
                
                default:
                    output[i++] = Void.class;
                    break;
            }
        }
		return output;
	}

    
}