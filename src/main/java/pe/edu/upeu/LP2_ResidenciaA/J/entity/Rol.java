package pe.edu.upeu.LP2_ResidenciaA.J.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Column(length = 30)
    private String nombre;

    @Column(length = 50)
    private String descripcion;

    @Column(length = 1)
    private String estado;

    public Rol() {}

    public Rol(Long idRol, String nombre, String descripcion, String estado) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Long getIdRol() { return idRol; }
    public void setIdRol(Long idRol) { this.idRol = idRol; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
