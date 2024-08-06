package remedios.fernando.curso.Gerenciamento_De_Remedios.Remedio;

import java.time.LocalDate;

public record DadosDetalhamentoRemedio(Long id, String nome, Via via, String lote, Laboratorio laboratorio,
		LocalDate validade, Boolean ativo, int quantidade) {
	
	
	public DadosDetalhamentoRemedio(Remedio remedio) {
		this(remedio.getId(), remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getLaboratorio(),
				remedio.getValidade(), remedio.getAtivo(), remedio.getQuantidade());
	}
}
