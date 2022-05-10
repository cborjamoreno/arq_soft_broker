import java.util.ArrayList;
import java.rmi.Naming;
import java.util.Scanner;

public class ClientC {
    private static final String ipBroker = "localhost";
    private static final String brokerName = "MyBroker";

    public static void main(String[] args){
        System.setProperty("java.security.policy", "./src/java.policy");
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
                    String metodo = sc.nextLine();
                    System.out.println("Ejecutando metodo...");
                    System.out.println(broker.ejecutar_servicio(metodo));

                    System.out.println();
                    break;

                default:
                    System.out.println("No te he entendido");

                    System.out.println();
                    break;
            }
        }
    }
}