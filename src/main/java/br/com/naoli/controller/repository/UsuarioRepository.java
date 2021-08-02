package br.com.naoli.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.naoli.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
}