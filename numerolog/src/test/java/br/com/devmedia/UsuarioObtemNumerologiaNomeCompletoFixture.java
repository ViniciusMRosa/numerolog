package br.com.devmedia;

public class UsuarioObtemNumerologiaNomeCompletoFixture extends FixtureBase{
	
	public void cadastrarNumerologiaDoNomeDousuario(){
		usuario.numerologiaDoNome();
		repository.save(usuario);
	}
	
	public int obterNumerologiaNomeCompleto(){
		this.usuario = obterUsuario(usuario.getNome(), usuario.getDataNascimento());
		return usuario.getNumeroDoNome();
	}
}
