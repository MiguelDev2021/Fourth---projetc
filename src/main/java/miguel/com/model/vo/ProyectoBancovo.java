package miguel.com.model.vo;

public class ProyectoBancovo {
    private Integer Id;
    private String Constructora;
    private String Cuidad;
    private String Clasificacion;
    private Integer estrato;
    private String lider;
   
   
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public String getConstructora() {
        return Constructora;
    }
    public void setConstructora(String constructora) {
        Constructora = constructora;
    }
    public String getCuidad() {
        return Cuidad;
    }
    public void setCuidad(String cuidad) {
        Cuidad = cuidad;
    }
    public String getClasificacion() {
        return Clasificacion;
    }
    public void setClasificacion(String clasificacion) {
        Clasificacion = clasificacion;
    }
 
    public String getLider() {
        return lider;
    }
    public void setLider(String lider) {
        this.lider = lider;
    }
    public Integer getEstrato() {
        return estrato;
    }
    public void setEstrato(Integer estrato) {
        this.estrato = estrato;
    }



    
}
