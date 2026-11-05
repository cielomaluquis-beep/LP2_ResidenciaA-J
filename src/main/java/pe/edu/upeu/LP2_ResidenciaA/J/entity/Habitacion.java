package pe.edu.upeu.LP2_ResidenciaA.J.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Habitaciones")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private Long idHabitacion;

    @Column(length = 8)
    private String numero;

    private Integer piso;

    @Column(length = 200)
    private String descripcion;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "img_habitacion", length = 200)
    private String imgHabitacion;

    @Column(length = 1)
    private String estado;

    public Habitacion() {}

    public Habitacion(Long idHabitacion, String numero, Integer piso, String descripcion, Double precio, String imgHabitacion, String estado) {
        this.idHabitacion = idHabitacion;
        this.numero = numero;
        this.piso = piso;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imgHabitacion = imgHabitacion;
        this.estado = estado;
    }

    public Long getIdHabitacion() { return idHabitacion; }
    public void setIdHabitacion(Long idHabitacion) { this.idHabitacion = idHabitacion; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public Integer getPiso() { return piso; }
    public void setPiso(Integer piso) { this.piso = piso; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public String getImgHabitacion() { return imgHabitacion; }
    public void setImgHabitacion(String imgHabitacion) { this.imgHabitacion = imgHabitacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
