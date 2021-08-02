package br.com.naoli.controller.form;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.naoli.controller.repository.UsuarioRepository;
import br.com.naoli.model.Usuario;

public class AtualizacaoUsuarioForm {
	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String nome;
	
	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String username;
	
	@NotNull
	@NotEmpty
	@Length(min = 6)
	private String password;
	
	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(id);
		usuario.setNome(nome);
		usuario.setPassword(password);
		usuario.setUsername(username);
		return usuario;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}