package mx.unam.aragon.ico.te.h_de_ss_b.DTO;

public class UsuarioInfoDTO {
    private String identificador; 
    private String rol;
    private String mensajeBienvenida;

    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getMensajeBienvenida() { return mensajeBienvenida; }
    public void setMensajeBienvenida(String mensajeBienvenida) { this.mensajeBienvenida = mensajeBienvenida; }
}