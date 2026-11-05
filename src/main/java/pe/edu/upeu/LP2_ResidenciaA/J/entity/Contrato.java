package pe.edu.upeu.LP2_ResidenciaA.J.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Contrato")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato")
    private Long idContrato;

    @ManyToOne
    @JoinColumn(name = "id_tipoContrato")
    private TipContrato tipContrato;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;

    @Column(name = "fecha_reserva")
    @Temporal(TemporalType.DATE)
    private Date fechaReserva;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "metodo_pago", length = 100)
    private String metodoPago;

    @Column(name = "img_pagoReserva", length = 200)
    private String imgPagoReserva;

    @Column(length = 1)
    private String estado;

    public Contrato() {}

    public Contrato(Long idContrato, TipContrato tipContrato, Cliente cliente, Usuario usuario, Habitacion habitacion,
                    Date fechaReserva, Date fechaInicio, Date fechaFin, Double monto, String metodoPago,
                    String imgPagoReserva, String estado) {
        this.idContrato = idContrato;
        this.tipContrato = tipContrato;
        this.cliente = cliente;
        this.usuario = usuario;
        this.habitacion = habitacion;
        this.fechaReserva = fechaReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.imgPagoReserva = imgPagoReserva;
        this.estado = estado;
    }

    public Long getIdContrato() { return idContrato; }
    public void setIdContrato(Long idContrato) { this.idContrato = idContrato; }

    public TipContrato getTipContrato() { return tipContrato; }
    public void setTipContrato(TipContrato tipContrato) { this.tipContrato = tipContrato; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Habitacion getHabitacion() { return habitacion; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }

    public Date getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(Date fechaReserva) { this.fechaReserva = fechaReserva; }

    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }

    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public String getImgPagoReserva() { return imgPagoReserva; }
    public void setImgPagoReserva(String imgPagoReserva) { this.imgPagoReserva = imgPagoReserva; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
