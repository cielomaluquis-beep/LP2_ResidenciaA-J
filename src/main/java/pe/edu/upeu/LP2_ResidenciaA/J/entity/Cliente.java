package pe.edu.upeu.LP2_ResidenciaA.J.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @OneToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    public Cliente() {}

    public Cliente(Long idCliente, Persona persona) {
        this.idCliente = idCliente;
        this.persona = persona;
    }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }
}
