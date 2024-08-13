package remedios.fernando.curso.Gerenciamento_De_Remedios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import remedios.fernando.curso.Gerenciamento_De_Remedios.Usuario.DadosAutenticacao;
import remedios.fernando.curso.Gerenciamento_De_Remedios.Usuario.Usuario;
import remedios.fernando.curso.Gerenciamento_De_Remedios.infra.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> efetuarLogin (@RequestBody @Valid DadosAutenticacao dados){
		var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		var authentication = manager.authenticate(token);
		
		return ResponseEntity.ok(tokenService.gerarToken((Usuario) authentication.getPrincipal()));
	}
}
