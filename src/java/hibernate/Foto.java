package hibernate;
// Generated 30-ene-2015 9:24:53 by Hibernate Tools 4.3.1



/**
 * Foto generated by hbm2java
 */
public class Foto  implements java.io.Serializable {


     private Integer id;
     private Inmueble inmueble;
     private String ruta;
     private String usuario;

    public Foto() {
    }

    public Foto(Inmueble inmueble, String ruta, String usuario) {
       this.inmueble = inmueble;
       this.ruta = ruta;
       this.usuario = usuario;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Inmueble getInmueble() {
        return this.inmueble;
    }
    
    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }
    public String getRuta() {
        return this.ruta;
    }
    
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }




}


