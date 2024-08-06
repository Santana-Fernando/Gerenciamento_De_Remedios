package remedios.fernando.curso.Gerenciamento_De_Remedios.Remedio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RemedioRepository extends JpaRepository<Remedio, Long> {

	List<Remedio> findAllByAtivoTrue();

}
