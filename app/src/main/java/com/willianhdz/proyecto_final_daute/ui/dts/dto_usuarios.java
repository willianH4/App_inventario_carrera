package  com.willianhdz.proyecto_final_daute.ui.dts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dto_usuarios {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("nombre")
    @Expose
    String nombre;

    @SerializedName("apellido")
    @Expose
    String apellido;

    @SerializedName("correo")
    @Expose
    String correo;

    @SerializedName("usuario")
    @Expose
    String usuario;

    @SerializedName("clave")
    @Expose
    String clave;

    @SerializedName("tipo")
    @Expose
    int tipo;

    @SerializedName("estado")
    @Expose
    int estado;

    @SerializedName("pregunta")
    @Expose
    String pregunta;

    @SerializedName("respuesta")
    @Expose
    String respuesta;

    @SerializedName("fecha_registro")
    @Expose
    String fecha_registro;

    public dto_usuarios() {
    }

    public dto_usuarios(int id, String nombre, String apellido, String correo, String usuario, String clave, int tipo, int estado, String pregunta, String respuesta, String fecha_registro) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.usuario = usuario;
        this.clave = clave;
        this.tipo = tipo;
        this.estado = estado;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.fecha_registro = fecha_registro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    @Override
    public String toString() {
        return "dto_usuarios{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                ", tipo=" + tipo +
                ", estado=" + estado +
                ", pregunta='" + pregunta + '\'' +
                ", respuesta='" + respuesta + '\'' +
                ", fecha_registro='" + fecha_registro + '\'' +
                '}';
    }
}
