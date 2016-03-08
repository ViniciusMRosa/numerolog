package br.com.devmedia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	List<Usuario> findByNome(String nome);
}
