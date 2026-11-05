package pe.edu.upeu.LP2_ResidenciaA.J.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Permisos_rol_modulo")
public class PermisoRolModulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Long idPermiso;

    @Column(name = "ver_modulo")
    private Integer verModulo;

    @Column(name = "p_view")
    private Integer pView;

    @Column(name = "p_create")
    private Integer pCreate;

    @Column(name = "p_edit")
    private Integer pEdit;

    @Column(name = "p_delete")
    private Integer pDelete;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "id_modulo")
    private Modulo modulo;

    public PermisoRolModulo() {}

    public PermisoRolModulo(Long idPermiso, Integer verModulo, Integer pView, Integer pCreate, Integer pEdit, Integer pDelete, Rol rol, Modulo modulo) {
        this.idPermiso = idPermiso;
        this.verModulo = verModulo;
        this.pView = pView;
        this.pCreate = pCreate;
        this.pEdit = pEdit;
        this.pDelete = pDelete;
        this.rol = rol;
        this.modulo = modulo;
    }

    public Long getIdPermiso() { return idPermiso; }
    public void setIdPermiso(Long idPermiso) { this.idPermiso = idPermiso; }

    public Integer getVerModulo() { return verModulo; }
    public void setVerModulo(Integer verModulo) { this.verModulo = verModulo; }

    public Integer getpView() { return pView; }
    public void setpView(Integer pView) { this.pView = pView; }

    public Integer getpCreate() { return pCreate; }
    public void setpCreate(Integer pCreate) { this.pCreate = pCreate; }

    public Integer getpEdit() { return pEdit; }
    public void setpEdit(Integer pEdit) { this.pEdit = pEdit; }

    public Integer getpDelete() { return pDelete; }
    public void setpDelete(Integer pDelete) { this.pDelete = pDelete; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public Modulo getModulo() { return modulo; }
    public void setModulo(Modulo modulo) { this.modulo = modulo; }
}
