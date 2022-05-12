import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
    /**
    * Ejecuta un metodo en el servidor
    *
    * @param metodo         Nombre del servidor
    * @param tipoParametro  IP del servidor 
    * @param retorno
    * @param parametros
    * @throws RemoteException
    * @throws IllegalAccessException
    * @throws IllegalArgumentException
    * @throws InvocationTargetException
    * @throws NoSuchMethodException
    * @throws SecurityException
    * @return               Valor devuelto serializado como String
    */
    public String ejecutar_metodo(String metodo, String[] tipoParametros, String retorno, Object[] parametros) throws RemoteException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
}
