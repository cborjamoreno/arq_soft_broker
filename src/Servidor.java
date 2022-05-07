

public class Servidor {

    private String nombre;
    private String host_remoto_IP_port;
    public ArrayList<Servicio> listaServicios;

    public Servidor(String nombre, String host_remoto_IP_port, ArrayList<Servicio> lista_servicios) {
        this.nombre = nombre;
        this.host_remoto_IP_port = host_remoto_IP_port;
        this.listaServicios = lista_servicios;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIpPort() {
        return host_remoto_IP_port;
    }

    public ArrayList<Servicio> getServicios() {
        return listaServicios;
    }

    public addServicio(Servicio service) {
        listaServicios.add(service);
    }
}