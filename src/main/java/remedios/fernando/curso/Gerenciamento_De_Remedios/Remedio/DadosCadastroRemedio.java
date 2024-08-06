package remedios.fernando.curso.Gerenciamento_De_Remedios.Remedio;

import java.time.LocalDate;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroRemedio(
		@NotBlank
		String nome,
		@Enumerated
		Via via,
		@NotBlank
		String lote,
		
		int quantidade,
		
		@Future
		LocalDate validade,
		
		@Enumerated
		Laboratorio laboratorio) {

}
