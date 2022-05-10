
public class Servicio {

    private String tipo;
    private String nombre;
    //private String[] lista_param; //Array con los parámetros del servicio

    public Servicio(String nombre, String retorno) {
        this.tipo = retorno;
        this.nombre = nombre;
    }
    
	//Getters
    public String getNombre() {
        return nombre;
    }

    public String getTipoRetorno() {
        return tipo;
    }

    // public String[] getListaParam() {
    //     return lista_param;
    // }
    
    /*
     * Reescritura del método toString de la clase Object de Java.
     */ 
	@Override
    public String toString() {
        //String parametros = Arrays.toString(lista_param);
        return tipo + " " + nombre + "()"; //+ "(" + parametros + ")";
    }
}
