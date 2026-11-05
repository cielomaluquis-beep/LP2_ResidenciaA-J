package pe.edu.upeu.LP2_ResidenciaA.J.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Modulos")
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modulo")
    private Long idModulo;

    @Column(length = 50)
    private String nombre;

    @Column(length = 500)
    private String ruta;

    @Column(length = 500)
    private String icono;

    private Integer orden;

    public Modulo() {}

    public Modulo(Long idModulo, String nombre, String ruta, String icono, Integer orden) {
        this.idModulo = idModulo;
        this.nombre = nombre;
        this.ruta = ruta;
        this.icono = icono;
        this.orden = orden;
    }

    public Long getIdModulo() { return idModulo; }
    public void setIdModulo(Long idModulo) { this.idModulo = idModulo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRuta() { return ruta; }
    public void setRuta(String ruta) { this.ruta = ruta; }

    public String getIcono() { return icono; }
    public void setIcono(String icono) { this.icono = icono; }

    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }
}
