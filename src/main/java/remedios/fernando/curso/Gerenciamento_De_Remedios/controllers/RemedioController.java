package remedios.fernando.curso.Gerenciamento_De_Remedios.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import remedios.fernando.curso.Gerenciamento_De_Remedios.Remedio.DadosAtualizarRemedio;
import remedios.fernando.curso.Gerenciamento_De_Remedios.Remedio.DadosCadastroRemedio;
import remedios.fernando.curso.Gerenciamento_De_Remedios.Remedio.DadosDetalhamentoRemedio;
import remedios.fernando.curso.Gerenciamento_De_Remedios.Remedio.DadosListagemRemedio;
import remedios.fernando.curso.Gerenciamento_De_Remedios.Remedio.Remedio;
import remedios.fernando.curso.Gerenciamento_De_Remedios.Remedio.RemedioRepository;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

	@Autowired
	private RemedioRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriBuilder) {
		var remedio = new Remedio(dados);
		repository.save(remedio);
		
		var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
	}
	
	@GetMapping
	public ResponseEntity<List<DadosListagemRemedio>> listar() {
		var list = repository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList();
		
		return ResponseEntity.ok(list);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados){
		var remedio = repository.getReferenceById(dados.id());
		remedio.atualizarInformecoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.inativar();
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("ativar/{id}")
	@Transactional
	public ResponseEntity<Void> ativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.ativar();
		
		return ResponseEntity.noContent().build();
	}
}
