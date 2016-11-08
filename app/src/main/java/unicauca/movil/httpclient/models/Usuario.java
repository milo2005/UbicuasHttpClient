package unicauca.movil.httpclient.models;

/**
 * Created by Dario Chamorro on 8/11/2016.
 */

public class Usuario {

    long id;
    String nombre, celular;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
