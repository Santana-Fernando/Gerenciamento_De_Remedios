package remedios.fernando.curso.Gerenciamento_De_Remedios.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import remedios.fernando.curso.Gerenciamento_De_Remedios.Remedio.DadosAtualizarRemedio;
import remedios.fernando.curso.Gerenciamento_De_Remedios.Remedio.DadosCadastroRemedio;
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
	public void cadastrar(@RequestBody @Valid DadosCadastroRemedio dados) {
		repository.save(new Remedio(dados));
	}
	
	@GetMapping
	public List<DadosListagemRemedio> listar() {
		return repository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList();
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizarRemedio dados){
		var remedio = repository.getReferenceById(dados.id());
		remedio.atualizarInformecoes(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@DeleteMapping("inativar/{id}")
	@Transactional
	public void inativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.inativar();
	}
	
	@PutMapping("ativar/{id}")
	@Transactional
	public void ativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.ativar();
	}
}
