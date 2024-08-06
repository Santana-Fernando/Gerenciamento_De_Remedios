package remedios.fernando.curso.Gerenciamento_De_Remedios.Remedio;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRemedio(
		@NotNull
		Long id, 
		
		String nome, 
		
		Via via, 
		
		Laboratorio laboratorio) {

}
