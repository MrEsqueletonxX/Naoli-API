package br.com.naoli.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

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

import br.com.naoli.controller.DTO.UsuarioDTO;
import br.com.naoli.controller.form.AtualizacaoUsuarioForm;
import br.com.naoli.controller.form.UsuarioForm;
import br.com.naoli.controller.repository.UsuarioRepository;
import br.com.naoli.model.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/listAll")
	public List<UsuarioDTO> listaTodosUsuarios(){
		List<Usuario> listUsuarios = usuarioRepository.findAll();
		return UsuarioDTO.converterLista(listUsuarios);
	}
	
	@GetMapping("/listById/{id}")
	public ResponseEntity<UsuarioDTO> listById(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isPresent()) {
			return ResponseEntity.ok(new UsuarioDTO(usuario.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/listByCpf/{cpf}")
	public ResponseEntity<UsuarioDTO> listByCpf(@PathVariable("cpf") String cpf) {
		Optional<Usuario> usuario = usuarioRepository.findByCpf(cpf);
		if(usuario.isPresent()) {
			return ResponseEntity.ok(new UsuarioDTO(usuario.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/cadastro")
	@Transactional
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
		//Convertendo UsuarioForm para Usuario
		Usuario usuario = usuarioForm.converterUsuarioForm();
		usuarioRepository.save(usuario);
		
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
	}
	
	@PutMapping("/update/{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody @Valid AtualizacaoUsuarioForm usuarioForm) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if(optional.isPresent()) {
			usuarioForm.atualizar(id, usuarioRepository);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}