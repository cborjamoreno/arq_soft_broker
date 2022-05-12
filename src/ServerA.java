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
    private static String brokerName;
    private static String hostName = "ServerA3675";

    public ServerA(String ip, String brokerName) throws RemoteException {
        super();
        ServerA.ip = ip;
        ServerA.brokerName = brokerName;
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

    /**
    * Funcion auxiliar que convierte un array de strings representando clases en array de clases
    *
    * @param param    Tipos de parametros en formato string
    * @return         Array de clases representadas por la entrada
    */
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

    public static void main(String args[]) {
        System.setProperty("java.security.policy", "./java.policy");
        System.setSecurityManager(new SecurityManager());
        
        try {
            ServerA o = new ServerA(args[0],args[1]);
            System.out.println("Creado!");

            Broker broker = (Broker) Naming.lookup("//" + ipBroker + "/" + brokerName);

            // Registrar el objeto ServerA remoto
            Naming.rebind("//" + ip + "/"+ hostName, o);

            // Registrar el servidor en el broker
            broker.registrar_servidor(hostName,ip);
            System.out.println("Estoy registrado en el Broker!");
            
            //Doy de alta los servicios
            broker.registrar_servicio(hostName, "dar_hora", "string", new String[]{});
            broker.registrar_servicio(hostName, "dar_fecha", "string", new String[]{});
            broker.registrar_servicio(hostName, "eco", "string", new String[]{"String"});
            //broker.registrar_servicio(hostName, "suma", "string", new String[]{"String","String"});
            System.out.println("Servicios registrados");

            //Quito un servicio porque no me gusta
            // broker.baja_servicio("suma");
            // System.out.println("He dado de baja suma");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
}