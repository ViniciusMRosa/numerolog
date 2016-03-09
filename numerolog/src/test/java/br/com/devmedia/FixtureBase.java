package br.com.devmedia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

@RunWith(ConcordionSpringJunit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
public abstract class FixtureBase {

	
		@Autowired
		protected UsuarioRepository repository;
		protected Usuario usuario;
		
		
		public void criarUsuario(String nome, String dataNascimento){
			this.usuario = new Usuario(nome, converter(dataNascimento));
		}
		
		protected Usuario obterUsuario(String nome, LocalDate dataNascimento) {
			Usuario usuario = repository.findByNome(nome).get(0);
			if(usuario.getDataNascimento().equals(dataNascimento)){
				return usuario;
			}else{
				throw new IllegalStateException();
			}
		}

		protected LocalDate converter(String dataNascimento) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return LocalDate.parse(dataNascimento,formatter);
		}
		
		public void excluirUsuario(){
			repository.delete(usuario);
		}
}
