package br.com.naoli.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public UsuarioDTO listById(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isPresent()) {
			return new UsuarioDTO(usuario.get());
		}
		return null;
	}
	
	@PostMapping("/cadastro")
	@Transactional
	public Usuario cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm) {
		//Convertendo UsuarioForm para Usuario
		Usuario usuario = usuarioForm.converterUsuarioForm();
		return this.usuarioRepository.save(usuario);
	}
	
	@PutMapping("/update/{id}")
	@Transactional
	public UsuarioDTO updateUsuario(@PathVariable Long id, @RequestBody @Valid AtualizacaoUsuarioForm usuarioForm) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if(optional.isPresent()) {
			Usuario usuario = usuarioForm.atualizar(id, usuarioRepository);
			return new UsuarioDTO(usuario);
		}
		return null;
	}
	
	@DeleteMapping("/delete/{id}")
	@Transactional
	public String delete(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		String mensagem = "Deletado com sucesso!";
		if(usuario.isPresent()) {
			usuarioRepository.deleteById(id);
			return mensagem;
		}
		
		return null;
	}
}