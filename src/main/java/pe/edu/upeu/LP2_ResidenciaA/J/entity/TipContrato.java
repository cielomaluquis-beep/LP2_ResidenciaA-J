package pe.edu.upeu.LP2_ResidenciaA.J.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TIP_CONTRATO")
public class TipContrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipoContrato")
    private Long idTipoContrato;

    @Column(length = 50)
    private String nombre;

    @Column(length = 1)
    private String estado;

    public TipContrato() {}

    public TipContrato(Long idTipoContrato, String nombre, String estado) {
        this.idTipoContrato = idTipoContrato;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Long getIdTipoContrato() { return idTipoContrato; }
    public void setIdTipoContrato(Long idTipoContrato) { this.idTipoContrato = idTipoContrato; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
