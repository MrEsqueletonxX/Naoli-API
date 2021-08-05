package br.com.naoli.controller.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.naoli.model.Usuario;

public class UsuarioDTO {
	private Long id;
	private String nome;
	private String cpf;
	private String username;
	private LocalDateTime dataCriacao;
	
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf();
		
		this.username = usuario.getUsername();
		
		this.dataCriacao = usuario.getDataCriacao();
	}
	
	public static List<UsuarioDTO> converterLista(List<Usuario> usuarios){
		return usuarios.stream()
				.map(UsuarioDTO::new)
				.collect(Collectors.toList());
	}
	
	public UsuarioDTO converterUsuario(Usuario usuario) {
		return new UsuarioDTO(usuario);
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getUsername() {
		return username;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
}
