package pe.edu.upeu.LP2_ResidenciaA.J.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.LP2_ResidenciaA.J.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}
