package br.com.devmedia;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@Transactional
public class UsuarioRepositoryIT {

	@Autowired
	private UsuarioRepository repository;
	
	@Test
	public void deveCadastrarUsuario(){
		Usuario usuario =new Usuario("Maria da Silva",LocalDate.of(1980,Month.JANUARY,15));
		repository.save(usuario);
		List<Usuario> usuariosDoBanco = repository.findByNome("Maria da Silva");
		assertThat(usuariosDoBanco.size(), equalTo(1));
		
	}
	
	
	@Test
	public void deveConsultarUsuarioPorNome(){
		Usuario usuario =new Usuario("Maria da Silva",LocalDate.of(1980,Month.JANUARY,15));
		repository.save(usuario);
		assertThat(repository.findByNome("Maria da Silva").size(), equalTo(1));
		
	}
	
	@Test
	public void deveConsultarTodosOsUsuarios(){
		Usuario maria =new Usuario("Maria da Silva",LocalDate.of(1980,Month.JANUARY,15));
		repository.save(maria);
		
		Usuario joao =new Usuario("Joao da Silva",LocalDate.of(1980,Month.MARCH,12));
		repository.save(joao);	
		
		repository.findAll().forEach(c->assertThat(c,notNullValue()));
	}
	
	@Test
	public void deveModificarUsuario(){
		Usuario usuario =new Usuario("Maria da Silva",LocalDate.of(1980,Month.JANUARY,15));
		repository.save(usuario);
		assertThat(usuario.getNumeroDoNome(),equalTo(0));
		
		usuario.numerologiaDoNome();		
		repository.save(usuario);
		
		usuario =  repository.findByNome("Maria da Silva").get(0);
		
		assertThat(usuario.getNumeroDoNome(), not(equalTo(0)));
		
	}
	
	@Test
	public void deveExcluirUsuario(){
		Usuario usuario =new Usuario("Maria da Silva",LocalDate.of(1980,Month.JANUARY,15));
		repository.save(usuario);
		
		repository.delete(usuario);
		
		assertThat(repository.findByNome("Maria da Silva").size(),equalTo(0));
	}
}
