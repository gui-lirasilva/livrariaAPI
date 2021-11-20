package br.com.alura.livrariaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.livrariaAPI.modelo.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
