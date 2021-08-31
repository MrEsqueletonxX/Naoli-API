package br.com.naoli.controller.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.naoli.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByCpf(String cpf);
	
	Optional<Usuario> findByUsername(String username);
}