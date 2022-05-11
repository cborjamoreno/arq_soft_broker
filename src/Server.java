import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
    public String ejecutar_metodo(String metodo, String[] tipoParametros, String retorno, Object[] parametros) throws RemoteException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
}
