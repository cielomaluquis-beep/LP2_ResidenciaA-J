package pe.edu.upeu.LP2_ResidenciaA.J.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Notificaciones")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Long idNotificacion;

    @Column(length = 50)
    private String tipo;

    @Column(length = 50)
    private String mensaje;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    private Integer leido;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "url_destino", length = 50)
    private String urlDestino;

    public Notificacion() {}

    public Notificacion(Long idNotificacion, String tipo, String mensaje, Date fechaCreacion, Integer leido, Usuario usuario, String urlDestino) {
        this.idNotificacion = idNotificacion;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.leido = leido;
        this.usuario = usuario;
        this.urlDestino = urlDestino;
    }

    public Long getIdNotificacion() { return idNotificacion; }
    public void setIdNotificacion(Long idNotificacion) { this.idNotificacion = idNotificacion; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public Integer getLeido() { return leido; }
    public void setLeido(Integer leido) { this.leido = leido; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getUrlDestino() { return urlDestino; }
    public void setUrlDestino(String urlDestino) { this.urlDestino = urlDestino; }
}
