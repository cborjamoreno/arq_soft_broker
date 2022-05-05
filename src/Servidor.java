

public class Servidor {

    private String nombre;
    private String host_remoto_IP_port;

    public Servidor(String nombre, String host_remoto_IP_port) {
        this.nombre = nombre;
        this.host_remoto_IP_port = host_remoto_IP_port;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIpPort() {
        return host_remoto_IP_port;
    }
}