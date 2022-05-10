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
            System.out.println("    1: Mostrar metodos");
            System.out.println("    2: Ejecutar un metodo");
            System.out.println("    3: Salir");
            switch (sc.nextInt()) {
                case 1: //Mostrar metodos
                    System.out.println("Estos son los metodos:");
                    for(Servicio s : broker.listar_servicios()) {
                        System.out.println("    "+s.toString());
                    }

                    System.out.println();
                    break;

                case 2: //Ejecutar un metodo
                    System.out.println("¿Que metodo quieres que ejecute?");
                    sc.nextLine();
                    String metodo = sc.nextLine();
                    System.out.println("Ejecutando metodo "+metodo+"...");
                    System.out.println(broker.ejecutar_servicio(metodo));

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