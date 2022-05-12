import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientC {
    private static final String ipBroker = "155.210.154.209";
    private static final String brokerName = "Broker3675";

    public static void main(String[] args) throws RemoteException{
        System.setProperty("java.security.policy", "./java.policy");
        if (System.getSecurityManager() == null) {
            //Crear administrador de seguridad
            System.setSecurityManager(new SecurityManager());
        }
        System.setProperty("java.rmi.server.hostname","155.210.154.209");
        Broker broker = null;
        try
        {
            //Paso 1 - Obtener una referencia al objeto broker creado anteriormente
            broker = (Broker) Naming.lookup("//" + ipBroker + "/" + brokerName);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }

        //Bucle principal
        Scanner sc = new Scanner(System.in);  
        while(true) {
            System.out.println("¿Que quieres que haga?");
            System.out.println("    1: Mostrar servicios");
            System.out.println("    2: Ejecutar un servicio");
            System.out.println("    3: Salir");
            switch (sc.nextInt()) {
                case 1: //Mostrar servicios
                    System.out.println("Estos son los servicios:");
                    for(Servicio s : broker.listar_servicios()) {
                        System.out.println("    "+s.toString());
                    }

                    System.out.println();
                    break;

                case 2: //Ejecutar un servicio
                    System.out.println("¿Que servicio quieres que ejecute?");
                    sc.nextLine();
                    String servicio = sc.nextLine();
                    System.out.println("¿Argumentos? (separados por comas)");
                    String arguments = sc.nextLine();

                    System.out.println("Ejecutando servicio "+servicio+" con argumentos ["+arguments+"]...");
                    if(arguments.compareTo("") == 0) {
                        System.out.println(broker.ejecutar_servicio(servicio, new String[]{}));
                    }
                    else {
                        System.out.println(broker.ejecutar_servicio(servicio, arguments.split(",")));
                    }


                    System.out.println();
                    break;
                
                case 3: //Salir
                    System.out.println("Adios :)");
                    sc.close();
                    return;

                default:
                    System.out.println("No te he entendido");

                    System.out.println();
                    break;
            }
        }
    }
}