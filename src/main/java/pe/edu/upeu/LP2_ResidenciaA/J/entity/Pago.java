package pe.edu.upeu.LP2_ResidenciaA.J.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;

    @ManyToOne
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "monto")
    private Double monto;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "metodo_pago", length = 20)
    private String metodoPago;

    @Column(name = "img_pago", length = 200)
    private String imgPago;

    @Column(length = 1)
    private String estado;

    public Pago() {}

    public Pago(Long idPago, Contrato contrato, Usuario usuario, Double monto, Date fecha, String metodoPago, String imgPago, String estado) {
        this.idPago = idPago;
        this.contrato = contrato;
        this.usuario = usuario;
        this.monto = monto;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.imgPago = imgPago;
        this.estado = estado;
    }

    public Long getIdPago() { return idPago; }
    public void setIdPago(Long idPago) { this.idPago = idPago; }

    public Contrato getContrato() { return contrato; }
    public void setContrato(Contrato contrato) { this.contrato = contrato; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public String getImgPago() { return imgPago; }
    public void setImgPago(String imgPago) { this.imgPago = imgPago; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
