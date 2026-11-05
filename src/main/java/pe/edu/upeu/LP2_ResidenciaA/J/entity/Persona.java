package pe.edu.upeu.LP2_ResidenciaA.J.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPersona;

    @Column(length = 50)
    private String nombres;

    @Column(name = "ap_Paterno", length = 50)
    private String apPaterno;

    @Column(name = "ap_Materno", length = 50)
    private String apMaterno;

    @Column(name = "nro_documento", length = 8)
    private String nroDocumento;

    @Column(length = 9)
    private String telefono;

    @Column(length = 50)
    private String email;

    @Column(name = "fecha_Nac")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;

    public Persona() {}

    public Persona(Long idPersona, String nombres, String apPaterno, String apMaterno, String nroDocumento, String telefono, String email, Date fechaNac) {
        this.idPersona = idPersona;
        this.nombres = nombres;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.nroDocumento = nroDocumento;
        this.telefono = telefono;
        this.email = email;
        this.fechaNac = fechaNac;
    }

    public Long getIdPersona() { return idPersona; }
    public void setIdPersona(Long idPersona) { this.idPersona = idPersona; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApPaterno() { return apPaterno; }
    public void setApPaterno(String apPaterno) { this.apPaterno = apPaterno; }

    public String getApMaterno() { return apMaterno; }
    public void setApMaterno(String apMaterno) { this.apMaterno = apMaterno; }

    public String getNroDocumento() { return nroDocumento; }
    public void setNroDocumento(String nroDocumento) { this.nroDocumento = nroDocumento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getFechaNac() { return fechaNac; }
    public void setFechaNac(Date fechaNac) { this.fechaNac = fechaNac; }
}
