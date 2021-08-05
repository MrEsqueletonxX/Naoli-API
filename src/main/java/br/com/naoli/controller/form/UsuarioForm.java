package br.com.naoli.controller.form;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.naoli.model.Usuario;

@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "cpf"),
                @UniqueConstraint(columnNames = "username")
        }
)
public class UsuarioForm {
	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String nome;
	
	@NotNull
	@NotEmpty
	@Length(min = 11, max = 14)
	@Column(unique = true)
	private String cpf;
	
	@NotNull
	@NotEmpty
	@Length(min = 3)
	@Column(unique = true)
	private String username;
	
	@NotNull
	@NotEmpty
	@Length(min = 6)
	private String password;
	
	public Usuario converterUsuarioForm() {
		return new Usuario(nome, cpf, username, password);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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